package Domain.ADT;

import java.util.List;

public interface MyIList<T> {
    void add(T elem);
    int size();
    void set(MyIList<T> new_list);
    List<T> getLst();
    T get(int i);
}
