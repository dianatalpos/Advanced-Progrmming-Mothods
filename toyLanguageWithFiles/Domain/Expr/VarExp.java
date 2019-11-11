package Domain.Expr;

import Domain.ADT.MyIDictionary;
import Domain.ICopy;
import Domain.Value.IValue;
import Errors.MyError;

public class VarExp implements Exp, ICopy<Exp> {
    String var;

    public VarExp(String id)
    {
        var = id;
    }

    public String toString()
    {
        return var;
    }
    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyError {
        return tbl.lookUp(var);
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(var);
    }
}
