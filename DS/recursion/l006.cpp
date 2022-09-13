#include<iostream>
#include<vector>
using namespace std;

int offToON(int n,int k)
{
    int mask=(1<<k);
    return(n|mask);
}

int OnToOff(int n,int k)
{
    int mask=(~(1<<k));
    return (n&mask);
}

//leetcode-191
int hammingWeight_01(uint32_t n)
{
    //O(N)
    int count=0;
    for(int i=0;i<32;i++)
    {
        int mask=(1<<i);
        if((n&mask)!=0)
        {
            count++;
        }
    }
    return count;
}

int hammingWeight_02(uint32_t n)
{
    //greater than O(logN)
    int count=0;
    while(n!=0)
    {
        if((n&1)!=0)
        {
            count++;
        }
        n>>=1;
    }
    return count;
}

int hammingWeight_03(uint32_t n)
{
    //O(logN)
    int count=0;
    while(n!=0)
    {
        count++;
        n&=(n-1);
    }
    return count;
}

//leetcode-231
bool isPowerofTwo(int n)
{
    return n>0 && (n&(n-1))==0;
}

//leetcode-342
bool isPowerOfFour(int num)
{
    if((num>0) && (num&(num-1))==0)
    {
        int count=0;
        while(num!=1)
        {
            num>>=1;
            count++;
        }
        return (count&1)==0?true:false;
    }
    return false;
}

//leetcode-36
int singleNumber(vector<int>& nums)
{
    int ans=0;
    for(int ele:nums)
    {
        ans^=nums[ele];
    }
    return ans;
}

int rsb(int n)
{
    int mask=0;
    int i=0;
    while(n!=0)
    {
        mask=(1<<i);
        if((n&mask)==0)
        {
            i++;
        }
        else
        {
            break;
        }
    }
    return i;
}

int main()
{
    //cout<<offToON(5,1)<<endl;
    //cout<<OnToOff(5,0);
    //cout<<hammingWeight_01(5)<<endl;
    //cout<<hammingWeight_02(5)<<endl;
    //cout<<hammingWeight_03(5)<<endl;
    //cout<<isPowerofTwo(4);
    //cout<<isPowerOfFour(16);
    //vector<int> nums={2,3,4,3,4};
    //cout<<singleNumber(nums);
   // cout<<rsb(5);
   //int arr[6]={1, -2,3,4,5,6};
  // un(arr,6);
}