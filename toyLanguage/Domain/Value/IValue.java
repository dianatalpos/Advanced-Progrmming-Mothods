package Domain.Value;

import Domain.ICopy;
import Domain.Type.IType;

public interface IValue extends ICopy<IValue> {
    public IType getType();
}
