package Domain.ADT;
import Domain.ICopy;
import Errors.MyError;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MyDictionary<T1, T2> implements MyIDictionary<T1, T2>{
    private Map<T1, T2> dict;

    public String toString()
    {
        String result = "";
        Iterator it = dict.keySet().iterator();
        while(it.hasNext())
        {
            T1  val = (T1)it.next();
            result += val.toString() + " --> ";
            T2 val2 = dict.get(val);
            result += val2.toString()+ "\n";
        }
        return result;
    }

    public void delete(T1 key)
    {
        dict.remove(key);
    }

    @Override
    public Map<T1, T2> getContent() {
        return dict;
    }

    public T2 lookUp(T1 id) throws MyError
    {
        T2 val = dict.get(id);
        if (val == null)
            throw new MyError("Variable not defined!");
        return val;
    }

    @Override
    public boolean isDefined(T1 elem) {
        if (dict.get(elem)  == null)
            return false;
        else return true;
    }

    @Override
    public void update(T1 elem, T2 val) throws MyError {
        if (!dict.containsKey(elem))
            throw new MyError("Inexisting key!");
        dict.put(elem, val);
    }

    public MyDictionary(){
        dict = new HashMap<T1,T2>();
    }
    @Override
    public void put(T1 key, T2 elem) {
        dict.put(key, elem);
    }

    @Override
    public T2 get(T1 key) {
        return dict.get(key);
    }

    public Set<T1> getKeys()
    {
        return dict.keySet();
    }

    @Override
    public boolean isEmpty() {
        return dict.isEmpty();
    }
}
