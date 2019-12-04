package Domain.Stmt;

import Domain.ADT.IHeap;
import Domain.ADT.MyHeap;
import Domain.ADT.MyIDictionary;
import Domain.Expr.Exp;
import Domain.PrgState;
import Domain.Type.RefType;
import Domain.Value.IValue;
import Domain.Value.RefValue;
import Errors.MyError;

public class HeapWritingStmt implements IStmt {

    String var_name;
    Exp expression;

    public HeapWritingStmt(String given_var_name, Exp given_expression)
    {
        var_name = given_var_name;
        expression = given_expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIDictionary<String, IValue> symbols = state.getSymTbl();
        IHeap heap = state.getHeap();
        if(!(symbols.isDefined(var_name)))  throw new MyError("The variable is not defined!");
        IValue value = symbols.get(var_name);
        if(!(value.getType() instanceof RefType)) throw new MyError("The type of the variable is not a reference type!");
        RefValue cast_value= (RefValue)value;
        if(!(heap.isDefined(cast_value.getAddress()))) throw new MyError("The address doesn't exists!");
        IValue val = expression.eval(symbols, heap);
        RefType new_typeval = (RefType)cast_value.getType();
        if(!(val.getType().equals(new_typeval.getInner()))) throw new MyError("The type of the expression doen't match with the type of the variable!");
        heap.update(cast_value.getAddress(), val);
        return null;
    }
    public String toString()
    {
        return "wH( " + var_name + ", " + expression.toString() + ")";
    }
    @Override
    public IStmt deepCopy() {
        return new HeapWritingStmt(var_name, expression.deepCopy());
    }
}
