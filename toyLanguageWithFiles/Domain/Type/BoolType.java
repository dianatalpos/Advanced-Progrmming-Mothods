package Domain.Type;

import Domain.ICopy;
import Domain.Value.BoolValue;
import Domain.Value.IValue;

public class BoolType implements IType {

    public boolean equals(Object elem)
    {
        return elem instanceof BoolType;
    }

    public String toString()
    {
        return "bool";
    }

    @Override
    public BoolType deepCopy() {
        return new BoolType();
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue();
    }
}
