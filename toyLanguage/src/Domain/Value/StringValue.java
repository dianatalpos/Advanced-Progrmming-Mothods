package Domain.Value;

import Domain.Type.IType;
import Domain.Type.StringType;

public class StringValue implements  IValue{
    String val;


    public boolean equals(Object obj)
    {
        StringValue b = (StringValue)(obj);
        return val == b.getVal();
    }


    public StringValue(String s)
    {
        val =s;
    }

    public StringValue() { val="";}

    public String toString()
    {
        return val;
    }

    public String getVal() { return val;}
    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(val);
    }
}
