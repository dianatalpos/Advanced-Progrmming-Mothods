package Domain.Stmt;

import Domain.PrgState;
import Errors.MyError;

public class NopStmt implements IStmt {

    public NopStmt()
    {
    }

    public String toString()
    {
        return "(nop)";
    }
    @Override
    public PrgState execute(PrgState state) throws MyError {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
