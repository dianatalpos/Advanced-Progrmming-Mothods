package Domain.Expr;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.Type.IntType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Errors.MyError;

public class RelationalExp implements Exp {
    Exp expr1;
    Exp expr2;
    String operand;

    public RelationalExp(Exp e1, Exp e2, String op)
    {
        expr1 = e1;
        expr2 = e2;
        operand = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap heap) throws MyError {
        IValue value1, value2;
        value1 = expr1.eval(tbl, heap);
        if(!value1.getType().equals(new IntType()))
            throw new MyError("First expression is not an integer!");
        value2 = expr2.eval(tbl, heap);
        if(!value2.getType().equals(new IntType()))
            throw new MyError("Second expression is not an integer!");
        IntValue cast_value1, cast_value2;
        cast_value1 = (IntValue)value1;
        cast_value2 = (IntValue)value2;
        int v1,v2;
        v1 = cast_value1.getVal();
        v2 = cast_value2.getVal();
        switch (operand)
        {
            case "<":
                return new BoolValue(v1<v2);
            case "<=":
                return new BoolValue(v1<=v2);
            case "==":
                return new BoolValue(v1==v2);
            case "!=":
                return new BoolValue(v1!=v2);
            case ">":
                return new BoolValue(v1>v2);
            case ">=":
                return new BoolValue(v1>=v2);
            default:
                return null;
        }
    }

    public String toString()
    {
        return expr1.toString() + operand + expr2.toString();
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(expr1.deepCopy(), expr2.deepCopy(), operand);
    }
}
