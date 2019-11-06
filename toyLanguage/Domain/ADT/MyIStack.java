package Domain.ADT;

public interface MyIStack<T> {
    public T pop();
    public void push(T elem);
    public boolean isEmpty();
}
