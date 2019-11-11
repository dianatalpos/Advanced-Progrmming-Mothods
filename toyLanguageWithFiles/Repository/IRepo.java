package Repository;

import Domain.PrgState;
import Errors.MyError;

public interface IRepo {
    public PrgState getCrtPrg();
    void logPrgStateExec() throws MyError;
}
