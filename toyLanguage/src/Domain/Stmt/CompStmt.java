package Domain.Stmt;

import Domain.ADT.MyIStack;
import Domain.PrgState;
import Errors.MyError;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt second;

    public CompStmt(IStmt f, IStmt s)
    {
        first = f;
        second = s;
    }

    public String toString()
    {
        return "(" + first.toString() + ";" + second.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }
}
