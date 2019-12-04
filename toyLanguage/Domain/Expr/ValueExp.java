package Domain.Expr;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.ICopy;
import Domain.Value.IValue;
import Errors.MyError;

public class ValueExp implements Exp, ICopy<Exp> {
    IValue e;

    public ValueExp(IValue e1)
    {
        e = e1;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap heap) throws MyError {
        return e;
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp( e.deepCopy());
    }
}
