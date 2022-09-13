import javax.lang.model.util.ElementScanner14;

public class l007 {
    public static class linkedlist
    {
        private class Node{
            int data=0;
            Node next=null;
            Node(int data)
            {
                this.data=data;
            }
        }
        private Node head=null;
        private Node tail=null;
        private int elementcount=0;

        public boolean isempty()
        {
            return this.elementcount==0;
        }

        public int size()
        {
            return this.elementcount;
        }

        public void addFirstNode(Node node)
        {
            if(elementcount==0)
            {
                this.head=node;
                this.tail=node;
            }
            else
            {
                node.next=this.head;
                this.head=node;
            }
            this.elementcount++;
        }

        public void addFirst(int val)
        {
            Node node=new Node(val);
            addFirstNode(node); 
        }

        public void addLastNode(Node node)
        {
            if(elementcount==0)
            {
                this.head=node;
                this.tail=node;
            }
            else
            {
                this.tail.next=node;
                this.tail=node;
            }
            this.elementcount++;
        }

        public void addLast(int val)
        {
            Node node=new Node(val);
            addLastNode(node); 
        }

        public void addNodeAt(Node node,int idx)
        {
            if(idx==0)
            {
                addFirstNode(node);
            }
            else if(idx==this.elementcount)
            {
                addLastNode(node);
            }
            else
            {
                Node prev=getNodeat(idx-1);
                Node curr=prev.next;

                prev.next=node;
                node.next=curr;
            }
            this.elementcount++;
        }

        public void addAt(int data,int idx)throws Exception
        {
            if(idx<0||idx>=this.elementcount)
            {
                throw new Exception("invalidlocation");
            }
            Node node=getNodeat(data);
            addNodeAt(node, idx);
        }

        //-------------remove---------------

        public Node removeFirstNode()
        {
            Node node=this.head;
            if(this.elementcount==1)
            {
                this.head=null;
                this.tail=null;
            }
            else
            {
                this.head=this.head.next;
                node.next=null;
            }
            this.elementcount--;
            return node;
        }

        public int removeFirst(int idx) throws Exception
        {
            if(this.elementcount==0)
            {
                throw new Exception("nullpointerexception");
            }
            Node node=removeFirstNode();
            int rv=node.data;
            return rv;
        }

        

        public Node getNodeat(int idx)
        {
            Node curr=this.head;
            while(idx-->0)
            {
                curr=curr.next;
            }
            return curr;
        }

        public int getAt(int idx) throws Exception
        {
            if(idx<0||idx>=this.elementcount)
            {
                throw new Exception("invalidlocation");
            }
            Node node=getNodeat(idx);
            return node.data;
        }
    }
}
