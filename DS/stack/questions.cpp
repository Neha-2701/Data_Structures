#include<iostream>
#include<vector>
#include<stack>

using namespace std;
void ngor(vector<int> arr)
{
    int n=arr.size();
    vector<int> ans(n,n);

    stack<int> st;
    for(int i=0;i<arr.size();i++)
    {
        if(st.size()!=0 && st.top()<arr[i])
        {
            ans[st.top()]=i;
            st.pop();
        }
        st.push(i);
        
    }
}

void ngol(vector<int> arr)
{
    int n=arr.size();
    vector<int> ans(n,-1);

    stack<int> st;
    for(int i=n-1;i>=0;i--)
    {
        if(st.size()!=0 && st.top()<arr[i])
        {
            ans[st.top()]=i;
            st.pop();
        }
        st.push(i);
    }
}

void nsor(vector<int> arr)
{
    int n=arr.size();
    vector<int> ans(n,n);

    stack<int> st;
    for(int i=0;i<arr.size();i++)
    {
        if(st.size()!=0 && st.top()>arr[i])
        {
            ans[st.top()]=i;
            st.pop();
        }
        st.push(i);
    }
}

void nsol(vector<int> arr)
{
    int n=arr.size();
    vector<int> ans(n,-1);

    stack<int> st;
    for(int i=n-1;i>=0;i--)
    {
        if(st.size()!=0 && st.top()>arr[i])
        {
            ans[st.top()]=i;
            st.pop();
        }
        st.push(i);
    }
}
//Leetcode 20
bool isValid(string s)
{
    stack<char> st;
    int n=s.length();

    for(int i=0;i<s.length();i++)
    {
        char ch=s[i];
        if(ch=='('|| ch=='[' || ch=='{') st.push(ch);
        else if(ch==')' && st.top()!='(') return false;
        else if(ch==']' && st.top()!='[') return false;
        else if (ch=='}' && st.top()!='{') return false;
        else st.pop();
    }
    return true;
}

//Leetcode 503
vector<int> nextGreaterElements(vector<int>& nums) {
    int n=nums.size();
    vector<int> ans(n,-1);
    stack<int> st;
    for(int i=0;i<2*n;i++)
    {
        while(st.size()!=0 && nums[st.top()]<nums[i%n])
        {
            ans[st.top()]=nums[i%n];
            st.pop();
        }
        if(i<n) st.push(i);
    }
    return ans;
}

//Leetcode 946
bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
    stack<int> st;
    int i=0;
    for(int ele : pushed)
    {
        st.push(ele);
        while(st.size()!=0 && st.top()==popped[i])
        {
            st.pop();
            i++;
        }
    }     
    return st.size()==0;
}

//Leetcode 1021
string removeOuterParentheses(string S) {
    string ans="";
    int count=0;
    for(int i=0;i<S.length();i++)
    {
        char ch=S[i];
        if(ch=='(' && count++>0)
            ans+=ch;
        else if(ch==')' && count-->1)
            ans+=ch;
    }
    return ans;
}

//Leetcode 1249
string minRemoveToMakeValid(string s) {
    stack<int>st;
    string ans="";
    for(int i=0;i<s.length();i++)
    {
        char ch=s[i];
        if(ch=='(') st.push(i);
        else if(ch==')')
        {
            if(st.size()!=0 ) st.pop();
            else s[i]='#';
        }
    }
    while(st.size()!=0)
    {
        s[st.top()]='#';
        st.pop();
    }
    for(int i=0;i<s.length();i++)
    {
        char ch=s[i];
        if(ch!='#')
            ans+=ch;
    }
    return ans;
}

//Leetcode 32
int longestValidParentheses(string s) {
    stack<int>st;
    st.push(-1);
    int len=0;
    for(int i=0;i<s.length();i++)
    {
        if(st.top()!=-1 && s[st.top()]=='(' && s[i]==')')
        {
            st.pop();
            len=max(len,i-st.top());
        }
        else
            st.push(i);
    }
    return len;
}

//Leetcode 735
vector<int> asteroidCollision(vector<int>& arr) {
    stack<int>st;
    for(int ele:arr)
    {
        if(ele>0) st.push(ele);
        else
        {
            while(st.size()!=0 && st.top()>0 && st.top()<-ele)
                st.pop();
            if(st.size()!=0 && st.top()==-ele)
                st.pop();
            else if(st.size()==0 || st.top()<0)
                st.push(ele);
            else
            {
                //negative astroid will destroy
            }
        }
    }
    vector<int>ans(st.size(),0);
    for(int i=st.size()-1;i>=0;i--)
        ans[i]=st.top(),st.pop();
    return ans;
}

//Leetcode 901

class StockSpanner {
public:
    stack<pair<int,int>> st;
    int idx=0;

    StockSpanner() {
        st.push({-1,-1});        
    }
    
    int next(int price)
    {
        while(st.top().first!=-1 && st.top().second<=price)
            st.pop();
        int span=idx-st.top().first;
        st.push({idx++,price});
        return span;
    }
};

//Leetcode 84
int largestRectangleArea(vector<int>& heights) {
    int n=heights.size();
    stack<int>st;
    vector<int>left=nsoll(heights);
    vector<int>right=nsorr(heights);
    
    int area=0;
    for(int i=0;i<n;i++)
    {
        int h=heights[i];
        int w=right[i]-left[i]-1;
        area=max(area,h*w);
    }
    return area;
}
vector<int> nsorr(vector<int> & arr)
{
    int n=arr.size();
    vector<int> ans(n,n);
    stack<int> st;
    for(int i=0;i<n;i++)
    {
        while(st.size()!=0 && arr[st.top()]>arr[i])
        {
            ans[st.top()]=i;
            st.pop();
        }
        st.push(i);
    }
    return ans;
}
vector<int> nsoll(vector<int> & arr)
{
    int n=arr.size();
    vector<int> ans(n,-1);
    stack<int> st;
    for(int i=n-1;i>=0;i--)
    {
        while(st.size()!=0 && arr[st.top()]>arr[i])
        {
            ans[st.top()]=i;
            st.pop();
        }
        st.push(i);
    }
    return ans;
}

//Leetcode 85
int maximalRectangle(vector<vector<char>>& matrix) {
    if(matrix.size()==0 || matrix[0].size()==0) return;
    int n=matrix.size();
    int m=matrix[0].size();
    vector<int> arr(m,0);

    int area=0;
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<m;j++)
        {
            arr[j]=matrix[i][j]== '1' ? arr[j] : 0;
        }
        area=max(largestRectangleArea(arr),area);
    }        
    return area;
}

//alternative to question 84 to impress interviewer
int largestRectangleArea(vector<int> & heights)
{
    int n=heights.size();
    stack <int> st;
    st.push(-1);
    int area=0;
    for(int i=0;i<heights.size();i++)
    {
        while(st.top()!=-1 && heights[st.top()]>=heights[i])
        {
            int h=heights[st.top()];
            st.pop();
            int w=i-st.top()-1;
            area=max(area,h*w);
        }
        st.push(i);
    }
    while(st.size()!=1)
    {
        int h=heights[st.top()];
        st.pop();
        int w=n-st.top()-1;
        area=max(area,h*w);
    }
    return area;
}

//Leetcode 155
class MinStack
{
public:
    stack<long>st;
    long minEle=0;
    MinStack()
    {
    }
    void push(int x)
    {
        if(st.size()==0)
        {
            st.push(x);
            minEle=x;
            return;
        }
        if(x>=minEle) 
            st.push(x);
        else
        {
            st.push((x-minEle)+x);
            minEle=x;
        }
    }
    void pop()
    {
        if(st.top()<minEle)
            minEle=(minEle-st.top())+minEle;
        st.pop();
    }
    int top()
    {
        if(st.top()<minEle) return (int)minEle;
        return (int)st.top();
    }
    int getMin()
    {
        return (int)minEle;
    }
};

//Leetcode 402
string removeKdigits(string num, int k) {
    vector<char>st;
        
    for(int i=0;i<num.length();i++)
    {
        while(st.size()!=0 && k>0 && st.back()>num[i])
        {
            k--;
            st.pop_back();
        }
        if(st.size()==0 && num[i]=='0')
            continue;
        st.push_back(num[i]);
    }
    while(st.size()!=0 && k!=0)
    {
        st.pop_back();
        k--;
    }
    string ans="";
    for(int i=0;i<st.size();i++)
    {
        ans+=st[i];
    }
    return ans.length()==0?"0":ans;        
}

//Leetcode 316
string removeDuplicateLetters(string s) {

    if(s.length()==0) return s;
    vector<int> freq(26,0);
    vector<bool> vis(26,false);

    for(char ch:s)
        freq[ch-'a']++;

    vector<char> st;
    for(char ch: s)
    {
        freq[ch-'a']--;
        if(vis[ch-'a']) continue;
        while(st.size()!=0 && st.back()>ch && freq[st.back()-'a']>0)
        {
            vis[st.back()-'a']=false;
            st.pop_back();
        }
        vis[ch-'a']=true;
        st.push_back(ch);
    }
    string ans="";
    for(char ch: st) ans+=ch;
    return ans;
}

//Leetcode 42
int trap1(vector<int>& height) {
    int n=height.size();
    vector<int> left(n,0);
    vector<int> right(n,0);

    int prev=0;
    for(int i=0;i<n;i++)
    {
        left[i]=max(prev,height[i]);
        prev=left[i];
    } 

    int prev=0;
    for(int i=0;i<n;i++)
    {
        right[i]=max(prev,height[i]);
        prev=right[i];
    } 

    int water=0;
    for(int i=0;i<n;i++)
    {
        water+=min(left[i],right[i]) - height[i];
    } 
    return water;
}

int trap1(vector<int> & height)
{
    int n=height.size();
    stack<int> st;
    int water=0;
    for(int i=0;i<n;i++)
    {
        while(st.size()!=0 && height[st.top()]<=height[i])
        {
            int h=height[st.top()];
            st.pop();

            if(st.size(()==0) break;

            int w=i-st.top()-1;
            water+=w*(min(height[st.top()],height[i])-h);
        }
        st.push(i);
    }
    return water; 
}

int trap(vector<int> & height)
{
    int n=height.size();
    int water=0,i=0,j=n-2,lmax=0,rmax=0;
    while(i<j)
    {
        lmax=max(lmax,height[i]);
        rmax=max(rmax,height[j]);

        if(lmax<=rmax) water+=lmax-height[i++];
        else water+=rmax-height[j--];

        //water+=lmax<=rmax?lmax-height[i++]:rmax-height[j--];
    }
    return water;
}



