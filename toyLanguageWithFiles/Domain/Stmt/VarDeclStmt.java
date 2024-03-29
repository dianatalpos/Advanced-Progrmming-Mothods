package Domain.Stmt;

import Domain.ADT.MyIDictionary;
import Domain.PrgState;
import Domain.Type.BoolType;
import Domain.Type.IType;
import Domain.Type.IntType;
import Domain.Type.StringType;
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
        if (typ.equals(new IntType()))
        {
            IType tp = new IntType();
            IValue val = tp.defaultValue();
            symb.put(name, val);
        }
        if (typ.equals(new BoolType()))
        {
            IType tp = new BoolType();
            IValue val2 = tp.defaultValue();
            symb.put(name, val2);
        }
        if(typ.equals(new StringType()))
        {
            IType tp = new StringType();
            IValue val2 = tp.defaultValue();
            symb.put(name, val2);
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, typ.deepCopy());
    }
}
