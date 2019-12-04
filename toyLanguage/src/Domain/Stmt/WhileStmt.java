package Domain.Stmt;

import Domain.ADT.IHeap;
import Domain.ADT.MyIDictionary;
import Domain.ADT.MyIStack;
import Domain.Expr.Exp;
import Domain.PrgState;
import Domain.Type.BoolType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Errors.MyError;

public class WhileStmt implements IStmt {
    Exp condition;
    IStmt statements;

    public WhileStmt(Exp cond, IStmt states)
    {
        condition= cond;
        statements = states;
    }

    @Override
    public PrgState execute(PrgState state) throws MyError {
        MyIDictionary<String, IValue> symbols = state.getSymTbl();
        IHeap heap = state.getHeap();
        MyIStack<IStmt> stack = state.getExeStack();
        IValue val_cond = condition.eval(symbols, heap);
        if(!(val_cond.getType() instanceof BoolType)) throw new MyError("The value of the condition is not a boolean!");
        if(val_cond.equals(true))
        {
            stack.push(new WhileStmt(condition, statements));
            stack.push(statements);
        }
         return null;
    }

    public String toString()
    {
        return "while (" + condition.toString() + ") {" +  statements.toString() + "}";
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(condition, statements);
    }
}
