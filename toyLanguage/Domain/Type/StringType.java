package Domain.Type;

import Domain.Value.IValue;
import Domain.Value.StringValue;

public class StringType implements IType {

    public boolean equals(Object another)
    {
         return (another instanceof StringType);
    }

    @Override
    public IValue defaultValue() {
        return new StringValue();
    }

    public String toString()
    {
        return "String";
    }

    @Override
    public IType deepCopy() {
        return new StringType();
    }
}
