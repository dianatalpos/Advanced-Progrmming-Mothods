package Domain.ADT;

import Domain.ICopy;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;
    public MyStack()
    {
        stack = new  Stack<T>();
    }

    public String toString()
    {
        String result = "";
        Stack<T> stc = (Stack<T>)stack.clone();
        while( !stc.isEmpty())
        {
            T elem = stc.pop();
            result += elem.toString() + "\n";
        }
        return result;
    }
    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
