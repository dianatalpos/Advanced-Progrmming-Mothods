package Repository;

import Domain.ADT.MyIList;
import Domain.ADT.MyList;
import Domain.PrgState;

public class Repo implements IRepo {
    int currentPrg;
    MyIList<PrgState> programs;

    public Repo(PrgState prg)
    {
        currentPrg = 0;
        programs = new MyList<PrgState>();
        programs.add(prg);
    }

    @Override
    public PrgState getCrtPrg() {
        return programs.get(currentPrg);
    }

    public void addPrg(PrgState prg)
    {
        programs.add(prg);
    }
}
