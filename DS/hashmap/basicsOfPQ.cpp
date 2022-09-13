#include<vector>
#include<queue>
#include<iostream>

using namespace std;
void set1(vector<int>& arr)
{
    priority_queue<int>pq;          //maxPQ
    priority_queue<int,vector<int>,greater<int>> pq;        //minPQ

    for(int ele:arr)
    {
        pq.push(ele);
    }
    while(pq.size()!=0)
    {
        cout<<pq.top()<<" ";
        pq.pop();
    }
}