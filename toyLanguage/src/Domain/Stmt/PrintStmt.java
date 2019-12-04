package Domain.Stmt;

import Domain.ADT.IHeap;
import Domain.ADT.MyIList;
import Domain.Expr.Exp;
import Domain.PrgState;
import Domain.Value.IValue;
import Errors.MyError;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp e)
    {
        exp = e;
    }

    public String toString()
    {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIList<IValue> lst = state.getOutList();
        IHeap heap = state.getHeap();
        IValue i1 = exp.eval(state.getSymTbl(), heap);
        lst.add(i1);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy()) ;
    }
}
