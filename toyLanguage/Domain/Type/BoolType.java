package Domain.Type;

import Domain.ICopy;

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
}
