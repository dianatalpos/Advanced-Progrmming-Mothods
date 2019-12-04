package Domain.ADT;

import Domain.Value.IValue;
import Errors.MyError;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyHeap implements IHeap {
    private Map<Integer, IValue> dict;
    private int nextFree;

    public MyHeap()
    {
        dict = new HashMap<Integer, IValue>();
        nextFree =1;
    }

    public Integer getKey()
    {
        return nextFree-1;
    }

    @Override
    public boolean isDefined(Integer elem) {
        if (dict.get(elem)  == null)
            return false;
        else return true;
    }

    @Override
    public void setContent(Map<Integer, IValue> map) {
        dict = map;
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return dict;
    }

    public String toString()
    {
        String result = "";
        Iterator it = dict.keySet().iterator();
        while(it.hasNext())
        {
            Integer  val = (Integer)it.next();
            result += val.toString() + " --> ";
            IValue val2 = dict.get(val);
            result += val2.toString()+ "\n";
        }
        return result;
    }

    public void delete(Integer key)
    {
        dict.remove(key);
    }

    @Override
    public void update(Integer elem, IValue val) throws MyError {
        if (!dict.containsKey(elem))
            throw new MyError("Inexisting key!");
        dict.put(elem, val);
    }

    @Override
    public void put(IValue elem) {
        dict.put( nextFree, elem);
        nextFree +=1;
    }

    @Override
    public IValue get(Integer key) {
        return dict.get(key);
    }

    @Override
    public boolean isEmpty() {
        return dict.isEmpty();
    }



}
