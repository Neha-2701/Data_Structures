#include<iostream>
#include<vector>
using namespace std;

//Leetcode 46
vector<vector<int>> res;
vector<int> smallAns;

void permute_(vector<int> &nums,int count,vector<bool>vis)
{
    if(count==nums.size())
    {
        res.push_back(smallAns);
        return res;
    }
    for(int i=0;i<nums.size();i++)
    {
        if(!vis[i])
        {
            vis[i]=true;
            smallAns.push_back(nums[i]);
            permute_(nums,count+1,vis);
            smallAns.pop_back(nums[i]);
            vis[i]=false;
        }
    }
}
vector<vector<int>> permute(vector<int>& nums) {
    vector<bool> vis(nums.size(),false);
    permute_(nums,0,vis);
    return res;
}

//Leetcode 47
vector<vector<int>> res;
vector<int> smallAns;

void permute_(vector<int> &nums,int count,vector<bool>vis)
{
    if(count==nums.size())
    {
        res.push_back(smallAns);
        return res;
    }
    int prev=-(int) 1e9;
    for(int i=0;i<nums.size();i++)
    {
        if(!vis[i] && prev!=nums[i])
        {
            vis[i]=true;
            smallAns.push_back(nums[i]);
            permute_(nums,count+1,vis);
            smallAns.pop_back(nums[i]);
            vis[i]=false;
            prev=nums[i];
        }
    }
}
vector<vector<int>> permuteUnique(vector<int>& nums) {
    vector<bool> vis(nums.size(),false);
    permute_(nums,0,vis);
    return res;
}