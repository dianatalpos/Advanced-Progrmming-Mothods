package Domain.Value;

import Domain.ICopy;
import Domain.Type.IType;
import Domain.Type.IntType;

public class IntValue implements IValue, ICopy<IValue> {
    int val;

    public IntValue() { val = 0;}

    public IntValue(int i)
    {
        val = i;
    }

    public int getVal()
    {
        return val;
    }

    @Override
    public IType getType()
    {
        return new IntType();
    }

    public String toString()
    {
        return String.valueOf(val);
    }

    @Override
    public IntValue deepCopy() {
        return new IntValue(val);
    }
}