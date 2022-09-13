
import java.util.ArrayList;

public class recursion{
    public static void printDec(int n)
    {
        if(n==0)
            return;
        System.out.print(n);
        printDec(n-1);
    }

    public static void printInc(int n)
    {
        if(n==0)
            return;
        printInc(n-1);
        System.out.print(n);
    }

    public static void oddEven(int n)
    {
        if(n==0)
            return;
        if(n%2!=0) System.out.print(n);;
        oddEven(n-1);
        if(n%2==0) System.out.print(n);;
    }

    public static int fact(int n)
    {
        return n==0?1:fact(n-1)*n;
    }

    public static int pow(int a,int b)
    {
        return b==0?1:pow(a,b-1)*a;
    }

    public static int powBtr(int a,int b)
    {
        if(b==0) return 1;
        int ans=pow(a,b/2);
        ans*=ans;
        return (b%2==0)?ans:ans*a;
    }

    public static void display(int[] arr,int idx)
    {
        if(idx==arr.length)
            return;
        System.out.println(arr[idx]);
        display(arr,idx+1);
    }

    public static int maximum(int[] arr,int idx)    
    {
        if(idx==arr.length) return -1;
        return Math.max(arr[idx],maximum(arr,idx+1));
    }

    public static boolean find(int[] arr,int idx,int data)
    {
        if(idx==arr.length) return false;
        if(arr[idx]==data) return true;
        return find(arr,idx+1,data);
    }
    
    
    public static int firstIndex(int[] arr,int idx,int data)
    {
        if(idx==arr.length) return -1;
        if(data==arr[idx]) return idx;
        return firstIndex(arr,idx+1,data);
    }

    public static int lastIndex_(int[] arr,int idx,int data)
    {
        if(idx==0) return -1;
        if(data==arr[idx]) return idx;
        return lastIndex_(arr,idx-1,data);
    }

    public static int lastIndex(int[] arr,int idx,int data)
    {
        if(idx==arr.length) return -1;
        int ans=lastIndex(arr,idx+1,data);
        if(ans!=-1) return ans;
        return (data==arr[idx])?idx:-1;
    }

    public static int[] allIndices(int[] arr,int idx,int data,int count)
    {
        if(idx==arr.length)
        {
            int[] baseArray=new int[count];
            return baseArray;
        }
        if(arr[idx]==data) count++;
        int[] smallAns=allIndices(arr,idx+1,data,count);
        if(arr[idx]==data) smallAns[count-1]=idx;
        return smallAns;
    }

    void basics()
    {
        //printDec(5);
        //printInc(5);
        //oddEven(5);
        //System.out.println(fact(5));
        //System.out.println(pow(2,5));
        //System.out.println(powBtr(2,5));
        //int[] arr={1,2,2,2,2,2,3,4,5};
        //System.out.println(find(arr,0,2));
        //System.out.println(maximum(arr, 0));
        //display(arr, 0);
        //System.out.println(firstIndex(arr, 0, 2));
        //System.out.println(lastIndex(arr, 0, 2));
        //System.out.println(lastIndex_(arr, arr.length-1, 2));
        //int[] ans=allIndices(arr, 0, 2, 0);
        //for(int ele:ans) System.out.println(ele);
    }

    public static int subsequence(String str,String ans)
    {
        if(str.length()==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        count+=subsequence(str.substring(1),ans);
        count+=subsequence(str.substring(1),ans+str.charAt(0));
        return count;
    }

    public static ArrayList<String> subsequence(String str,int idx)
    {
        if(idx==str.length())
        {
            ArrayList<String>baseArray=new ArrayList<>();
            baseArray.add("");
            return baseArray;
        }
        char ch=str.charAt(idx);
        ArrayList<String> smallAns=subsequence(str,idx+1);
        ArrayList<String> myAns=new ArrayList<>();
        for(String s:smallAns)
        {   
            myAns.add(s);
            myAns.add(ch+s);
        }
        return myAns;
    }
    static String[] words={"!*(","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz","@#$","@#$^"};
    public static int keypad_combinations(String str,int idx,String ans)
    {
        if(idx==str.length())
        {
            System.out.println(ans);
            return 1;
        }
        int chi=str.charAt(idx)-'0';
        String word=words[chi];
        int count=0;
        for(int i=0;i<word.length();i++)
        {
            count+=keypad_combinations(str, idx+1, ans+word.charAt(i));
        }
        return count; 
    }

    public static ArrayList<String> keypad(String str,int idx)
    {
        if(idx==str.length())
        {
            ArrayList<String> baseArray=new ArrayList<>();
            baseArray.add("");
            return baseArray;
        }
        int ch=str.charAt(idx)-'0';
        String word=words[ch];
        ArrayList<String> smallAns=keypad(str, idx+1);
        ArrayList<String> myAns=new ArrayList<>();
        for(String s:smallAns)
        {
            for(int i=0;i<word.length();i++)
            {
                myAns.add(word.charAt(i)+s);
            }
        }
        return myAns;
    }

    public static int keypad_modified(String str,int idx,String ans)
    {
        if(idx==str.length())
        {
            System.out.print(ans);
            return 1;
        }
        int count=0;
        int ch=str.charAt(idx)-'0';
        String word=words[ch];
        for(int i=0;i<word.length();i++) count+=keypad_modified(str,idx+1,ans+word.charAt(i));
        if(idx<str.length()-1)
        {
            int ch1=ch*10+(str.charAt(idx+1)-'0');
            if(ch1>=10 && ch1<=11)
            {
            word=words[ch1];
            for(int i=0;i<word.length();i++)
                count+=keypad_modified(str,idx+2,ans+word.charAt(i));
            }
        }
        return count;
    }

    /*public static int permutations(String str,String ans)
    {
        if(str.length()==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            String ros=str.substring(0, i-1)+str.substring(i+1);
            count+=permutations(ros, ans+ch);
        }
        return count;
    }*/

    public static ArrayList<String> mazePath(int sr,int sc,int er,int ec)
    {
        if(sr==er && sc==ec)
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns=new ArrayList<>();
        if(sr+1<=er)
        {
            ArrayList<String> vertical=mazePath(sr+1, sc, er, ec);
            for(String s:vertical) myAns.add(s+"V");
        }
        if(sc+1<=ec)
        {
            ArrayList<String> horizontal=mazePath(sr, sc+1, er, ec);
            for(String s:horizontal) myAns.add(s+"H");
        }
        if(sr+1<=er && sc+1<=ec)
        {
            ArrayList<String> diagonal=mazePath(sr+1, sc+1, er, ec);
            for(String s:diagonal) myAns.add(s+"D");
        }
        return myAns;
    }

    public static int mazePath_(int sr,int sc,int er,int ec,String ans)
    {
        if(sr==er && sc==ec)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        if(sr+1<=er)
            count+=mazePath_(sr+1, sc, er, ec, ans+"H");
        if(sc+1<=ec)
            count+=mazePath_(sr, sc+1, er, ec, ans+"V");
        if(sr+1<=er && sc+1<=ec)
            count+=mazePath_(sr+1, sc+1, er, ec, ans+"D");
        return count;
    }

    public static int mazepath_jumps(int sr,int sc,int er,int ec,String ans)
    {
        if(sr==er && sc==ec)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int jump=1;sr+jump<=er;jump++) count+=mazepath_jumps(sr+jump, sc, er, ec, ans+"H"+jump);
        for(int jump=1;sc+jump<=ec;jump++) count+=mazepath_jumps(sr, sc+jump, er, ec, ans+"V"+jump);
        for(int jump=1;sr+jump<=er && sc+jump<=ec;jump++) count+=mazepath_jumps(sr+jump, sc+jump, er, ec, ans+"D"+jump);
        return count;
    }

    /*static ArrayList<ArrayList<Boolean>> vis=new ArrayList<>();
    int[][] dir={{0,-1},{-1,0},{1,0},{0,1}};
    String[] dirS={"L","R","H","V"};
    public static int floodfill(int sr,int sc,int er,int ec,String ans)
    {
        if(sr==er && sc==ec)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        vis[sr][sc]=0;
        
    }*/
    public static void maze()
    {
        System.out.println(mazepath_jumps(0, 0, 2, 2, ""));
        //System.out.println(mazePath_(0, 0, 2, 2, ""));
        //System.out.print(permutations("abc", ""));
        //basics();
        //System.out.println(subsequence("abc", ""));
        //ArrayList<String> ans=subsequence("abc", 0);
        //for(String s:ans) System.out.println(s);
        //System.out.println(keypad_combinations("112", 0, ""));
        //System.out.println(keypad_modified("132", 0, ""));
        //ArrayList<String> ans=keypad("abc", 0);
        //for(String s:ans)
        //    System.out.println(s);
        //ArrayList<String> ans=mazePath(0, 0, 2, 2);
        //for(int i=0;i<ans.size();i++)
        //     System.out.println(ans.get(i));
    }

    public static int permutation(int[] coins,int tar,String ans)
    {
        if(tar==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=0;i<coins.length;i++)
        {
            if(tar-coins[i]>=0)
                count+=permutation(coins,tar-coins[i],ans+coins[i]);
        }
        return count;
    }

    public static int permutation_sub(int[] coins,int tar,int idx,String ans)
    {
        if(tar==0 || idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count =0;
        if(tar-coins[idx]>=0)
        {
            count+=permutation_sub(coins, tar-coins[idx], 0, ans+coins[idx]);
            count+=permutation_sub(coins, tar, idx+1, ans);
        }
        return count;
    }

    public static int combination(int[] coins,int tar,int idx,String ans)
    {
        if(tar==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i<coins.length;i++)
        {
            if(tar-coins[i]>=0)
                count+=combination(coins,tar-coins[i],i,ans+coins[i]);
        }
        return count;
    }

    public static int combination_sub(int[] coins,int tar,int idx,String ans)
    {
        if(tar==0 || idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count =0;
        if(tar-coins[idx]>=0)
        {
            count+=combination_sub(coins, tar-coins[idx], idx, ans+coins[idx]);
            count+=combination_sub(coins, tar, idx+1, ans);
        }
        return count;
    }

    public static int permuatation_Single(int[] coins,int tar,String ans,boolean[] vis)
    {
        if(tar==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=0;i<coins.length;i++)
        {
            if(tar-coins[i]>=0 && !vis[i])
            {
                vis[i]=true;
                count+=permuatation_Single(coins,tar-coins[i],ans+coins[i],vis);
                vis[i]=false;
            }
        }
        return count;
    }

    public static int permuatation_Single_sub(int[] coins,int tar,int idx,String ans,boolean[] vis)
    {
        if(tar==0 || idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count =0;
        if(tar-coins[idx]>=0 &&!vis[idx])
        {
            vis[idx]=true;
            count+=permuatation_Single_sub(coins, tar-coins[idx], 0, ans+coins[idx],vis);
            vis[idx]=false;
        }
        count+=permuatation_Single_sub(coins, tar, idx+1, ans ,vis);
        return count;
    }

    public static int combination_Single(int[] coins,int tar,int idx,String ans)
    {
        if(tar==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i<coins.length;i++)
        {
            if(tar-coins[i]>=0)
                count+=combination_Single(coins,tar-coins[i],i+1,ans+coins[i]);
        }
        return count;
    }

    public static int combination_Single_sub(int[] coins,int tar,int idx,String ans)
    {
        if(tar==0 || idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count =0;
        if(tar-coins[idx]>=0)
        {
            count+=combination_Single_sub(coins, tar-coins[idx], idx+1, ans+coins[idx]);
            count+=combination_Single_sub(coins, tar, idx+1, ans);
        }
        return count;
    }

    public static void coins()
    {
        //int[] coins={2,3,5,7};
        //boolean[] vis=new boolean[4];
        //System.out.println(permuatation_Single(coins,10,"",vis));
        //System.out.println(permuatation_Single_sub(coins, 10, 0, "" ,vis));
        //System.out.println(combination_Single(coins,10,0,""));
        //System.out.println(combination_Single_sub(coins, 10, 0, "" ));
        //System.out.println(permutation(coins,10,""));
        //System.out.println(permutation_sub(coins,10,0,""));
        //System.out.println(combination(coins,10,0,""));
        //System.out.println(combination_sub(coins,10,0,""));
    }

    public static int queen_Combi(boolean[] boxes,int idx,int tnq,int qpsf,String ans)
    {
        if(tnq==qpsf)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i<boxes.length;i++)
            count+=queen_Combi(boxes, i+1, tnq, qpsf+1, ans+"b"+i+"q"+qpsf+" ");
        return count;
    }

    public static int queen_permu_2D(boolean[][] boxes,int tnq,int qpsf,String ans)
    {
        if(tnq==qpsf)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        int n=boxes.length;
        for(int i=0;i<n*n;i++)
        {
            int r=i/n;
            int c=i%n;
            if(!boxes[r][c])
            {
                boxes[r][c]=true;
                count+=queen_permu_2D(boxes, tnq, qpsf+1, ans+"b"+i+"q"+qpsf+" ");
                boxes[r][c]=false;
            }
        }
            
        return count;
    }

    public static int queen_Combi_2D(boolean[][] boxes,int idx,int tnq,int qpsf,String ans)
    {
        if(tnq==qpsf)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        int n=boxes.length;
        for(int i=idx;i<n*n;i++)
        {
            int r=i/n;
            int c=i%n;
            count+=queen_Combi_2D(boxes, i+1, tnq, qpsf+1, ans+"("+r+","+c+")");
        }
        return count;
    }
    public static boolean isSafeToPlace(boolean[][] boxes,int r,int c){
        int[][] dirA={{1,0},{0,1},{1,1},{-1,-1},{-1,1},{1,-1},{0,-1},{-1,0}};
        for(int d=0;d<dirA.length;d++){
            for(int rad=1;rad<=boxes.length;rad++){
                int x=r+rad*dirA[d][0];
                int y=c+rad*dirA[d][1];

                if(x>=0 && y>=0 && x<boxes.length && y<boxes[0].length){
                    if(boxes[x][y])
                        return false;
                }
                else
                    break;
            }
        }
        return true;
    }

    public static int nqueen_01(boolean[][] boxes,int idx,int tnq,int qpsf,String ans){
        if(qpsf==tnq){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        int n=boxes.length;
        for(int i=idx;i<n*n;i++){
            int r=i/n;
            int c=i%n;
            if(isSafeToPlace(boxes,r,c)){
                boxes[r][c]=true;
                count+=nqueen_01(boxes, i+1, tnq, qpsf+1, ans+"("+r+","+c+")");
                boxes[r][c]=false;
            }
        }
        return count;
    }

    public static int nqueen_02(boolean[][] boxes,int tnq,int qpsf,String ans){
        if(qpsf==tnq){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        int n=boxes.length;
        for(int i=0;i<n*n;i++){
            int r=i/n;
            int c=i%n;
            if(!boxes[r][c] && isSafeToPlace(boxes,r,c)){
                boxes[r][c]=true;
                count+=nqueen_02(boxes,tnq, qpsf+1, ans+"("+r+","+c+")");
                boxes[r][c]=false;
            }
        }
        return count;
    }

    static boolean[] row,col,diag,Adiag;
    public static int nqueen_03(int n,int idx,int tnq,int qpsf,String ans){
        if(qpsf==tnq){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i<n*n;i++){
            int r=i/n;
            int c=i%n;

            if(!row[r] && !col[c] && !diag[r+c] && !Adiag[r-c+n-1]){
                row[r]=!row[r];
                col[c]=!col[c];
                diag[r+c]=!diag[r+c];
                Adiag[r-c+n-1]=!Adiag[r-c+n-1];
                count+=nqueen_03(n, i+1, tnq, qpsf+1, ans+"("+r+","+c+")");
                row[r]=!row[r];
                col[c]=!col[c];
                diag[r+c]=!diag[r+c];
                Adiag[r-c+n-1]=!Adiag[r-c+n-1];

            }
        }
        return count;
    }

    public static void nqueen_03(boolean[][] boxes){
        int n=boxes.length;
        int m=boxes[0].length;

        row=new boolean[n];
        col=new boolean[m];
        diag=new boolean[n+m-1];
        Adiag=new boolean[n+m-1];
        System.out.println(nqueen_03(n, 0, 4, 0, ""));
    }


    public static void queen()
    {
       // boolean[] boxes=new boolean[6];
       // System.out.println(queen_Combi(boxes, 0, 2, 0, ""));
        boolean[][] boxes=new boolean[4][4];
        //System.out.println(queen_Combi_2D(boxes, 0, 4, 0, ""));
        //System.out.println(queen_permu_2D(boxes, 4, 0, ""));
        //System.out.println(nqueen_01(boxes,0,4,0,""));
        //System.out.println(nqueen_02(boxes, 4, 0, ""));
        nqueen_03(boxes);
    }

    public static boolean isSafe(int[][] board,int x,int y,int val){
        for(int j=0;j<board[0].length;j++){
            if(board[x][j]==val) return false;
        }
        for(int i=0;i<board.length;i++){
            if(board[i][y]==val) return false;
        }
        int smi=x/3*3;
        int smj=y/3*3;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[smi+i][smj+j]==val) return false;
            }
        }
        return true;
    }
    public static void main(String[] args)
    {
        //basics();
        //maze();
        //coins();
        //queen();
        //sudoku();
    }
}