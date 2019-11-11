package View;

import Controller.Controller;
import Domain.ADT.*;
import Domain.Expr.ArithExp;
import Domain.Expr.RelationalExp;
import Domain.Expr.ValueExp;
import Domain.Expr.VarExp;
import Domain.PrgState;
import Domain.Stmt.*;
import Domain.Type.BoolType;
import Domain.Type.IntType;
import Domain.Type.StringType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Domain.Value.StringValue;
import Repository.IRepo;
import Repository.Repo;

import java.io.BufferedReader;

public class Interpreter {

    private static Controller makeController(IStmt ex)
    {

        String path = "C:\\Andrea\\semestrul III\\map\\toyLanguage\\repo";
        MyIStack<IStmt> exeS = new MyStack<IStmt>();
        MyIDictionary<String, IValue> symb = new MyDictionary<String, IValue>();
        MyIList<IValue> out = new MyList<IValue>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<String, BufferedReader>();
        PrgState prg = new PrgState(exeS, symb, out, fileTable,ex);
        IRepo repo1 = new Repo(prg, path);
        Controller ctr1 = new Controller(repo1);
        return ctr1;
    }
    public static void main(String[] args)
    {

        IStmt ex1 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));

        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                        ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                        new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
                                ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(
                                new IfStmt(new RelationalExp(new ValueExp(new IntValue(2)), new ValueExp(new IntValue(3)), ">"),
                                        new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(
                                                new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),  new CompStmt(new AssignStmt("varf",
                new ValueExp(new StringValue("test"))), new CompStmt(new OpenRStmt(new VarExp("varf")),
                new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                        new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRStmt(new VarExp("varf"))))) )  ))));

        Controller ctr1 = makeController(ex1);
        Controller ctr2 = makeController(ex2);
        Controller ctr3 = makeController(ex3);
        Controller ctr4 = makeController(ex4);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.show();
    }
}
