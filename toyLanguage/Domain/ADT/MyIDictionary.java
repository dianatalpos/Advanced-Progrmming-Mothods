package Domain.ADT;

import Errors.MyError;

public interface MyIDictionary<T1, T2> {
    public T2 lookUp(T1 elem) throws MyError;
    public boolean isDefined(T1 elem);
    public void update(T1 elem, T2 val) throws MyError;
    public void put(T1 key, T2 elem);
    public T2 get(T1 key);
    public boolean isEmpty();

}
