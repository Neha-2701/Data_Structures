public class linkedlist{
    private class Node{
        int data=0;
        Node next=null;
        Node(int data){
            this.data=data;
        }
    }   
    private Node head=null;
    private Node tail=null;
    private int elementCount=0;
    
    public int size(){
        return this.elementCount;
    }

    //ADD
    protected void addFirstNode(Node node){
        if(elementCount==0)
        {
            this.head=node;
            this.tail=node;
        }
        node.next=this.head;
        this.head=node;
        this.elementCount++;
    }
    public void addFirst(int val){
        Node node=new Node(val);
        addFirstNode(node);
    }

    protected void addLastNode(Node node){
        if(elementCount==0)
        {
            this.head=node;
            this.tail=node;
        }
        this.tail.next=node;
        this.tail=node;
        this.elementCount++;
    }
    public void addLast(int val){
        Node node=new Node(val);
        addLastNode(node);
    }

    protected void addNodeAt(Node node,int idx){
        if(idx==0){
            addFirstNode(node);
        }
        else if(idx==elementCount){
            addLastNode(node);
        }
        else{
            Node prev=getNodeAt(idx-1);
            Node curr=getNodeAt(idx);
            prev.next=node;
            node.next=curr;
        }
    }
    public void addAt(int data,int idx){
        Node node=new Node(data);
        addNodeAt(node,idx);
    }

    //Remove
    protected Node removeFirstNode(){
        Node curr=this.head;
        if(elementCount==1){
            this.head=null;
            this.tail=null;
        }
        this.head=curr.next;
        curr.next=null;
        elementCount--;
        return curr;
    }
    public int removeFirst() throws Exception{
        if(elementCount==0){
            throw new Exception("error");
        }
        Node node=removeFirstNode();
        return node.data;
    }

    protected Node removeLastNode(){
        Node curr=this.tail;
        if(elementCount==1){
            this.head=null;
            this.tail=null;
        }
        Node prev=getNodeAt(this.elementCount-2);
        this.tail=prev;
        prev.next=null;
        elementCount--;
        return curr;
    }
    public int removeLast() throws Exception{
        if(elementCount==0){
            throw new Exception("error");
        }
        Node node=removeLastNode();
        return node.data;
    }

    protected Node removeNodeAt(int idx){
        if(idx==0) removeFirstNode();
        if(idx==elementCount) removeLastNode();
        Node prev=getNodeAt(idx-1);
        Node curr=prev.next;
        prev.next=curr.next;
        curr.next=null;
        return curr;
    }

    public int removeAt(int idx) throws Exception{
        if(idx<0|| idx>=elementCount){
            throw new Exception("error");
        }
        Node node=removeNodeAt(idx);
        return node.data;
    }

    //GET
    protected Node getFirstNode(){
        return this.head;
    }
    public int getFirst(){
        Node node=getFirstNode();
        return node.data;
    }

    protected Node getLastNode(){
        return this.tail;
    }
    public int getLast(){
        Node node=getLastNode();
        return node.data;
    }

    protected Node getNodeAt(int idx){
        Node curr=this.head;
        while(idx-->0){
            curr=curr.next;
        }
        return curr;
    }
    public int getAt(int idx) throws Exception{
        if(idx<0 || idx>=this.elementCount){
            throw new Exception("Invalid location");
        }
        Node node=getNodeAt(idx);
        return node.data;  
    }
}