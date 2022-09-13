import java.util.ArrayList;
import java.util.LinkedList;

public class BFS{
    public static class Edge{
        int v=0;
        int w=0;
        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    }

    static int N=7;
    static ArrayList<Edge>[] graph=new ArrayList[N];
    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(){
        for(int i=0;i<N;i++)
        {
            System.out.print(i+"->");
            for(Edge e:graph[i]){
                System.out.print("("+e.v+","+e.w+")"+" ");
            }
            System.out.println();
        }
    }

    public static void construct(){
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<Edge>();
        }
        addEdge(0,1,10);
        addEdge(1, 2, 20);
        addEdge(2,3,30);
        addEdge(3,4,20);
        addEdge(4,5,30);
        addEdge(5,6,30);
        addEdge(6,1,20);
    }

    public static void bfs_01(int src,boolean[] vis){
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        boolean cycle=false;
        while(que.size()!=0){
            int vtx=que.removeFirst();
            if(vis[vtx]){
                cycle=true;
                continue;
            }
            vis[vtx]=true;
            System.out.print(vtx);
            for(Edge e:graph[vtx]){
                if(!vis[e.v]) que.addLast(e.v);
            }
            System.out.println();
        }
    }

    /*public static void BFS_02(int src,boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        que.addLast(null);

        boolean cycle = false;
        int level = 0;

        while(que.size() != 1){
            int vtx = que.removeFirst();
            
            if(vis[vtx]){
                cycle = true;
                continue;
            }

            if(vtx == null){
                System.out.println(level);
            }

            vis[vtx] = true;
            
            for(Edge e: graph[vtx]){
                if(!vis[e.v]){
                    que.addLast(e.v);
                }
            }

            if(que.getFirst() == null){
                level++;
                que.addLast(que.removeFirst());
            }
        }
    }*/

    public static void main(String[] args){
        boolean[] vis=new boolean[N];
        
        construct();
        display();

        //bfs_01(0, vis);
       
    }

}