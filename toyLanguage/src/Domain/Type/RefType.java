package Domain.Type;

import Domain.Value.IValue;
import Domain.Value.RefValue;

public class RefType implements IType {

    IType inner;

    public RefType(IType innerr)
    {
        inner= innerr;
    }

    public RefType()
    {
    }

    public IType getInner()
    {
        return inner;
    }

    public boolean equals(Object another)
    {
        if ( another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else return false;
    }
    public String toString()
    {
        return "Ref " + inner.toString();
    }
    @Override
    public IValue defaultValue() {
        return new RefValue(inner, 0);
    }

    @Override
    public IType deepCopy() {
        return new RefType(inner.deepCopy());
    }
}
