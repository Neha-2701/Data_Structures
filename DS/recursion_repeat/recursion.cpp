#include<iostream>
#include<vector>

using namespace std;
void printDec(int n)
{
    if(n==0)
        return;
    cout<<n;
    printDec(n-1);
}

void printInc(int n)
{
    if(n==0)
        return;
    printInc(n-1);
    cout<<n;
}

void oddEven(int n)
{
    if(n==0)
        return;
    if(n%2!=0) cout<<n;
    oddEven(n-1);
    if(n%2==0) cout<<n;
}

int fact(int n)
{
    return n==0?1:fact(n-1)*n;
}

int pow(int a,int b)
{
    return b==0?1:pow(a,b-1)*a;
}

int powBtr(int a,int b)
{
    if(b==0) return 1;
    int ans=pow(a,b/2);
    ans*=ans;
    return (b%2==0)?ans:ans*a;
}

void display(vector<int> arr,int idx)
{
    if(idx==arr.size())
        return;
    cout<<arr[idx];
    display(arr,idx+1);
}

int maximum(vector<int> arr,int idx)
{
    if(idx==arr.size()) return -1;
    return max(arr[idx],maximum(arr,idx+1));
}

bool find(vector<int> arr,int idx,int data)
{
    if(idx==arr.size()) return false;
    if(arr[idx]==data) return true;
    return find(arr,idx+1,data);
}

int firstIndex(vector<int> arr,int idx,int data)
{
    if(idx==arr.size()) return -1;
    if(data==arr[idx]) return idx;
    return firstIndex(arr,idx+1,data);
}

int lastIndex_(vector<int> arr,int idx,int data)
{
    if(idx==0) return -1;
    if(data==arr[idx]) return idx;
    return lastIndex_(arr,idx-1,data);
}

int lastIndex(vector<int> arr,int idx,int data)
{
    if(idx==arr.size()) return -1;
    int ans=lastIndex(arr,idx+1,data);
    if(ans!=-1) return ans;
    return (data==arr[idx])?idx:-1;
}

vector<int> allIndices(vector<int> arr,int idx,int data,int count)
{
    if(idx==arr.size())
    {
        vector<int> baseArray(count,0);
        return baseArray;
    }
    if(arr[idx]==data) count++;
    vector<int> smallAns=allIndices(arr,idx+1,data,count);
    if(arr[idx]==data) smallAns[count-1]=idx;
    return smallAns;
}

void basics()
{
    //printDec(5);
    //printInc(5);
    //oddEven(5);
    //cout<<fact(5);
    //cout<<pow(2,5);
    //cout<<powBtr(2,4);
    //vector<int> arr{1,2,2,2,2,2,3};
    //display(arr,0);
    //cout<<maximum(arr,0);
    //cout<<find(arr,0,4);
    //cout<<firstIndex(arr,0,2);
    //cout<<lastIndex_(arr,arr.size()-1,3);
    //cout<<lastIndex(arr,0,3);
    //vector<int> ans=allIndices(arr,0,2,0);
}
int subsequence(string str,string ans)
{
    if(str.length()==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    count+=subsequence(str.substr(1),ans);
    count+=subsequence(str.substr(1),ans+str[0]);
    return count;
}

vector<string> subsequence(string str,int idx)
{
    if(idx==str.length())
    {
        vector<string>baseArray;
        baseArray.push_back("");
        return baseArray;
    }
    char ch=str[idx];
    vector<string> smallAns=subsequence(str,idx+1);
    vector<string> myAns;
    for(string s:smallAns)
    {
        myAns.push_back(s);
        myAns.push_back(ch+s);
    }
    return myAns;
}

vector<string> words={"!*(","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz","@#$"};
int keypad_combinations(string str,int idx,string ans)
{
    if(idx==str.length())
    {
        cout<<ans;
        return 1;
    }
    int count=0;
    int ch=str[idx]-'0';
    string word=words[ch];
    for(int i=0;i<word.length();i++) count+=keypad_combinations(str,idx+1,ans+word[i]);
    return count; 
}

vector<string> keypad(string str,int idx)
{
    if(idx==str.length())
    {
        vector<string> baseArray;
        baseArray.push_back("");
        return baseArray;
    }
    int ch=str[idx]-'0';
    string word=words[ch];
    vector<string> smallAns=keypad(str,idx+1);
    vector<string>myAns;
    for(string s:smallAns)
    {
        for(int i=0;i<word.length();i++)
        {
            myAns.push_back(word[i]+s);
        }
    }
    return myAns;
}

int keypad_modified(string str,int idx,string ans)
{
    if(idx==str.length())
    {
        cout<<ans;
        return 1;
    }
    int count=0;
    int ch=str[idx]-'0';
    string word=words[ch];
    for(int i=0;i<word.length();i++) count+=keypad_combinations(str,idx+1,ans+word[i]);
    if(idx<str.length()-1)
    {
        int ch1=ch*10+(str[idx+1]-'0');
        if(ch1>=10 && ch1<=11)
        {
            word=words[ch1];
            for(int i=0;i<word.length();i++)
                count+=keypad_modified(str,idx+2,ans+word[i]);
        }
    }
    return count;
}

int permutations(string str,string ans)
{
    if(str.length()==0)
    {
        cout<<ans;
        return 1;
    }
    int count=0;
    for(int i=0;i<str.length();i++)
    {
        char ch=str[i];
        string ros=str.substr(0,i)+str.substr(i+1);
        count+=permutations(ros,ans+ch);
    }
    return count;
}

int permutation_Unique(string str,string ans)
{
    if(str.length()==0)
    {
        cout<<ans;
        return 1;
    }
    int count=0;
    vector<bool> vis(26,0);
    for(int i=0;i<str.length();i++)
    {
        char ch=str[i];
        if(!vis[ch-'a'])
        {
            vis[ch-'a']=true;
            string ros=str.substr(0,i)+str.substr(i+1);
            count+=permutation_Unique(ros,ans+ch);
        }
    }
    return count;
}


vector<string> mazePath(int sr,int sc,int er,int ec)
{
    if(sr==er && sc==ec)
    {
        vector<string> baseArray;
        baseArray.push_back("");
        return baseArray;
    }
    vector<string>myAns;
    if(sr+1<=er)
    {
        vector<string> vertical=mazePath(sr+1,sc,er,ec);
        for(string s:vertical) myAns.push_back("V"+s);
    }
    if(sc+1<=ec)
    {
        vector<string> horizontal=mazePath(sr,sc+1,er,ec);
        for(string s:horizontal) myAns.push_back("H"+s);
    }
    if(sr+1<=er)
    {
        vector<string> diagonal=mazePath(sr+1,sc+1,er,ec);
        for(string s:diagonal) myAns.push_back("D"+s);
    }
    return myAns;
}

int mazePath_(int sr,int sc,int er,int ec,string ans)
{
    if(sr==er && sc==ec)
    {
        cout<<ans;
        return 1;
    }
    int count=0;
    if(sr+1<=er) count+=mazePath_(sr+1,sc,er,ec,ans+"V");
    if(sc+1<=ec) count+=mazePath_(sr,sc+1,er,ec,ans+"H");
    if(sr+1<=er && sc+1<<ec) count+=mazePath_(sr+1,sc+1,er,ec,ans+"D");
    return count;
}
/*
int mazePath_multiplePath(int sr,int sc,int er,int ec,string ans)
{
    if(sr==er && sc==ec)
    {
        cout<<ans;
        return 1;
    }
    int count=0;
    for(int jump=1;sr+jump<=er;jump++) count+=mazePath_multiplePath(sr+jump,sc,er,ec,"V"+jump);
    for(int jump=1;sc+jump<=ec;jump++) count+=mazePath_multiplePath(sr,sc+jump,er,ec,"H"+jump);
    for(int jump=1;sr+jump<=er && sc+jump<=ec;jump++) count+=mazePath_multiplePath(sr+jump,sc+jump,er,ec,"D"+jump);
    return count;
}*/

vector<vector<int>> vis;
vector<vector<int>> dir{{1,0},{0,1},{-1,0},{0,-1}};
vector<string> dirS{"L","R","T","D"};

int floodfill(int sr,int sc,int er,int ec,string ans)
{
    if(sr==er && sc==ec)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count =0;
    vis[sr][sc]=1;
    for(int d=0;d<dir.size();d++)
    {
        int r=sr+dir[d][0];
        int c=sc+dir[d][1];

        if(r>=0 && c>=0 && r<vis.size() && c<vis[0].size() && vis[r][c]==0)
        {
            count+=floodfill(r,c,er,ec,ans+dirS[d]);
        }
    }
    vis[sr][sc]=0;
    return count;
}

int floodfill_jump(int sr,int sc,int er,int ec,string ans)
{
    if(sr==er && sc==ec)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count =0;
    vis[sr][sc]=1;
    for(int jump=1;jump<=max(er,ec);jump++)
    {
        for(int d=0;d<dir.size();d++)
        {
            int r=sr+dir[d][0];
            int c=sc+dir[d][1];

            if(r>=0 && c>=0 && r<vis.size() && c<vis[0].size() && vis[r][c]==0)
            {
                count+=floodfill_jump(r,c,er,ec,ans+dirS[d]+to_string(jump));
            }
        }
    }
    vis[sr][sc]=0;
    return count;
}


void maze()
{
    vis.resize(3,vector<int>(3,0));
    //cout<<floodfill(0,0,2,2,"");
    cout<<floodfill_jump(0,0,2,2,"");

    //for(int ele:ans) cout<<ele;
    //cout<<subsequence("abc","");
    //vector<string> ans=subsequence("abc",0);
    //for(string s:ans) cout<<s<<" ";
    //cout<<keypad_combinations("123",0,"");
    //vector<string> ans=keypad("123",0);
    //for(string s:ans) cout<<s;
    //cout<<permutations("abc","");
    //cout<<permutation_Unique("aba","");
    //vector<string> ans=mazePath(0,0,2,2);
    //for(string s:ans) cout<<s<<" ";
    //cout<<mazePath_(0,0,2,2,"");
    //cout<<mazePath_multiplePath(0,0,2,2,"");
}

int permutation(vector<int> coins,int tar,string ans)
{
    if(tar==0)
    {
        cout<<ans;
        return 1;
    }
    int count=0;
    for(int i=0;i<coins.size();i++)
    {
        if(tar-coins[i]>=0)
            count+=permutation(coins,tar-coins[i],ans+to_string(coins[i]));
    }
    return count;
}

int combination(vector<int> coins,int tar,int idx,string ans)
{
    if(tar==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=idx;i<coins.size();i++)
    {
        if(tar-coins[i]>=0)
            count+=combination(coins,tar-coins[i],i,ans+to_string(coins[i]));
    }
    return count;
}

int SinglePermutation(vector<int>coins,int tar,vector<bool> vis,string ans)
{
    if(tar==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=0;i<coins.size();i++)
    {
        if(tar-coins[i]>=0 && !vis[i])
        {
            vis[i]=true;
            count+=SinglePermutation(coins,tar-coins[i],vis,ans+to_string(coins[i]));
            vis[i]=false;
        }
    }
    return count;
}

int Singlecombination(vector<int>coins,int tar,int idx,string ans)
{
    if(tar==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=idx;i<coins.size();i++)
    {
        if(tar-coins[i]>=0)
            count+=combination(coins,tar-coins[i],i+1,ans+to_string(coins[i]));
    }
    return count;
}

int permutation_sub(vector<int> coins,int idx,int tar,string ans)
{
    if(tar==0 || idx>=coins.size())
    {
        if(tar==0)
        {
            cout<<ans;
            return 1;
        }
    }   
    int count=0;
    if(tar-coins[idx]>=0)
    {
        count+=permutation_sub(coins,0,tar-coins[idx],ans+to_string(coins[idx]));
        count+=permutation_sub(coins,idx+1,tar,ans);
    }
    return count;
}

int combinatation_sub(vector<int> coins,int idx,int tar,string ans)
{
    if(tar==0 || idx>=coins.size())
    {
        if(tar==0)
        {
            cout<<ans;
            return 1;
        }
    }   
    int count=0;
    if(tar-coins[idx]>=0)
    {
        count+=combinatation_sub(coins,idx,tar-coins[idx],ans+to_string(coins[idx]));
        count+=combinatation_sub(coins,idx+1,tar,ans);
    }
    return count;
}

int combinatation_single_sub(vector<int> coins,int idx,int tar,string ans)
{
    if(tar==0 || idx>=coins.size())
    {
        if(tar==0)
        {
            cout<<ans;
            return 1;
        }
    }   
    int count=0;
    if(tar-coins[idx]>=0)
    {
        count+=combinatation_single_sub(coins,idx+1,tar-coins[idx],ans+to_string(coins[idx]));
        count+=combinatation_single_sub(coins,idx+1,tar,ans);
    }
    return count;
}

int permutation_single_sub(vector<int> coins,int idx,int tar,vector<bool> vis,string ans)
{
    if(tar==0 || idx>=coins.size())
    {
        if(tar==0)
        {
            cout<<ans<<" ";
            return 1;
        }
        return 0;
    }   
    int count=0;
    if(tar-coins[idx]>=0 && !vis[idx])
    {
        vis[idx]=true;
        count+=permutation_single_sub(coins,0,tar-coins[idx],vis,ans+to_string(coins[idx]));
        vis[idx]=false; 
    }
    count+=permutation_single_sub(coins,idx+1,tar,vis,ans);
        
    return count;
}

void coin()
{
    vector<int> coins={2,3,5,7};
    vector<bool> vis(4,false);
    //cout<<permutation(coins,10,"")<<endl;
    //cout<<permutation_sub(coins,0,10,"");
    //cout<<combination(coins,10,0,"");
    //cout<<combinatation_sub(coins,0,10,"");
    //cout<<SinglePermutation(coins,10,vis,"");
    cout<<permutation_single_sub(coins,0,10,vis,"");
    //cout<<Singlecombination(coins,10,0,"");
    //cout<<combinatation_single_sub(coins,0,10,"");
}

int queen_Combi(vector<bool> boxes,int idx,int tnq,int qpsf,string ans)
{
    if(tnq==qpsf)
    {
        cout<<ans<<" ";
        return 1;
    }
    int count=0;
    for(int i=idx;i<boxes.size();i++)
    {
        //cout<<qpsf;
        count+=queen_Combi(boxes,i+1,tnq,qpsf+1,ans+"b"+to_string(i)+"Q"+to_string(qpsf));

    }
    return count;
}

int queen_Combi_2D(vector<vector<bool>> boxes,int idx,int tnq,int qpsf,string ans)
{
    if(tnq==qpsf)
    {
        cout<<ans<<" ";
        return 1;
    }
    int count=0;
    int n=boxes.size();
    for(int i=idx;i<n*n;i++)
    {
        int r=i/n;
        int c=i%n;
        //cout<<qpsf;
        count+=queen_Combi_2D(boxes,i+1,tnq,qpsf+1,ans+"("+to_string(c)+","+to_string(r)+")");
    }
    return count;
}

int queen_permu(vector<bool> boxes,int tnq,int qpsf,string ans)
{
    if(tnq==qpsf)
    {
        cout<<ans<<" ";
        return 1;
    }
    int count=0;
    for(int i=0;i<boxes.size();i++)
    {
        if(!boxes[i])
        {
            //cout<<qpsf;
            boxes[i]=true;
            count+=queen_permu(boxes,tnq,qpsf+1,ans+"b"+to_string(i)+"Q"+to_string(qpsf));
            boxes[i]=false;
        }

    }
    return count;
}

int queen_permu_2D(vector<vector<bool>> boxes,int tnq,int qpsf,string ans)
{
    if(tnq==qpsf)
    {
        cout<<ans<<" ";
        return 1;
    }
    int count=0;
    int n=boxes.size();
    for(int i=0;i<n*n;i++)
    {
        int r=i/n;
        int c=i%n;
        if(!boxes[r][c])
        {
            //cout<<qpsf;
            boxes[r][c]=true;
            count+=queen_permu_2D(boxes,tnq,qpsf+1,ans+"b"+to_string(i)+"Q"+to_string(qpsf));
            boxes[r][c]=false;
        }

    }
    return count;
}


void queen()
{
    //vector<bool> boxes(6,0);
    vector<vector<bool>> boxes;
    boxes.resize(4,vector<bool>(4));
    //cout<<queen_Combi(boxes,0,2,0,"");
    //cout<<queen_permu(boxes,3,0,"");
    //cout<<queen_Combi_2D(boxes,0,4,0,"");
    cout<<queen_permu_2D(boxes,4,0,"");
}

int main()
{
    //basics();
    //maze();
    //coin();
    queen();
    return 0;
}

