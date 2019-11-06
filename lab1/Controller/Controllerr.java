package Controller;

import Errors.MyErrors;
import Model.AppleTree;
import Model.CherryTree;
import Model.PearTree;
import Model.Tree;
import Repository.Repository;
import com.sun.xml.internal.ws.message.stream.StreamHeader11;

public class Controllerr {
    private Repository repo;

    public Controllerr(Repository r)
    {
        this.repo = r;
    }

    public void addController(Tree t) throws MyErrors
    {
        repo.add(t);
    }

    public void deleteController(int position) throws MyErrors
    {
        this.repo.delete(position);
    }

    public String filterByAge(int age)
    {
        Tree[] obj  = this.repo.getContent();
        String result = "";
        for(int i=0; i<obj.length; i++)
        {
            if (obj[i].getAge() >=3 )
            {
                result += obj[i].toString() + "\n";
            }
        }
        return result;
    }

}
