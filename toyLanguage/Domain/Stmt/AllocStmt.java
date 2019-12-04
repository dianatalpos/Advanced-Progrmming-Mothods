package Domain.Stmt;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.Expr.Exp;
import Domain.PrgState;
import Domain.Type.RefType;
import Domain.Value.IValue;
import Domain.Value.RefValue;
import Errors.MyError;

public class AllocStmt implements IStmt {
    String var_name;
    Exp expression;

    public AllocStmt(String var_name1, Exp expression1)
    {
        var_name = var_name1;
        expression = expression1;
    }
    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIDictionary<String, IValue>  symbols = state.getSymTbl();
        IHeap heap = state.getHeap();
        if (! symbols.isDefined(var_name))  throw new MyError("The variable is not defined!");
        IValue value = symbols.get(var_name);
        if (!( value.getType() instanceof  RefType)) throw new MyError("The type of the variable is not a reference type!");
        IValue val = expression.eval(symbols, heap);
        RefValue val_cast = (RefValue)value;
        if(val.getType().equals(val_cast.getType())) throw new MyError("Different types");
        heap.put(val);
        Integer addr = heap.getKey();
        RefValue v = new RefValue(val.getType(), addr);
        symbols.update(var_name, v);
        return null;
    }

    public String toString()
    {
        return "new (" + var_name + "," + expression.toString() + ")";
    }

    @Override

    public IStmt deepCopy() {
        return new AllocStmt(var_name, expression.deepCopy());
    }
}
