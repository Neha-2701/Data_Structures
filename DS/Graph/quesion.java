import java.util.LinkedList;
import java.util.Arrays; 

//Leetcode 200
public class quesion {
    public void numIslandsDFS(char[][] grid,int r,int c,int[][] dirc)
    {
        grid[r][c]='0';
        for(int i=0;i<dirc.length;i++)
        {
            int x=r+dirc[i][0];
            int y=c+dirc[i][1];
        
            if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]=='1')
            {   
                numIslandsDFS(grid, x, y, dirc);
            }
        }   
    }
    public int numIslands(char[][] grid) {
        int[][] dirc={{0,1},{1,0},{-1,0},{0,-1}};
        int count=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                {
                    numIslandsDFS(grid,i,j,dirc);
                    count++;
                }
            }
        }
        return count;
    }

    //leetcode 695
    public int maxAreaOfIslandDFS(int[][] grid,int r,int c,int[][] dirc)
    {
        grid[r][c]=0;
        int count=0;
        for(int i=0;i<dirc.length;i++)
        {
            int x=r+dirc[i][0];
            int y=c+dirc[i][1];
        
            if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]==1)
            {   
                count+=maxAreaOfIslandDFS(grid, x, y, dirc);
            }
        }   
        return count+1;
    }
    public int maxAreaOfIsland(int[][] grid) {
        int[][] dirc={{0,1},{1,0},{-1,0},{0,-1}};
        int maxArea=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                {
                    maxArea=Math.max(maxArea,maxAreaOfIslandDFS(grid,i,j,dirc));
                }
            }
        }
        return maxArea;
    }

    //leetcode 493
    public int perimeter(int[][] grid)
    {
        int[][] dirc={{0,1},{1,0},{-1,0},{0,-1}};
        int Countones=0;
        int Countnbr=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]==1)
                {
                    Countones++;
                    for(int d=0;d<dirc.length;d++)
                    {
                        int x=i+dirc[d][0];
                        int y=j+dirc[d][1];
                        
                        if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]==1)
                        {
                            Countnbr++;
                        }
                    }
                }
            }
        }
        return Countones*4 -Countnbr;  
    }

    //Leetcode 130
    public void SurroundedRegionDFS(char[][] grid,int r,int c,int[][] dir){
        grid[r][c] = '$';
        for(int d = 0 ;d < 4 ; d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if( x>=0 && y>=0 && x < grid.length && y < grid[0].length && grid[x][y] == 'O') 
               SurroundedRegionDFS(grid,x,y,dir);
        }
    }


    public void solve(char[][] grid) {
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){
                if(i ==0 || j == 0 || i == grid.length-1 || j == grid[0].length - 1){
                    if(grid[i][j] == 'O')
                       SurroundedRegionDFS(grid,i,j,dir);
                }

            }
        }

        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 'O') grid[i][j] = 'X';
                else if(grid[i][j] == '$') grid[i][j] = 'O';
            }
        }
    }

    //leetcode-785
    public boolean isBipartite(int[][] graph,int[] markedColor,int src)
    {
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        int color=0;
        while(que.size()!=0)
        {
            int size=que.size();
            while(size-->0)
            {
                int vtx=que.removeFirst();
                if(markedColor[vtx]!=-1)
                {
                    if(markedColor[vtx]!=color)
                    {
                        return false;
                        
                    }
                    continue;
                }
                markedColor[vtx]=color;
                for(int e:graph[vtx])
                {
                    if(markedColor[e]==-1) que.addLast(e);
                }
                    
            }
            color=(color+1)%2;
        }
        return true;
    }
    public boolean isBipartite(int[][] graph) {
        int N=graph.length;
        int[] markedColor=new int[N];
        Arrays.fill(markedColor,-1);

        for(int i=0;i<N;i++)
        {
            if(markedColor[i]==-1)
            {
                boolean ans=isBipartite(graph,markedColor,i);
                if(!ans) 
                {
                    return false;
                }
            }
        }
        return true;
    }

    //leetcode 1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return -1;
        
        int n=grid.length;
        int m=grid[0].length;

        if(grid.length==1 || grid[0].length==1) return -1;

        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(0*m+0);

        int[][] dir={{0,1},{1,0},{-1,0},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1}};
        grid[0][0]=1;
        int level=1;
        while(que.size()!=0)
        {
            int size=que.size();
            while(size-->0)
            {
                int idx=que.removeFirst();
                int r=idx/m;
                int c=idx%m;

                if(r==n-1 && c==m-1) return level;

                for(int d=0;d<8;d++)
                {
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];

                    if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]==0)
                    {
                        grid[x][y]=1;
                        que.addLast(x*m+y);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    //leetcode 286
    public void wallsAndGates(int[][] rooms)
    {
        LinkedList<Integer>que=new LinkedList<>();
        int[][] dir={{0,1},{1,0},{-1,0},{0,-1}};

        int n=rooms.length;
        int m=rooms[0].length;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(rooms[i][j]==0)
                {
                    que.addLast(i*m+j);
                }
            }
        }
        int level=0;
        while(que.size()!=0)
        {
            int size=que.size();
            while(size-->0)
            {
                int idx=que.removeFirst();
                int r=idx/m;
                int c=idx%n;

                for(int d=0;d<4;d++)
                {
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];

                    if(x>=0 && y>=0 && x<n && y <n && rooms[x][y]==2147483647)
                    {
                        rooms[x][y]=level+1;
                        que.addLast(x*m+c);
                    }
                }
            }
            level++;
        }
    }

    public void wallsAndGatesByUsingVisited(int[][] rooms)
    {
        LinkedList<Integer>que=new LinkedList<>();
        int[][] dir={{0,1},{1,0},{-1,0},{0,-1}};
        
        int n=rooms.length;
        int m=rooms[0].length;

        boolean[][] vis=new boolean[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(rooms[i][j]==0)
                {
                    que.addLast(i*m+j);
                    vis[i][j]=true;
                }
            }
        }
        int level=0;
        while(que.size()!=0)
        {
            int size=que.size();
            while(size-->0)
            {
                int idx=que.removeFirst();
                int r=idx/m;
                int c=idx%n;

                rooms[r][c]=level;
                for(int d=0;d<4;d++)
                {
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];

                    if(x>=0 && y>=0 && x<n && y <n && (!vis[x][y]) && rooms[x][y]!=-1)
                    {   
                        if(!vis[x][y])
                        {
                            vis[x][y]=true;
                            que.addLast(x*m+c);
                        }
                    }
                }
            }
            level++;
        }
    }

    //letecode 994
    public int orangesRotting(int[][] grid) {
       
        if(grid.length==0 || grid[0].length==0) return -1;
        int n=grid.length;
        int m=grid[0].length;
        int freshOranges=0;

        int[][] dir={{0,1},{1,0},{-1,0},{0,-1}};
        LinkedList<Integer>que=new LinkedList<>(); 

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j]==1)
                    freshOranges++;
                if(grid[i][j]==2)
                    que.addLast(i*m +j);
            }
        }
        
        if(freshOranges==0) return 0;
        int time=0;
        while(que.size()!=0)
        {
            int size=que.size();
            while(size-->0)
            {
                int idx=que.removeFirst();
                int r=idx/m;
                int c=idx%m;

                for(int d=0;d<4;d++)
                {
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];

                    if(x>=0 && y>=0 && x<n && y <m  && grid[x][y]==1)
                    {   
                        grid[x][y]=2;
                        freshOranges--;
                        que.addLast(x*m+y);
                        
                        if(freshOranges==0) return time+1;
                    }
                }
            }
            time++;
        }
        return -1;
    }

    //leetcode 1061
    int[] par=new int[26];
    int findpar(int u)
    {
        if(par[u]==u) return u;
        return par[u]=findpar(par[u]);
    }
    public String smallestEquivalentString(String A,String B,String C)
    {
        
        for(int i=0;i<26;i++)
        {
            par[i]=i;
        }
        for(int i=0;i<A.length();i++)
        {
            int p1=findpar(A.charAt(i)-'a');
            int p2=findpar(B.charAt(i)-'b');

            par[p1]=Math.min(p1, p2);
            par[p2]=Math.min(p1,p2);
        }

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<C.length();i++)
        {
            int p=findpar(C.charAt(i)-'a');
            sb.append((char)(p+'a'));
        }
        return sb.toString();
    }
}
