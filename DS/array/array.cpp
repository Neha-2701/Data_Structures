#include<iostream>
#include<vector>

using namespace std;

void segregatePositiveNegative(vector<int> &arr)
{
    if(arr.size()==0)
        return;
    int pivot=-1,idx=0,n=arr.size();
    while(idx<n)
    {
        if(arr[idx]>=0)
            swap(arr[++pivot],arr[idx]);
        idx++;
    }
}

void segregateOneTwo(vector<int> & arr)
{
    if(arr.size()==0)
        return;
    int pivot=-1,idx=0,n=arr.size();
    while(idx<n)
    {
        if(arr[idx]==0)
            swap(arr[++pivot],arr[idx]);
        idx++;   
    }
}

void segregateZeroOneTwo(vector<int> &arr)
{
    if(arr.size()==0)
        return;
    int p1=-1,idx=0,n=arr.size(),p2=n-1;
    while(idx<=p2)
    {
        if(arr[idx]==0)
            swap(arr[++p1],arr[idx++]);
        else if(arr[idx]==2)
            swap(arr[idx],arr[p2--]);
        else
            idx++;
    }
}

//Leetcode 3
int lengthOfLongestSubstring(string s) {
    if(s.length()<=1) return s.length();
    int n=s.length(),si=0,ei=0,count=0,len=0;
    vector<int>map(128,0);
    while(ei<n)
    {
        if(map[s[ei++]]++ > 0) count++;
        while(count>0) if(map[s[si++]]-->1) count--;
        len=max(len,ei-si);
    }
    return len;
}



int main()
{
    vector<int> arr{1,2,3,-5,-3,2,4};
    segregatePositiveNegative(arr);
    for(int i=0;i<arr.size();i++)
    {
        cout<<arr[i];
    }
    return 0;
}
