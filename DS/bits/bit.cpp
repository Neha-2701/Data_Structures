#include<iostream>
using namespace std;

//-------------find wheather a no is odd or even-----------

void oddeven(int n)
{
    if((n&1)==0)
    {
        cout<<"even";
    }
    else
    {
        cout<<"odd";
    }
}

//----------------to extract a particular bit--------------

int get(int n,int i)
{
    int mask=(1<<i);
    int ans=(n&mask);
    return ans>0?1:0;
}

//----------------to change a particular bit----------------

int set(int n,int i)
{
    int mask=(1<<i);
    int ans=n|mask;
    return ans;
}

//-------------------to clear a bit--------------------------

int clear(int n,int i)
{
    int mask=(1<<i);
    mask=~mask;
    int ans=n&mask;
    return ans;
}

//-----------------to clear a range of bits-------------------

int range(int n,int i,int j)
{
    int a=(~0)<<(j+1);
    int b=(1<<i)-1;
    int mask=a|b;
    int ans=n&mask;
    return ans;
}

//----------------to replace N by M----------------------------

int replace(int M,int N,int i,int j)
{
    M=M<<i;
    int ans=N|M;
    return ans;
}

int main()
{
    //oddeven(6);
    //cout<<get(5,3);
    //cout<<set(5,1);
    //cout<<clear(5,2);
    //cout<<range(5,1,3);
    cout<<replace(128,21,2,6);
}
