package Domain.ADT;

import Domain.ICopy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyList<T> implements MyIList<T> {

    private List<T> lst;

    public MyList() {
        lst = new ArrayList<T>();
    }
    @Override
    public void add(T elem) {
        lst.add(elem);
    }

    @Override
    public String toString() {
        String result = "";
        for(int i =0;i < lst.size(); i++)
        {
            result += lst.get(i).toString()+ "\n";
        }
        return result;
    }

    @Override
    public int size() {
        return lst.size();
    }

    @Override
    public void set(MyIList<T> new_list) {
        lst = new_list.getLst();
    }

    @Override
    public List<T> getLst() {
        return lst;
    }

    @Override
    public T get(int i) {
        return lst.get(i);
    }

}
