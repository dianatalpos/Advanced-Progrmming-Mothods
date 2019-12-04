package Domain.Stmt;

import Domain.ADT.MyIDictionary;
import Domain.PrgState;
import Domain.Type.*;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Errors.MyError;

public class VarDeclStmt implements IStmt {

    String name;
    IType typ;

    public VarDeclStmt(String nam, IType t)
    {
        name = nam;
        typ = t;
    }

    @Override
    public String toString() {
        return "(" +typ.toString() + " " + name + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIDictionary<String, IValue> symb = state.getSymTbl();
        if (symb.isDefined(name))
            throw new MyError("Variable already defined");
        IValue val2 = typ.defaultValue();
        symb.put(name, val2);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, typ.deepCopy());
    }
}
