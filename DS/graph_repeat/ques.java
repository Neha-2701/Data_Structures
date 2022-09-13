import java.util.ArrayList;

public class ques {
    public static class Edge{
        int v=0;
        int w=0;

        public Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    }

    @SuppressWarnings("unchecked")
    static int N=7;
    static ArrayList<Edge> [] graph=new ArrayList[N]; 

    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(){
        for(int i=0;i<N;i++){
            System.out.print(i+"->");
            for(Edge e:graph[i]){
                System.out.print("("+e.v+","+e.w+")");
            }
            System.out.println();
        }
    }

    public static void construct()
    {
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<Edge>();
        }
        addEdge(0,1,2);
        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,2,20);
        addEdge(2,3,30);
        addEdge(3,4,30);
        addEdge(4,5,40);
        addEdge(5,2,40);
    }

    public static boolean hasPath(int src,int dest,boolean[] vis){
        if(src==dest) return true;
        boolean res=false;
        vis[src]=true;
        for(Edge e:graph[src])
        {
            if(!vis[e.v])
                res=res||hasPath(e.v, dest, vis);
        }
        return res;
    }

    public static int allPath(int src,int dest,boolean[] vis,String psf,int wsf){
        if(src==dest){
            System.out.println(psf+src+"@"+wsf);
            return 1;
        }
        int count=0;
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                count+=allPath(e.v, dest, vis, psf+src+" ", wsf+e.w);
            }
        }
        return count;
    }

    public static class pair{
        int weight=0;
        String path="";
        boolean isDesti=false;
        public pair(int weight,String path,boolean isDesti){
            this.weight=weight;
            this.path=path;
            this.isDesti=isDesti;
        }
    }

    public static pair maxiPath(int src,int dest,boolean[] vis){
        if(src==dest) {
            return new pair(0,src+ " ",true);
        }
        pair myAns=new pair(0,"",false);
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                pair recAns=maxiPath(e.v, dest, vis);
                if(recAns.weight+e.w>myAns.weight){
                    myAns.weight=recAns.weight+e.w;
                    myAns.path=src+" " +recAns.path;
                    myAns.isDesti=true;
                }
            }
        }
        vis[src]=false;
        return myAns;
    }

    public static pair maxPath(int src,int dest,boolean[] vis){
        if(src==dest) {
            return new pair(0,src+ " ",true);
        }
        pair myAns=new pair(0,"",false);
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                pair recAns=maxiPath(e.v, dest, vis);
                if(recAns.weight+e.w >myAns.weight){
                    myAns.weight=recAns.weight+e.w;
                    myAns.path=src+" " +recAns.path;
                    myAns.isDesti=true;
                }
            }
        }
        vis[src]=false;
        return myAns;
    }

    


    public static void main(String[] args)
    {
        construct();
        display();
        boolean[] vis=new boolean[N];
        //System.out.println(hasPath(0, 1, vis));  
        //System.out.println(allPath(0, 2, vis, "", 0));
        pair myAns=maxiPath(0, 5, vis);
        System.out.println(myAns.path+" "+myAns.weight+" "+myAns.isDesti);
    }
}
