package Repository;

import Errors.MyErrors;
import Model.Tree;

public interface Repository {
    public void add(Tree t) throws MyErrors;
    public void delete(int i) throws MyErrors;
    public Tree[] getContent();
}
