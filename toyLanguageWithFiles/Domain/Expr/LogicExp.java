package Domain.Expr;

import Domain.ADT.MyIDictionary;
import Domain.ICopy;
import Domain.Type.BoolType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Errors.MyError;

public class LogicExp implements Exp, ICopy<Exp> {
    Exp e1;
    Exp e2;
    int op; //1-&& , 2-||
    String opc;

    public LogicExp(String a, Exp ee1, Exp ee2)
    {
        opc = a;
        if (a == "and" )
            op = 1;
        if(a == "or")
            op=2;
        e1=ee1;
        e2=ee2;
    }

    @Override
    public String toString() {
        if (op == 1)
            return e1.toString() + " and " + e2.toString();
        else return e1.toString() + " or " + e2.toString();

    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyError {
        IValue v1,v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new BoolType()))
        {
            v2 = e2.eval(tbl);
            if(v2.getType().equals(new BoolType()))
            {
                BoolValue i1, i2;
                i1 = (BoolValue)v1;
                i2 = (BoolValue)v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                switch (op)
                {
                    case 1:
                        return new BoolValue(n1 && n2);
                    case 2:
                        return new BoolValue(n1 || n2);
                    default:
                        break;
                }
            }
            else throw new MyError("Second operand not a boolean value!!");
        }
        else throw new MyError("First operand not a boolean value!!");

        return null;
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(opc,e1.deepCopy(),e2.deepCopy() );
    }
}
