package Domain.Expr;

import Domain.ADT.MyIDictionary;
import Domain.ICopy;
import Domain.Value.IValue;
import Errors.MyError;

public interface Exp extends ICopy<Exp> {
    IValue eval(MyIDictionary<String, IValue> tbl) throws MyError;
}
