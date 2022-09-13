#include<iostream>
#include<vector>
using namespace std;
vector<vector<int>> res;
vector<int> smallAns;

///---------subsequence-of-a-string---------

vector<string> subsequence(string& str,int idx)
{
    if(idx==str.length())
    {
        vector<string>base;
        base.push_back("");
        return base;
    }
    vector<string>smallAns=subsequence(str,idx+1);
    vector<string>myAns;

    for(string s:smallAns)
    {
        myAns.push_back(s);
        myAns.push_back(str[idx]+s);
    }
    return myAns;
}

///---------------encoding------------------

int encoding(string &str,int idx,string ans)
{
    if(idx==str.length())
    {
        cout<<ans<<endl;
        return 1;
    }
    char ch=str[idx];
    if(ch=='0')
    {
        return 0;
    }
    int count=0;
    count+=encoding(str,idx+1,ans+string(1,(ch-'0')+('a'-1)));
    if(idx<str.length()-1)
    {
        int ch1=(ch-'0')*10+(str[idx+1]-'0');
        if(ch1>=10 && ch1<=26)
        {
            count+=encoding(str,idx+2,ans+string(1,ch1+'a'-1));
        }
    }
    return count;
}