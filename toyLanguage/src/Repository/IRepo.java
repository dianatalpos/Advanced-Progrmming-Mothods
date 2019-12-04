package Repository;

import Domain.ADT.MyIList;
import Domain.PrgState;
import Errors.MyError;

import java.util.List;

public interface IRepo {
    public PrgState getCrtPrg();
    public List<PrgState> getPrgList();
    public void setPrgList(List<PrgState> programs);
    void logPrgStateExec(PrgState program) throws MyError;
}
