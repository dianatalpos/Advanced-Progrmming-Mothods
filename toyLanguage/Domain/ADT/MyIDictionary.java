package Domain.ADT;

import Errors.MyError;

import java.util.Map;
import java.util.Set;

public interface MyIDictionary<T1, T2> {
    public T2 lookUp(T1 elem) throws MyError;
    public boolean isDefined(T1 elem);
    public void update(T1 elem, T2 val) throws MyError;
    public void put(T1 key, T2 elem);
    public T2 get(T1 key);
    public boolean isEmpty();
    public void delete(T1 key);
    public Map<T1,T2> getContent();
    public Set<T1> getKeys();
}
