package Domain.Type;

import Domain.ICopy;
import Domain.Value.IValue;

public interface IType extends ICopy<IType> {
    IValue defaultValue();

}
