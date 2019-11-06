package Model;

public class AppleTree implements Tree{
    private int age;
    public AppleTree(int age){
        this.age = age;
    }
    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String toString()
    {
        return "Apple tree " + String.valueOf(age) + "years";
    }

}
