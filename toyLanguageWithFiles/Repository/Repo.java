package Repository;

import java.io.*;
import java.util.Iterator;
import Domain.ADT.MyIDictionary;
import Domain.ADT.MyIList;
import Domain.ADT.MyIStack;
import Domain.ADT.MyList;
import Domain.PrgState;
import Domain.Stmt.IStmt;
import Domain.Value.IValue;
import Errors.MyError;

import java.util.Iterator;

public class Repo implements IRepo {
    int currentPrg;
    MyIList<PrgState> programs;
    String log_path;
    public Repo(PrgState prg, String path)
    {
        currentPrg = 0;
        programs = new MyList<PrgState>();
        log_path = path;
        programs.add(prg);
        try
        {
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();

        }
        catch (IOException e)
        {

        }
    }


    @Override
    public PrgState getCrtPrg() {
        return programs.get(currentPrg);
    }

    @Override
    public void logPrgStateExec() throws MyError {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(log_path, true)));
        }
        catch(IOException e)
        {
            throw new MyError("File doesn't exist or cannot be open!");
        }
        logFile.print("ExeStack:\n");
        MyIStack<IStmt> stk = programs.get(currentPrg).getExeStack();
        logFile.print(stk.toString());
        logFile.print("\n");
        logFile.print("SymTable:");
        MyIDictionary<String, IValue> dict = programs.get(currentPrg).getSymTbl();
        String result = dict.toString();
        logFile.print("\n");
        logFile.print(result);
        logFile.print("\n");
        logFile.print("Output:\n");
        MyIList<IValue> out = programs.get(currentPrg).getOutList();
        logFile.print(out.toString());
        logFile.print("\n");
        MyIDictionary<String, BufferedReader> files = programs.get(currentPrg).getFileTable();
        logFile.print(files.toString());
        logFile.print('\n');
        logFile.print("\n");
        logFile.close();
    }

    public void addPrg(PrgState prg)
    {
        programs.add(prg);
    }
}
