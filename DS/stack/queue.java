public class queue {
    private int[] arr;
    private int front;
    private int back;
    private int elementCount;

    private void initializeValues(int size)
    {
        this.arr=new int[size];
        this.front=0;
        this.back=0;
        this.elementCount=0;
    }

    public queue()
    {
        initializeValues(10);
    }
    public queue(int size)
    {
        initializeValues(size);
    }
    public int size()
    {
        return this.elementCount;
    }

    public boolean isEmpty()
    {
        return this.elementCount==0;
    }

    public int capacity()
    {
        return this.arr.length;
    }

    public void push(int data) throws Exception
    {
        if(this.size()==this.capacity())
        {
            throw new Exception("QueueIsFull");
        }
        this.arr[this.back]=data;
        this.back=(this.back+1)%this.capacity();
        this.elementCount++;
    }
    
    public int front() throws Exception
    {
        if(this.isEmpty())
        {
            throw new Exception("QueueIsEmpty");
        }
        return this.arr[this.front];
    }

    public int pop() throws Exception
    {
        if(this.isEmpty())
            throw new Exception("QueueIsEmpty");
        int rv=this.arr[this.front];
        this.arr[this.front]=0;
        this.front=(this.front+1)%this.capacity();
        this.elementCount--;

        return rv;
    }
}
