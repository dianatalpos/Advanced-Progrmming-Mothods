package Controller;

import Domain.ADT.MyIStack;
import Domain.PrgState;
import Domain.Stmt.IStmt;
import Errors.MyError;
import Repository.IRepo;

public class Controller {
    IRepo repo;

    public Controller(IRepo r) {
        repo = r;
    }

    public PrgState oneStep(PrgState state, int f) throws MyError {

        if(f==1) System.out.println(state.toString());
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty()) throw new MyError("End of the program!");
        IStmt current = stk.pop();
        return current.execute(state);
    }

    public int allSteps(int f) {
        PrgState prg = repo.getCrtPrg();
        try {
            while (true) {
                //System.out.println(prg.toString());
                oneStep(prg, f);
            }
        } catch (MyError e) {
            System.out.println(e.getMessage());
            return 1;
        }

    }
}