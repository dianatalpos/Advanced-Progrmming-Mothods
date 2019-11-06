package Domain;

import Domain.ADT.*;
import Domain.Stmt.IStmt;
import Domain.Value.IValue;

public class PrgState
{
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, IValue> symTbl;
    MyIList<IValue> outList;
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

    public PrgState(MyIStack<IStmt> exx, MyIDictionary<String, IValue> dict, MyIList<IValue> out, IStmt state)
    {
        exeStack = exx;
        symTbl = dict;
        outList = out;
        exeStack.push(state);
        original = state.deepCopy();
    }

}
