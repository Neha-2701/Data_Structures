#include<iostream>
#include<vector>

using namespace std;

class stack{
private:
    vector<int>arr;
    int tos;
    int elementCount;

    void initializeValues(int size)
    {
        this->arr.resize(size,0);
        this->tos=-1;
        this->elementCount=0;
    }
public:
    stack()
    {
        initializeValues(10);
    }
    stack(int size)
    {
        initializeValues(size);
    }
    int size()
    {
        return this->elementCount;
    }
    bool isEmpty()
    {
        return this->elementCount==0;
    }
private:
    int capacity()
    {
        return this->arr.size();
    }
public:
    void push(int data)
    {
        this->arr[++this->tos]==data;
        this->elementCount++;
    }
    int peek()
    {
        return this->arr[this->tos];
    }
    int pop()
    {
        int rv=this->arr[this->tos];
        this->arr[this->tos--]=0;
        this->elementCount--;
        return rv;
    }
};

int main()
{
    stack st;
    st.push(10);
    cout<<st.peek()<<endl;
    return 0;
}