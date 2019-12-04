package Controller;

import Domain.ADT.IHeap;
import Domain.ADT.MyIStack;
import Domain.PrgState;
import Domain.Stmt.IStmt;
import Domain.Value.IValue;
import Domain.Value.RefValue;
import Errors.MyError;
import Repository.IRepo;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;



public class Controller {
    IRepo repo;
    ExecutorService executor;

    public Controller(IRepo r) {
        repo = r;
    }

    public Map<Integer, IValue> ConservativeGarbageCollector()
    {
        IHeap heap = repo.getPrgList().get(0).getHeap();
        Collection<IValue> heapValues = heap.getContent().values();
        Collection<Integer> list = new ArrayList<>();

        List<Integer> new_heap = repo.getPrgList().stream()
                    .map(prg->prg.getSymTbl())
                    .map(prg->prg.getContent())
                    .map(prg->prg.values())
                    .map(prg-> { List<Integer> lst = getAddrFromSymTable(prg, heapValues); return lst;})
                    .reduce((x,y)->{x.addAll(y); return x;})
                    .orElse(new ArrayList<Integer>());
        //.reduce(list, (x, y)->{List<Integer> lst = getAddrFromSymTable(y,heapValues); x.addAll(lst); return x;});
        //return newheap;

       return heap.getContent().entrySet().stream()
                        .filter(e->new_heap.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap)
    {
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues, Collection<IValue> heap){
        List<Integer> list = symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {
                    RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());

        List<Integer> list_heap = heap.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {
                    RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
        list.addAll(list_heap);
        return list;
    }


    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (Exception e) {
            }
        });
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());

        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {

                        }
                        return null;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
            prgList.addAll(newPrgList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        prgList.forEach(prg ->{ try{ repo.logPrgStateExec(prg);} catch(Exception e) {System.out.println(e.getMessage());}});
        repo.setPrgList(prgList);
    }


/*
    public int allSteps() {
        PrgState prg = repo.getCrtPrg();
        try {
            while (true) {
                prg.getHeap().setContent(unsafeGarbageCollector(
                        getAddrFromSymTable(prg.getSymTbl().getContent().values(),
                        prg.getHeap().getContent().values()), prg.getHeap().getContent()));

                //System.out.println(prg.toString());
                prg.oneStep();
                repo.logPrgStateExec(prg);
            }
        } catch (MyError e) {
            System.out.println(e.getMessage());
            return 1;
        }

    }
*/
    public void allSteps()
    {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while(prgList.size() >0)
        {
            prgList.get(0).getHeap().setContent(ConservativeGarbageCollector());
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }
}