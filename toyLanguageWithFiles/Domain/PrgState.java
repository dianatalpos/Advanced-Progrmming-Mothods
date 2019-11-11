package Domain;

import Domain.ADT.*;
import Domain.Stmt.IStmt;
import Domain.Value.IValue;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class PrgState
{
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, IValue> symTbl;
    MyIList<IValue> outList;
    MyIDictionary<String, BufferedReader> fileTable;
    IStmt original;

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
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

    public PrgState(MyIStack<IStmt> exx, MyIDictionary<String, IValue> dict, MyIList<IValue> out, MyIDictionary<String, BufferedReader> file, IStmt state)
    {
        exeStack = exx;
        symTbl = dict;
        outList = out;
        fileTable = file;
        exeStack.push(state);
        original = state.deepCopy();
    }

}
