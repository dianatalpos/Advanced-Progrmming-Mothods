package View;

import Controller.Controller;
import Domain.ADT.*;
import Domain.Expr.*;
import Domain.PrgState;
import Domain.Stmt.*;
import Domain.Type.BoolType;
import Domain.Type.IntType;
import Domain.Type.RefType;
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
        IHeap heap = new MyHeap();
        PrgState prg = new PrgState(heap, exeS, symb, out, fileTable,ex);
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
        IStmt ex5 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new AllocStmt
                ("a", new ValueExp(new IntValue(5))), new CompStmt(new HeapWritingStmt("a",new  ValueExp(new IntValue(6))),new CompStmt(new VarDeclStmt("v", new RefType(new RefType(new IntType()))),
                new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("a"))), new NopStmt())))));
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new AllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new AllocStmt("a", new VarExp("v")),
                                        new CompStmt(new AllocStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))))))));
        /*
         int v; Ref int a; v=10;new(a,22);
 fork(wH(a,30);v=32;print(v);print(rH(a)));
 print(v);print(rH(a))
         */

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),new CompStmt(
                    new AllocStmt("a", new ValueExp(new IntValue(22))), new CompStmt( new ForkStmt(
                     new CompStmt(new HeapWritingStmt("a", new ValueExp(new IntValue(30))), new CompStmt(
                             new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")),
                             new CompStmt(
                             new PrintStmt(new HeapReadingExp(new VarExp("a"))), new CompStmt( new VarDeclStmt("c", new RefType(new RefType(new IntType()))),new AllocStmt("c", new VarExp("a")))))
                     ))
                    ), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))
                    ))));

        Controller ctr7 = makeController(ex7);
        Controller ctr5 = makeController(ex5);
        Controller ctr1 = makeController(ex1);
        Controller ctr2 = makeController(ex2);
        Controller ctr3 = makeController(ex3);
        Controller ctr4 = makeController(ex4);
        Controller ctr6 = makeController(ex6);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
        menu.show();
    }
}
