package Domain.Expr;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.ICopy;
import Domain.Type.IntType;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Errors.MyError;

public class ArithExp implements Exp, ICopy<Exp>
{
    Exp e1;
    Exp e2;
    int op;//1-plus, 2-minus, 3-multiply, 4-divide
    char o;
    public ArithExp(char a, Exp ee1, Exp ee2){
        o = a;
        switch (a)
        {
            case ('+'):
            {
                op =1;
                break;
            }
            case '-':
                op=2;
                break;
            case '*':
                op=3;
                break;
            case '/':
                op=4;
                break;
            default:
                break;
        }
        e1 = ee1;
        e2=ee2;
    }

    @Override
    public String toString() {
        switch (op)
        {
            case (1):
            {
                return e1.toString() + "+" + e2.toString();
            }
            case 2:
                return e1.toString() + "-" + e2.toString();
            case 3:
                return e1.toString() + "*" + e2.toString();
            case 4:
                return e1.toString() + "/" + e2.toString();
            default:
                break;
        }
        return null;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap heap) throws MyError {
        IValue v1, v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new IntType()))
        {
            v2 = e2.eval(tbl, heap);
            if(v2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                switch (op)
                {
                    case 1:
                        return new IntValue(n1+n2);
                    case 2:
                        return new IntValue(n1-n2);
                    case 3:
                        return new IntValue(n1*n2);
                    case 4:
                        if(n2== 0)
                        {
                            throw new MyError("Division by 0!!");
                        }
                        else return new IntValue(n1/n2);
                }
            }
            else
                throw new MyError("Second operand is not an integer!");
        }
        else throw new MyError("First operant is not an integer!");

        return null;
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(o, e1.deepCopy(), e2.deepCopy());
    }
}
