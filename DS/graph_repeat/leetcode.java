public class leetcode{
    //Leetcode 200
    public static void Island(char[][] grid,int r,int c,int[][] dirA){
        grid[r][c]='0';
        for(int d=0;d<dirA.length;d++)
        {
            int x=r+dirA[d][0];
            int y=c+dirA[d][1];

            if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]=='1')
                Island(grid, x, y, dirA);
        }
    }
    public static int numIslands(char[][] grid)
    {
        int[][] dirA={{0,1},{1,0},{-1,0},{0,-1}};
        int count=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
                if(grid[i][j]=='1')
                {
                    Island(grid,i,j,dirA);
                    count++;
                }
            }
        }
        return count;
    }

    //Leetcode-695
    public static int maxAreaOfIsland(int[][] grid,int r,int c,int[][] dirA){
        int count=0;
        grid[r][c]=0;
        for(int d=0;d<dirA.length;d++){
            int x=r+dirA[d][0];
            int y=c+dirA[d][1];

            if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]==1){
                count+=maxAreaOfIsland(grid,x,y,dirA);
            }
        }
        return count+1;
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int maxArea=0;
        int[][] dirA={{0,1},{1,0},{0,-1},{-1,0}};
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1)
                {
                    maxArea=Math.max(maxArea,maxAreaOfIsland(grid,i,j,dirA));
                }
            }
        }
        return maxArea;
    }

    //Leetcode 463
    public int islandPerimeter(int[][] grid) {
        int countOnes=0;
        int countNbrs=0;
        int[][] dirA={{1,0},{0,1},{0,-1},{-1,0}};
        for(int i=0;i<dirA.length;i++){
            for(int j=0;j<dirA[0].length;j++){
                if(grid[i][j]==1) countOnes++;
                for(int d=0;d<dirA.length;d++)
                {
                    int x=i+dirA[d][0];
                    int y=j+dirA[d][1];
                    if(x>=0 && y>=0 && x<grid.length && y<grid[0].length && grid[x][y]==1) countNbrs++;
                }
            }
        }
        return 4*countOnes-countNbrs;
    }

    public static void main(String[] args){
       /* char[][] grid={
            {1,1,1,1,0},
            {1,1,0,1,0},
            {1,1,0,0,0},
            {0,0,0,0,0}
        };*/
        int[][] grid={{0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(grid));
    }
}