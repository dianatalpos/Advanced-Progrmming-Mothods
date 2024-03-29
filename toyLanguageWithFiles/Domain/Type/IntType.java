package Domain.Type;

import Domain.ICopy;
import Domain.Value.IValue;
import Domain.Value.IntValue;

public class IntType implements IType {

    //Ceva nu cred ca ii bine



    public boolean equals(Object another)
    {
        if(another instanceof IntType)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return "int";
    }

    @Override
    public IntType deepCopy() {
        return new IntType();
    }

    @Override
    public IValue defaultValue() {
        return new IntValue();
    }
}
