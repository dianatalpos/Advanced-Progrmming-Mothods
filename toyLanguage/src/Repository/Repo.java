package Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import Domain.ADT.*;
import Domain.PrgState;
import Domain.Stmt.IStmt;
import Domain.Value.IValue;
import Errors.MyError;

import java.util.Iterator;
import java.util.List;

public class Repo implements IRepo {
    int currentPrg;
    List<PrgState> programs;
    String log_path;
    public Repo(PrgState prg, String path)
    {
        currentPrg = 0;
        programs = new ArrayList<PrgState>();
        log_path = path;
        programs.add(prg);
        try
        {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();

        }
        catch (IOException e)
        {

        }
    }


    @Override
    public PrgState getCrtPrg() {

        return programs.get(currentPrg);
    }

    @Override
    public List<PrgState> getPrgList() {
        return programs;
    }

    @Override
    public void setPrgList(List<PrgState> programs) {

        this.programs = programs;
    }

    @Override
    public void logPrgStateExec(PrgState program) throws MyError {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(log_path, true)));
        }
        catch(IOException e)
        {
            throw new MyError("File doesn't exist or cannot be open!");
        }
        logFile.print("Id: " + program.value() + "\n");
        logFile.print("ExeStack:\n");
        MyIStack<IStmt> stk = program.getExeStack();
        logFile.print(stk.toString());
        logFile.print("\n");
        logFile.print("SymTable:");
        MyIDictionary<String, IValue> dict = program.getSymTbl();
        String result = dict.toString();
        logFile.print("\n");
        logFile.print(result);
        logFile.print("\n");
        logFile.print("Output:\n");
        MyIList<IValue> out = program.getOutList();
        logFile.print(out.toString());
        logFile.print("\n");
        MyIDictionary<String, BufferedReader> files = program.getFileTable();
        logFile.print(files.toString());
        logFile.print('\n');
        logFile.print("\n");
        IHeap heap = program.getHeap();
        logFile.print("\n");
        logFile.print("Heap:\n");
        logFile.print(heap.toString());
        logFile.print('\n');
        logFile.print("\n");

        logFile.close();
    }

    public void addPrg(PrgState prg)
    {
        programs.add(prg);
    }
}
