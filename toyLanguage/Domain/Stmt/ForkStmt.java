package Domain.Stmt;

import Domain.ADT.*;
import Domain.PrgState;
import Domain.Value.IValue;
import Errors.MyError;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.Set;

public class ForkStmt implements IStmt {
    IStmt program;

    public ForkStmt(IStmt newprogram)
    {
        program = newprogram;
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIStack<IStmt> stack = new MyStack<IStmt>();
        MyIDictionary<String, IValue> symbols = new MyDictionary<String, IValue>();
        Set<String> keys = state.getSymTbl().getKeys();
        Iterator it = keys.iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            IValue val = state.getSymTbl().get(key).deepCopy();
            symbols.put(key, val);
        }
        IHeap heap = state.getHeap();
        MyIList<IValue> out = state.getOutList();
        MyIDictionary<String, BufferedReader> file = state.getFileTable();
        PrgState newProgram = new PrgState(heap, stack, symbols, out, file, program);
        return newProgram;
    }

    public String toString()
    {
        return " fork( " + program.toString() + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(program.deepCopy());
    }
}
