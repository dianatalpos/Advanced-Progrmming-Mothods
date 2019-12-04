package Domain;

import Domain.ADT.*;
import Domain.Stmt.IStmt;
import Domain.Value.IValue;
import Errors.MyError;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class PrgState
{
    static int id=0;
    int myId;
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, IValue> symTbl;
    MyIList<IValue> outList;
    MyIDictionary<String, BufferedReader> fileTable;
    IStmt original;
    IHeap heap;
    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public int value()
    {
        return myId;
    }

    public static synchronized void newId()
    {
        id++;
    }

    public boolean isNotCompleted()
    {
        if(exeStack.isEmpty())
            return false;
        else return true;
    }

    public PrgState oneStep() throws MyError {
        System.out.println(this.toString());
        if (exeStack.isEmpty()) throw new MyError("End of the program!");
        IStmt current = exeStack.pop();
        PrgState sts = current.execute(this);
        return sts;
    }


    public String toString()
    {
        String result = "";
        result+="Instructions left: \n";
        result += exeStack.toString();
        result += "Current variable: "+ "\n";
        result += symTbl.toString();
        result += "Output :\n";
        result += outList.toString();
        result +="Heap:\n";
        result += heap.toString();
        return result;
    }

    public MyIList<IValue> getOutList()
    {
        return outList;
    }

    public MyIDictionary<String, IValue> getSymTbl()
    {
        return symTbl;
    }

    public IStmt getOriginal() {return original;}

    public MyIDictionary<String, BufferedReader> getFileTable() { return fileTable;}
    public IHeap getHeap()
    {
        return heap;
    }
    public PrgState(IHeap h,MyIStack<IStmt> exx, MyIDictionary<String, IValue> dict, MyIList<IValue> out, MyIDictionary<String, BufferedReader> file, IStmt state)
    {
        newId();
        myId = id;
        exeStack = exx;
        symTbl = dict;
        outList = out;
        fileTable = file;
        heap = h;
        exeStack.push(state);
        original = state.deepCopy();
    }

}
