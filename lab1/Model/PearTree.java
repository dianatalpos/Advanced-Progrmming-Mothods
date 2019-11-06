package Model;

public class PearTree implements Tree{
    private int age;

    public PearTree(int age)
    {
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String toString()
    {
        return "Pear tree " + String.valueOf(age) + "years";
    }

}
