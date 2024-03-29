package View;

import Domain.ADT.*;
import Controller.Controller;
import Domain.Expr.ArithExp;
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
import Errors.MyError;
import Repository.IRepo;
import Repository.Repo;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class View {

    private static void menu() {
        System.out.println("Choose a program:\n");
        System.out.println("1.Program 1\n");
        System.out.println("2.Program 2\n");
        System.out.println("3.Program 3\n");
        System.out.println("0.Exit\n");
    }

    private static void subMenu() {
        System.out.println("Choose an option:\n");
        System.out.println("1.One step\n");
        System.out.println("2.All steps\n");
        System.out.println("0.Exit\n");
    }

    private static void executeProg(IStmt ex) {
        String path = "C:\\Andrea\\semestrul III\\map\\toyLanguage\\repo";
        MyIStack<IStmt> exeS = new MyStack<IStmt>();
        MyIDictionary<String, IValue> symb = new MyDictionary<String, IValue>();
        MyIList<IValue> out = new MyList<IValue>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<String, BufferedReader>();
        IHeap heap = new MyHeap();
        PrgState prg = new PrgState(heap,exeS, symb, out, fileTable, ex);
        IRepo repo = new Repo(prg, path);
        Controller ctr = new Controller(repo);
        while (!exeS.isEmpty()) {
            subMenu();
            int x;
            Scanner console = new Scanner(System.in);
            x = console.nextInt();
            if (x == 0) return;
            switch (x) {
                case 0:
                    break;
                case 1:
                    /*try {

                    } catch (MyError e) {
                        System.out.println(e.getMessage());
                    }*/
                    break;
                case 2:
                    System.out.println("Do you want to print all the steps?");
                    String s = "";
                    Scanner console2 = new Scanner(System.in);
                    s = console2.toString();
                    ctr.allSteps();
                    /*
                    MyIStack<IStmt> nexeS = new MyStack<IStmt>();
                    MyIDictionary<String, IValue> nsymb = new MyDictionary<String, IValue>();
                    MyIList<IValue> nout = new MyList<IValue>();
                    PrgState nprg = new PrgState(nexeS, nsymb, nout, prg.getOriginal());
                    IRepo nrepo = new Repo(nprg);
                    Controller nctr = new Controller(nrepo);
                    nctr.allSteps(1);
                    break;

                     */
            }

        }
    }
}
/*
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

        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),  new CompStmt(new AssignStmt("varf",
                new ValueExp(new StringValue("test"))), new CompStmt(new OpenRStmt(new VarExp("varf")),
                new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRStmt(new VarExp("varf"))))) )  ))));
        while (true)
        {
            menu();
            int x;
            Scanner console = new Scanner(System.in);
            x = console.nextInt();
            switch(x)
            {
                case 0:
                    exit(0);
                case 1:
                    executeProg(ex1);
                    break;
                case 2:
                    executeProg(ex2);
                    break;
                case 3:
                    executeProg(ex3);
                    break;
                case 4:
                    executeProg(ex4);
                    break;
                default:
                    System.out.println("Unavailable option!");
                    break;
            }
        }
    }
}

 */
