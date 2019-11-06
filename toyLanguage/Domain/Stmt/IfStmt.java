package Domain.Stmt;

import Domain.ADT.MyIDictionary;
import Domain.ADT.MyIStack;
import Domain.Expr.Exp;
import Domain.PrgState;
import Domain.Type.BoolType;
import Domain.Type.IType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Errors.MyError;

public class IfStmt implements IStmt{

    Exp expr;
    IStmt thenS;
    IStmt elseS;
    public IfStmt(Exp exp, IStmt th, IStmt el)
    {
        expr = exp;
        thenS = th;
        elseS = el;
    }

    public String toString()
    {
        return "IF (" + expr.toString() + " ) THEN ( " + thenS.toString() + ") ELSE (" + elseS.toString() + " )";
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, IValue> symMap = state.getSymTbl();
        IValue cond = expr.eval(symMap);
        IType tp = cond.getType();
        if (tp.equals(new BoolType()))
        {
            BoolValue condCast = (BoolValue)cond;
            if (condCast.getVal())
            {
                stk.push(thenS);
            }
            else stk.push(elseS);
        }
        else throw new MyError("Conditional expression is not a boolean!");
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(expr.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
