package Domain.Value;

import Domain.ICopy;
import Domain.Type.BoolType;
import Domain.Type.IType;

public class BoolValue implements IValue, ICopy<IValue> {
    private boolean val;

    public BoolValue()
    {
        val = false;
    }

    public BoolValue(boolean value)
    {
        val = value;
    }

    public boolean getVal()
    {
        return val;
    }
    @Override
    public IType getType() {
        return new BoolType();
    }

    public String toString()
    {
        return String.valueOf(val);
    }

    @Override
    public BoolValue deepCopy() {
        return new BoolValue(val);
    }
}
