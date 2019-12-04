package Domain.ADT;

import Domain.Value.IValue;
import Errors.MyError;

import java.util.Map;

public interface IHeap {

    public void update(Integer elem, IValue val) throws MyError;
    public void put(IValue elem);
    public IValue get(Integer key);
    public boolean isEmpty();
    public void delete(Integer key);
    public Integer getKey();
    public boolean isDefined(Integer elem);
    public void setContent(Map<Integer, IValue> map);
    public Map<Integer, IValue> getContent();
}
