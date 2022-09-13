#include<iostream> 
#include<vector>
#include<string>

using namespace std;
vector<vector<int>> res;
vector<int> smallAns;

///---------permutation of a string---------

int permutation(string str,string ans)
{
    if(str.length()==0)
    {
        cout<<ans<<" ";
        return 1;
    }
    int count=0;
    for(int i=0;i<str.length();i++)
    {
        char ch=str[i];
        string ros=str.substr(0,i)+str.substr(i+1);
        count+=permutation(ros,ans+ch);  
    }
    return count;
}

///---------unique permutation of a string---------

int permutation_unique(string str,string ans)
{
    if(str.length()==0)
    {
        cout<<ans<<" ";
        return 1;
    }
    int count=0;
    vector<bool>vis(26,0);
    for(int i=0;i<str.length();i++)
    {
        char ch=str[i];
        if(!vis[ch-'a'])
        {
            vis[ch-'a']=true;
            string ros=str.substr(0,i)+str.substr(i+1);
            count+=permutation_unique(ros,ans+ch);
        }
    }
    return count;
}

///----------floodfill------------

vector<vector<int>> vis;
vector<vector<int>> dir{{1,0},{0,-1},{-1,0},{0,1}};
vector<string> direction{"D","L","U","R"};  
                                            
int floodfill(int sr,int sc,int er,int ec,string ans)
{
    if(sr==er && sc==ec)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    vis[sr][sc]=1;
    for(int d=0;d<dir.size();d++)
    {
        int r=sr+dir[d][0];
        int c=sc+dir[d][1];

        if(r>=0 && c>=0 && r<vis.size() && c<vis[0].size() && vis[r][c]==0)
        {
            count+=floodfill(r,c,er,ec,ans+direction[d]);
        }
    }
    vis[sr][sc]=0;
    return count;
}

///----------floodfill-8-directions------------
//vector<vector<int>> vis;
vector<vector<int>> dir8{{1,0},{0,-1},{-1,0},{0,1},{1,1},{-1,-1},{-1,1},{1,-1}};
vector<string> direction8{"D","L","U","R","S","N","E","W"};  
                                            
int floodfill_8(int sr,int sc,int er,int ec,string ans)
{
    if(sr==er && sc==ec)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    vis[sr][sc]=1;
    for(int d=0;d<dir8.size();d++)
    {
        int r=sr+dir8[d][0];
        int c=sc+dir8[d][1];

        if(r>=0 && c>=0 && r<vis.size() && c<vis[0].size() && vis[r][c]==0)
        {
            count+=floodfill_8(r,c,er,ec,ans+direction8[d]);
        }
    }
    vis[sr][sc]=0;
    return count;
}

///----------floodfill-with-multiple-jump---------

//vector<vector<int>> vis;
//vector<vector<int>> dir{{1,0},{0,-1},{-1,0},{0,1},{1,1},{-1,-1},{-1,1},{1,-1}};
//vector<string> direction{"D","L","U","R","S","N","E","W"}; 
int floodfill_jump(int sr,int sc,int er,int ec,string ans)
{
    if(sr==er&& sc==ec)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=1;
    vis[sr][sc]=0;
    for(int jump=0;jump<=max(er,ec);jump++)
    {
        for(int d=0;d<dir8.size();d++)
        {
            int r=sr+jump*dir8[d][0];
            int c=sc+jump*dir8[d][1];

            if(r>=0 && c>=0 && r<vis.size() && c<vis[0].size() && vis[r][c]==0)
            {
                count+=floodfill(r,c,er,ec,ans+direction8[d]+to_string(jump));
            }
        }
        vis[sr][sc]=0;
    }
    return count;
}

///------------------find-shortest-path-in-floodfill-problem------------------

class pathPair
{
    public:
    string path;
    int len;
    pathPair(string path,int len)
    {
        this->path=path;
        this->len=len;
    }
};
pathPair floodshortestPath_jump(int sr,int sc,int er,int ec)
{
    if(sr==er &&  sc==ec)
    {
        pathPair p("",0);
        return p;
    }
    vis[sr][sc]=1;
    pathPair ans("",1e8);
    for(int jump=1;jump<=max(er,ec);jump++)
    {
        for(int d=0;d<dir.size();d++)
        {
            int r=sr+jump*dir[d][0];
            int c=sc+jump*dir[d][1];

            if(r>=0 && c>=0 && r<vis.size() && c<vis[0].size() && vis[r][c]==0)
            {
                pathPair recAns =floodshortestPath_jump(r,c,er,ec);
                if(recAns.len+1<ans.len)
                {
                    ans.len=recAns.len+1;
                    ans.path=direction8[d] + to_string(jump) + " " + recAns.path;
                }
            }
        }
    }
    vis[sr][sc]=0;
    return ans;
}

///------------------find-longest-path-in-floodfill-problem------------------

pathPair floodlongestPath_jump(int sr,int sc,int er,int ec)
{
    if(sr==er &&  sc==ec)
    {
        pathPair p("",0);
        return p;
    }
    vis[sr][sc]=1;
    pathPair ans("",-1e8);
    for(int jump=1;jump<=max(er,ec);jump++)
    {
        for(int d=0;d<dir.size();d++)
        {
            int r=sr+jump*dir[d][0];
            int c=sc+jump*dir[d][1];

            if(r>=0 && c>=0 && r<vis.size() && c<vis[0].size() && vis[r][c]==0)
            {
                pathPair recAns =floodlongestPath_jump(r,c,er,ec);
                if(recAns.len+1>ans.len)
                {
                    ans.len=recAns.len+1;
                    ans.path=direction8[d] + to_string(jump) + " " + recAns.path;
                }
            }
        }
    }
    vis[sr][sc]=0;
    return ans;
}

int main()
{
    //string str="124510";
    //cout<<encoding(str,0,"")<<endl;
    //hello();
    //cout<<permutation("abc","");
    //cout<<permutation_unique("aba","");
    //vector<int>nums{1,2,3};
    //permute(nums);
    int n=3;
    int m=3;
    vis.resize(n,vector<int>(m,0));
    //cout<<floodfill(0,0,2,2,"");
    //cout<<floodfill_8(0,0,2,2,"");
    //cout<<floodfill_jump(0,0,2,2,"");
    //pathPair ans=floodshortestPath_jump(0,0,2,2);
    pathPair ans=floodlongestPath_jump(0,0,2,2);
    cout<<ans.path<<"->"<<ans.len;
    return 0;
}