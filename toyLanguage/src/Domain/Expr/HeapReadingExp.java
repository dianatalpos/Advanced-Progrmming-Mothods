package Domain.Expr;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.Value.IValue;
import Domain.Value.RefValue;
import Errors.MyError;

public class HeapReadingExp implements Exp {
    Exp expression;

    public HeapReadingExp(Exp given_expression)
    {
        expression = given_expression;
    }


    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, IHeap heap) throws MyError {

        IValue value = expression.eval(tbl, heap);
        if (!(value instanceof RefValue)) throw new MyError("The value is not a reference value");
        RefValue real_value = (RefValue) value;
        int address = real_value.getAddress();
        if(! heap.isDefined(address)) throw new MyError("The address doesn't exists!");
        IValue final_value = heap.get(address);
        return final_value;
    }

    public String toString()
    {
        return "rh( " + expression.toString() + " )";
    }


    @Override
    public Exp deepCopy() {
        return new HeapReadingExp(expression.deepCopy());
    }
}
