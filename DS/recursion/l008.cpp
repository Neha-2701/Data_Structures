#include <iostream>
using namespace std;

class linkedlist{
    class Node{
        public:
        int data=0;
        Node *next=nullptr;
        Node(int data)
        {
            this->data=data;
        }
    };
    Node *head=nullptr;
    Node *tail=nullptr;
    int elementcount=0;
    int size()
    {
        return this->elementcount;
    }
    bool isempty()
    {
        return this->elementcount;   
    }
    ///--------------add----------------
    private:
    void addFirstNode(Node *node)
    {
        if(this->head==nullptr)
        {
            this->head=node;
            this->tail=node;
        }
        node->next=this->head;
        this->head=node;
        this->elementcount++;
    }
    public:
    void addFirst(int val)
    {
        Node *node=new Node(val);
        addFirstNode(node);
    }

    private:
    void addLastNode(Node *node)
    {
        if(this->head==nullptr)
        {
            this->head=node;
            this->tail=node;
        }
        this->tail->next=node;
        this->tail=node;
        this->elementcount++;
    }
    public:
    void addLast(int val)
    {
        Node *node=new Node(val);
        addLastNode(node);
    }
    private:
    void addNodeAt(Node *node,int idx)
    {
        if(idx==0)
        {
            addFirstNode(node);
        }
        if(idx==elementcount)
        {
            addLastNode(node);
        }
        Node *prev=getNodeAt(idx-1);
        Node *curr=prev->next;
        prev->next=node;
        node->next=curr;
        this->elementcount++;
    }
    public:
    void addAt(int data,int idx)
    {
        if(idx<0||idx>=elementcount)
        {
            throw ("invalid index");
        }
        Node *node=new Node(data);
        addNodeAt(node,idx);
    }

    //----------------remove--------------
    private:
    Node *removeFirstNode()
    {
        Node *node=this->head;
        if(this->elementcount==1)
        {
            this->head=nullptr;
            this->tail=nullptr;
        }
        else
        {
            this->head=this->head->next;
            node->next=nullptr;
        }
        this->elementcount--;
        return node;
    }
    public:
    int removeFirst(int val)
    {
        if(this->elementcount==0)
        {
            throw ("invalid");
        }
        Node *node=removeFirstNode();
        int rv=node->data;
        delete node;
        return rv;
    }

    private:
    Node *removeLastNode()
    {
        Node *node=this->head;
        if(this->elementcount==1)
        {
            this->head=nullptr;
            this->tail=nullptr;
        }
        else
        {
            Node *prev=getNodeAt(elementcount-2);
            this->tail=nullptr;
            prev->next=nullptr;
        }
        this->elementcount--;
        return node;
    }
    public:
    int removeLast(int val)
    {
        if(this->elementcount==0)
        {
            throw ("invalid");
        }
        Node *node=removeLastNode();
        int rv=node->data;
        delete node;
        return rv;
    }

    private:
    Node *removeNodeAt(int idx)
    {
        if(idx==0)
        {
            return removeFirstNode();
        }
        if(idx==elementcount-1)
        {
            return removeLastNode();
        }
        else
        {
            Node *prev=getNodeAt(idx-1);
            Node *curr=prev->next;
            
            prev->next=curr->next;
            curr->next=nullptr;
            this->elementcount--;
            return curr;
        }
    }
    public:
    int removeAt(int idx)
    {
        if(this->elementcount==0)
        {
            throw ("invalid");
        }
        Node *node=removeNodeAt(idx);
        int rv=node->data;
        delete node;
        return rv;
    }

    //----------------get-----------------
    private:
    Node* getFirstNode()
    {
        return this->head;
    }
    public:
    int getFirst()
    {
        if(this->elementcount==0)
        {
            throw ("invalid");
        }
        Node *node=getFirstNode();
        return node->data;
    }

    private:
    Node* getLastNode()
    {
        return this->tail;
    }
    public:
    int getLast()
    {
        if(this->elementcount==0)
        {
            throw ("invalid");
        }
        Node *node=getLastNode();
        return node->data;
    }
    private:
    Node* getNodeAt(int idx)
    {
        Node *curr=this->head;
        while (idx-->0)
        {
            curr=curr->next;
        }
        return curr;
    }
    public:
    int getAt(int idx)
    {
        if(idx==0||idx>=this->elementcount)
        {
            throw("invalid index");
        }
        Node *node=getNodeAt(idx);
        return node->data;
    }
};