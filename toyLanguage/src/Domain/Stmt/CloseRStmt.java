package Domain.Stmt;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.Expr.Exp;
import Domain.PrgState;
import Domain.Type.StringType;
import Domain.Value.IValue;
import Domain.Value.StringValue;
import Errors.MyError;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRStmt implements IStmt {
    Exp expression;

    public CloseRStmt(Exp expr)
    {
        expression = expr;
    }

    public String toString()
    {
        return "closeRFile(" + expression.toString() + ");";
    }


    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIDictionary<String, BufferedReader> file_table = state.getFileTable();
        MyIDictionary<String, IValue> symbols = state.getSymTbl();
        IHeap heap = state.getHeap();
        IValue value = expression.eval(symbols, heap);
        if(!value.getType().equals(new StringType()))
            throw new MyError("The name of the file is not a string!");
        StringValue value1 = (StringValue)value;
        if(!file_table.isDefined(value1.getVal()))
            throw new MyError("You try to close a file that was not opened before!");
        BufferedReader reader = file_table.get(value1.getVal());
        try {
            reader.close();
        }
        catch(IOException e)
        {
            throw new MyError("Something went wrong closing the file!");
        }
        file_table.delete(value1.getVal());
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CloseRStmt(expression.deepCopy());
    }
}
