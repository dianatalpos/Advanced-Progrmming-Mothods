package Domain.Stmt;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.Expr.Exp;
import Domain.PrgState;
import Domain.Type.IType;
import Domain.Type.IntType;
import Domain.Type.StringType;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Domain.Value.StringValue;
import Errors.MyError;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    Exp expression;
    String var_name;

    public ReadFileStmt(Exp exp, String var_name2)
    {
        expression = exp;
        var_name = var_name2;
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIDictionary<String, IValue> symbols = state.getSymTbl();
        IHeap heap =  state.getHeap();
        if (!symbols.isDefined(var_name))
            throw new MyError("The variable is not defined!");
        if (! symbols.get(var_name).getType().equals(new IntType()))
            throw new MyError("The value of the variable is not an integer!");
        IValue value = expression.eval(state.getSymTbl(), heap);
        IType type = value.getType();
        if(!type.equals(new StringType()))
            throw new MyError("The type is not a String value!");
        MyIDictionary<String, BufferedReader> file_table = state.getFileTable();
        StringValue val = (StringValue)value;
        if(! file_table.isDefined(val.getVal()))
            throw new MyError("The file is not opened!");
        BufferedReader reader = file_table.get(val.getVal());
        int number = 0;
        try {
            String line = reader.readLine();
            if(line != null) {
                number = Integer.parseInt(line);
            }
        }
        catch (IOException e)
        {
            throw new MyError("Something went wrong reading the file!");
        }
        IValue newValue = new IntValue(number);
        symbols.put(var_name, newValue);
        return null;
    }

    public String toString()
    {
        return "readFile(" + expression.toString() + "," + var_name + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFileStmt(expression.deepCopy(), var_name);
    }
}
