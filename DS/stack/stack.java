public class stack{
    private int[] arr;
    private int tos;
    private int elementCount;

    protected void initializeValues(int size)
    {
        this.arr=new int[size];
        this.tos=-1;
        this.elementCount=0;
    }

    public stack()
    {
        initializeValues(10);
    }

    public stack(int size)
    {
        initializeValues(size);
    }

    public int size()
    {
        return this.elementCount;
    }

    public boolean isEmpty()
    {
        return this.tos==-1;
    }

    public int capacity()
    {
        return this.arr.length;
    }

    public void push(int data) throws Exception
    {
        if(this.size()==this.capacity())
        {
            throw new Exception("StackIsFFull");
        }
        this.arr[++this.tos]=data;
        this.elementCount++;
    }

    public int peek() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("StackIsEmpty");
        }
        return this.arr[this.tos];
    }

    public int pop() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("StackIsEmpty");
        }
        int rv=this.arr[this.tos];
        this.arr[this.tos--]=0;
        this.elementCount--;

        return rv;
    }
}