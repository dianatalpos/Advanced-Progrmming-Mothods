package Domain.Stmt;

import Domain.ADT.MyIDictionary;
import Domain.Expr.Exp;
import Domain.PrgState;
import Domain.Type.IType;
import Domain.Type.StringType;
import Domain.Value.IValue;
import Domain.Value.StringValue;
import Errors.MyError;

import java.io.*;

public class OpenRStmt implements IStmt {

    Exp expr;

    public OpenRStmt(Exp expression)
    {
        expr = expression;
    }

    public String toString() {
        return "openRFile(" + expr.toString() + ");";
    }


    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIDictionary<String, BufferedReader> file_table = state.getFileTable();
        IValue value = expr.eval(state.getSymTbl());
        IType type = value.getType();
        if(type.equals(new StringType()))
        {
            StringValue cast_value =(StringValue)value;
            if(  !file_table.isDefined(cast_value.getVal()))
            {
                try
                {
                    BufferedReader file = new BufferedReader(new FileReader(cast_value.getVal()));
                    file_table.put(cast_value.getVal(), file);
                }
                catch(IOException e)
                {
                    throw new MyError("Something went wrong with opening the file!");
                }
            }
            else{
                throw new MyError("The file was open before!!");
            }

        }
        else{
            throw new MyError("Invalid type of operand");
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRStmt(expr.deepCopy());
    }
}
