package Domain.Stmt;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.ADT.MyIStack;
import Domain.Expr.Exp;
import Domain.ICopy;
import Domain.PrgState;
import Domain.Type.IType;
import Domain.Value.IValue;
import Errors.MyError;

public class AssignStmt implements IStmt, ICopy<IStmt> {
    String id;
    Exp expr;

    public AssignStmt(String id1, Exp e)
    {
        id = id1;
        expr =e;
    }


    public String toString()
    {
        return id + "=" + expr.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIStack<IStmt> stk = state.getExeStack();
        IHeap heap = state.getHeap();
        MyIDictionary<String, IValue> dict = state.getSymTbl();
        IValue val = expr.eval(dict, heap);
        if (dict.isDefined(id))
        {
            IType tp = dict.get(id).getType();
            if(val.getType().equals(tp))
            {
                dict.update(id, val);
            }
            else
            {
                throw new MyError(" declared type of variable " + id + "and type of the assigned expression do not match" );
            }
        }
        else throw new MyError("The used variable " + id + " was not declared before!" );
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, expr.deepCopy());
    }
}
