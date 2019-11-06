package Repository;

import Errors.MyErrors;
import Model.Tree;

public class arrayRepo implements Repository {
    private Tree[] objects;
    private int size;

    public arrayRepo(int capacity)
    {
        objects = new Tree[capacity];
        this.size = 0;
    }
    @Override
    public void add(Tree t) throws MyErrors {
        if(this.size == this.objects.length)
            throw new MyErrors("Too many elements!!");
        this.objects[this.size++] = t;
    }

    @Override
    public void delete(int i) throws MyErrors{
        if(i < 0 || i>this.size)
            throw new MyErrors("Invalid index!!");
        this.objects[i] = this.objects[size-1];
        this.size--;
    }
    @Override
    public Tree[] getContent() {

        Tree[] dup = new Tree[this.size];
        for(int i=0;i<size;i++)
            dup[i] = objects[i];
        return dup;
    }
}
