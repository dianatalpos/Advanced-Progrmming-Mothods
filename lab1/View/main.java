package View;

import Controller.Controllerr;
import Errors.MyErrors;
import Model.AppleTree;
import Model.CherryTree;
import Model.PearTree;
import Repository.arrayRepo;

public class main {

    public static void main(String[] args)
    {
        AppleTree a1 = new AppleTree(12);
        AppleTree a2 = new AppleTree(13);
        CherryTree c1  = new CherryTree(1);
        CherryTree c2 = new CherryTree(4);
        PearTree p1 = new PearTree(2);
        PearTree p2 = new PearTree(4);
        arrayRepo repo = new arrayRepo(5);
        Controllerr ctr = new Controllerr(repo);
        try
        {
            repo.add(a1);
            repo.add(a2);
            repo.add(c1);

        }
        catch (MyErrors e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            repo.add(c2);
            repo.add(p1);
            repo.add(p2);
        }
        catch (MyErrors e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println(ctr.filterByAge(3));
    }

}
