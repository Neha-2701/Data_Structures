#include<iostream>
#include<vector>

using namespace std;
class Edge{
    public:
    int v=0;
    int w=0;

    Edge(int v,int w)
    {
        this->v=v;
        this->w=w;
    }
};

int N=9;
vector<vector<Edge>> graph(N,vector<Edge>());

void addEdge(vector<vector<Edge>>graph,int u,int v,int w)
{
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));
}
void display(vector<vector<Edge>>graph,int N)
{
    for(int i=0;i<N;i++)
    {
        cout<<i<<" ";
        for(Edge e:graph[i])
        {
            cout<<"("+to_string(e.v)+","+to_string(e.w)+")";
        }
        cout<<endl;
    }
}

vector<int>par;
vector<int>size1;

int findPar(int u)
{
    if(par[u]==u) return u;
    return par[u]==findPar(par[u]);
}

void merge(int p1,int p2)
{
    if(size1[p1]<size1[p2])
    {
        par[p1]=p2;
        size1[p2]+=size1[p1];
    }
    else
    {
        par[p2]=p2;
        size1[p1]+=size1[p2];
    }  
}

void unionFind(int n,vector<vector<int>> &edges)
{
    vector<vector<Edge>> ngraph(n,vector<Edge>());
    for(int i=0;i<n;i++)
    {
        par.push_back(i);
        size1.push_back(1);
    }
    for(vector<int>a : edges)
    {
        int gp1=findPar(a[0]);
        int gp2=findPar(a[1]);

        if(gp1!=gp2)
        {
            addEdge(ngraph,a[0],a[1],a[2]);
        }
    }
    display(ngraph,n);
}
