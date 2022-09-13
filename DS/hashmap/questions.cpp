#include<vector>
#include<queue>
using namespace std;

//leetcode 215
int findKthlargest(vector<int> &nums,int k)
{
    priority_queue<int> pq;
    for(int ele:nums)
    {
        pq.push(ele);
        if(pq.size()>k) pq.pop();
    }
    return pq.top();
}


