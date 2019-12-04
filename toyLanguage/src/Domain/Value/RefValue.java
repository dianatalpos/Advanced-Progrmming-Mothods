package Domain.Value;

import Domain.Type.IType;
import Domain.Type.RefType;

public class RefValue implements IValue {

    int address;
    IType location;

/*
    public boolean equals(Object obj)
    {
    }
*/

    public RefValue(IType loc, int addr)
    {
        address = addr;
        location = loc;
    }
    public String toString()
    {
        return address + "--"+ location.toString();
    }
    public int getAddress()
    {
        return address;
    }

    @Override
    public IType getType() {
        return new RefType(location);
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(location.deepCopy(), address);
    }


}
