#include<iostream>
#include<vector>

using namespace std;
//Leetcode 684
vector<int> par;
int findpar(int u)
{
    if(par[u]==u) return u;
    return par[u]=findpar(par[u]);
}
vector<int> findRedundantConnection(vector<vector<int>>& edges) {
    int n=edges.size();
    for(int i=0;i<=n;i++)
    {
        par.push_back(i);
    }
    for(vector<int> a:edges)
    {
        int p1=findpar(a[0]);
        int p2=findpar(a[1]);

        if(p1!=p2)
            par[p1]=p2;
        else
            return a;
    }
    return {};
}

