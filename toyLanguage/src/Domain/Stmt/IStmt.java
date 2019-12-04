package Domain.Stmt;

import Domain.ICopy;
import Errors.MyError;
import Domain.PrgState;

public interface IStmt extends ICopy<IStmt> {
    public PrgState execute(PrgState state) throws MyError;
}
