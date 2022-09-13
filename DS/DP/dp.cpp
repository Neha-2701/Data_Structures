#include<iostream>
#include<vector>

using namespace std;
int fibonnaci_Memo(int n,vector<int> &dp)
{
    if(n<=1)
    {
        return dp[n]=n;
    }
    if(dp[n]!=0)
    {
        return dp[n];
    }
    int a=fibonnaci_Memo(n-1,dp);
    int b=fibonnaci_Memo(n-2,dp);
    return dp[n]=a+b;
}

int fibonnaci_Tabu(int N,vector<int> &dp)
{
    for(int n=0;n<N;n++)
    {
        if(n<=1)
        {
            dp[n]=n;
            continue;
        }
        
        int a=dp[n-1];
        int b=dp[n-2];
        dp[n]=a+b;
    }
    return dp[N];
}

int fibonnaci_opti(int N)
{
    int a=0;
    int b=1;
    for(int n=2;n<=N;n++)
    {
        int sum=a+b;
        a=b;
        b=sum;
    }
    return b;
}
void fibo()
{
    int n=8;
    vector<int> dp(n+1,0);
    cout<<fibonnaci_Memo(n,dp)<<endl;
    cout<<fibonnaci_Memo(8,dp)<<endl;
    cout<<fibonnaci_opti(8)<<endl;
}

int mazepath(int sr,int sc,int er,int ec,vector<vector<int>> &dp)
{
    if(sr==er && sc==ec)
    {
        return dp[sr][sc]=1;
    }
    if(dp[sr][sc]!=0)
    {
        return dp[sr][sc];
    }
    int count=0;
    if(sr+1<=er)
        count+=mazepath(sr+1,sc,er,ec,dp);
    if(sc+1<=ec)
        count+=mazepath(sr,sc+1,er,ec,dp);
    return dp[sr][sc]=count;
}

int mazepath_tabu(int SR,int SC,int er,int ec,vector<vector<int>> &dp)
{
    for(int sr=er;sr>=0;sr--)
    {
        for(int sc=ec;sc>=0;sc--)
        {
            if(sr==er && sc==ec)
            {
                dp[sr][sc]=1;
                continue;
            }
            int count=0;
            if(sr+1<=er)
                count+=dp[sr+1][sc];
            if(sc+1<=ec)
                count+=dp[sr][sc+1];
            dp[sr][sc]=count;
        }
    }
    return dp[SR][SC];
}

int mazepath_multiple(int sr,int sc,int er,int ec,vector<vector<int>> &dp)
{
    if(sr==er && sc==ec)
    {
        return dp[sr][sc]=1;
    }
    if(dp[sr][sc]!=0)
    {
        return dp[sr][sc];
    }
    int count=0;
    for(int jump=1;sr+jump<=er;jump++)
        count+=mazepath_multiple(sr+1,sc,er,ec,dp);
    for(int jump=1;sc+jump<=ec;jump++)
        count+=mazepath_multiple(sr,sc+1,er,ec,dp);
    for(int jump=1;sc+jump<=ec && sr+jump<=er;jump++)
        count+=mazepath_multiple(sr+1,sc+1,er,ec,dp);
    return dp[sr][sc]=count;
}


int mazepath_multiple_tabu(int SR,int SC,int er,int ec,vector<vector<int>> &dp)
{
    for(int sr=er;sr>=0;sr--)
    {
        for(int sc=ec;sc>=0;sc--)
        {
            if(sr==er && sc==ec)
            {
                dp[sr][sc]=1;
                continue;
            }
            int count=0;
            for(int jump=1;sr+jump<=er;jump++)
                count+=dp[sr+1][sc];
            for(int jump=1;sc+jump<=ec;jump++)
                count+=dp[sr][sc+1];
            for(int jump=1;sc+jump<=ec && sr+jump<=er;jump++)
                count+=dp[sr+1][sc+1];
            dp[sr][sc]=count;
        }
    }
    return dp[SR][SC];
}

void maze()
{
    int n=3;
    int m=3;
    vector<vector<int>> dp(n,vector<int>(m,0));
    //cout<<mazepath(0,0,2,2,dp);
    //cout<<mazepath_tabu(0,0,2,2,dp);
    cout<<mazepath_multiple(0,0,2,2,dp);
    cout<<mazepath_multiple_tabu(0,0,2,2,dp);
}

int minPathSum(int sr,int sc,int er,int ec,vector<vector<int>> &grid,vector<vector<int>> &dp)
{
    if(sr==er && sc==ec)
        return dp[sr][sc]=grid[sr][sc];
    
    if(dp[sr][sc]!=0)
        return dp[sr][sc];

    if(sr+1<=er)
        dp[sr][sc]=min(dp[sr][sc],minPathSum(sr+1,sc,er,ec,grid,dp));
    if(sc+1<=ec)
        dp[sr][sc]=min(dp[sr][sc],minPathSum(sr,sc+1,er,ec,grid,dp));
    
    return dp[sr][sc]+=grid[sr][sc];
}

int minPathSum_Tabo(int sr,int sc,int er,int ec,vector<vector<int>> &grid,vector<vector<int>> &dp)
{
    for(int sr=er;sr>=0;sr--)
    {
        for(int sc=ec;sc>=0;sc--)
        {
            if(sr==er && sc==ec)
            {   
                dp[sr][sc]=grid[sr][sc];
                continue;
            }
            if(sr+1<=er)
                dp[sr][sc]=min(dp[sr][sc],dp[sr+1][sc]);//min(dp[sr][sc],minPathSum(sr+1,sc,er,ec,grid,dp));
            if(sc+1<=ec)
                dp[sr][sc]=min(dp[sr][sc],dp[sr][sc+1]);//min(dp[sr][sc],minPathSum(sr,sc+1,er,ec,grid,dp));
    
            dp[sr][sc]+=grid[sr][sc];
        }
    }
    return dp[0][0];
}

int goldMine(int r,int c,int n,int m,vector<vector<int>> &arr,vector<vector<int>> &dp,vector<vector<int>>&dir)
{
    if(c==m-1)
        return dp[r][c]==arr[r][c];

    if(dp[r][c]!=0)
        return dp[r][c];

    int maxVal=0;
    for(int d=0;d<3;d++)
    {
        int x=r+dir[d][0];
        int y=c+dir[d][1];

        if(x>=0 && y>=0 && x<m && y<n)
        {
            maxVal=max(maxVal,goldMine(x,y,n,m,arr,dp,dir)+arr[r][c]);
        }
    }
    return dp[r][c]=maxVal;
}
int goldMineDP(int R,int C,int n,int m,vector<vector<int>>& arr,vector<vector<int>> &dp,vector<vector<int>> &dir)
{
    for(int c=C-1;c>=0;c--)
    {
        for(int r=R-1;r>=0;r--)
        {
            if(c==m-1)
            {
                dp[r][c]==arr[r][c];
                continue;
            }

            int maxVal=0;
            for(int d=0;d<3;d++)
            {
                int x=r+dir[d][0];
                int y=c+dir[d][1];

                if(x>=0 && y>=0 && x<m && y<n)
                {
                    maxVal=max(maxVal,dp[x][y]+arr[r][c]);
                }
            }
            dp[r][c]=maxVal;
        }
    }
    int maxVal=0;
    for(int r=0;r<n;r++)
        maxVal=max(maxVal,dp[r][0]);
}

int friendsPairing(int n,vector<int> &dp)
{
    if(n<=1)
        return dp[n]=1l;
    if(dp[n]!=0)
        return dp[n];
    int single=friendsPairing(n-1,dp);
    int pairUp=friendsPairing(n-2,dp);

    return dp[n]=single+pairUp;
}

int friendsPairingDP(int N,vector<int> &dp)
{
    for(int n=0;n<=N;n++)
    {
        if(n<=1)
        {
            dp[n]=1;
            continue;
        }
        int single=dp[n-1];
        int pairUp=dp[n-2]*(n-1);

        dp[n]=single+pairUp;
    }
    return dp[N];
}

int friendsPairing_Opti(int N,vector<int> & dp)
{
    int c=(int)1e9+7;
    for(int n=0;n<=N;n++)
    {
        if(n<=1)
        {
            dp[n]=1;
            continue;
        }
        dp[N]=(dp[n-1]%c+(dp[n-2]%c*(n-1)%c)%c)%c;
    }
    return dp[N];
}

//Leetcode 746
int minCostClimbingStairs(int n,vector<int> &cost,vector<int> &dp)
{
    if(n<=1) return dp[n]=cost[n];
    if(dp[n]!=0) return dp[n];

    int val=min(minCostClimbingStairs(n-1,cost,dp),minCostClimbingStairs(n-2,cost,dp));
    return dp[n]=val+((n<cost.size())?cost[n]:0);
}

int minCostClimbingStairs(vcetor<int>& cost)
{
    if(cost.size()==0) return 0;
    int n=cost.size();
    vector<int> dp(n+1,0);

    return minCostClimbingStairs(n,cost,dp);
}

int boardPath(int sp,int ep,vector<int> &dp)
{
    if(sp==ep) return dp[sp]=1;
    if(dp[sp]!=0) return dp[sp];

    int count=0;
    for(int dice=1;dice<=6;dice++)
        count+=boardPath(sp+dice,ep,dp);
    return dp[sp]=count;
}

int boardPathDP(int sp,int ep,vector<int> &dp)
{
    for(sp=ep;sp>=0;sp--)
    {
        if(sp==ep)
        {
            dp[sp]=1;
            continue;
        }
        int count=0;
        for(int dice=1;dice<=6;dice++)
            count+=dp[sp+dice];
        dp[sp]=count;
    }
    return dp[0];
}

int boardPath_Opti(int n)
{
    list<int>ll;

    for(int si=n;si>=0;si--)
    {
        if(ll.size()<=1)
            ll.push_front(1);
        else if(ll.size()<=6)
            ll.push_front(2*ll.front());
        else
        {
            ll.push_front(2*ll.front()-ll.back());
            ll.pop_back();
        }
    }
    return ll.front;
}

//Leetcode-91
int numDecodings(string &s,int idx,vector<int> &dp)
{
    if(idx==s.length()) return dp[idx]=1;
    if(dp[idx]!=-1) return dp[idx];
    char ch=s[idx];
    if(ch=='0') return dp[idx]=0;

    int count=0;
    count+=numDecodings(s,idx+1,dp);
    if(idx<s.length()-1)
    {
        int num=(s[idx]-'0')*10 + (s[idx+1]-'0');
        if(num<=26) count+=numDecodings(s,idx+2,dp);
    }
    return dp[idx]=count;
}

int numDecodings(string s)
{
    int n=s.length();
    if(n==0 || s[0]=='0') return 0;
    vector<int> dp(n+1,-1);
    return numDecodings(s,0,dp);
}

int numDecodingsDP(string &s,int idx,vector<int> &dp)
{
    for(idx=s.length();idx>=0;idx--)
    {
        if(idx==s.length())
        {
            dp[idx]=1;
            continue;
        }
        char ch=s[idx];
        if(ch=='0')
        {
            dp[idx]=0;
            continue;
        }
        int count=0;
        count+=dp[idx+1];
        if(idx<s.length()-1)
        {
            int num=(s[idx]-'0')*10+(s[idx+1]-'0');
            if(num<=26) 
                count+=dp[idx+2];
        }
        dp[idx]=count;
    }
    return dp[0];
}

int numDecodingsOpt(string &s)
{
    int a=1;
    int b=0;

    for(int idx=s.length()-1;idx>=0;idx--)
    {
        int sum=0;
        char ch=s[idx];
        if(ch=='0') sum=0;
        else
        {
            sum=a;
            if(idx<s.length()-1)
            {
                int num=(s[idx]-'0')*10 + (s[idx+1]-'0');
                if(num<=26) sum+=b;
            }
        }
        b=a;
        a=sum;
    }
    return a;
}

int main()
{
   
    //fibo();
    //maze();
    return 1;
}