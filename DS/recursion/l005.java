import java.util.HashSet;

public class l005 {
    public static int wordBreak(String ques,int idx,String ans,HashSet<String>words,int len)
    {
        if(idx==ques.length())
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i-idx<=len && i<=ques.length();i++)
        {
            String s=ques.substring(idx, i);
            if(words.contains(s))
            {
                count+=wordBreak(ques, i, ans+s+" ", words, len);
            }
        }
        
        return count;
    }

    public static int wordBreak_02(String ques,String ans,HashSet<String>words,int len)
    {
        if(ques.length()==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=0;i<=len && i<=ques.length();i++)
        {
            String s=ques.substring(0, i);
            if(words.contains(s))
            {
                count+=wordBreak_02(ques.substring(i), ans+s+" ", words, len);
            }
        }
        
        return count;
    }

    public static void wordBreak()
    {
        String dictionary[]={"mobile","sam","sung","samsung","i","like","and"};
        HashSet<String>words=new HashSet<>();
        int len=0;
        for(String str:dictionary)
        {
            words.add(str);
            len=Math.max(len,str.length());
        }
        String ques="ilikesamsung";
        //System.out.println(wordBreak(ques, 0, "", words, len));
        System.out.println(wordBreak_02(ques, "", words, len));
    }
    
    public static void display(int[][] board)
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean isValid(int[][] board,int x,int y,int val)
    {
        for(int j=0;j<board[0].length;j++)
        {
            if(board[x][j]==val)
            {
                return false;
            }
        }
        for(int i=0;i<board.length;i++)
        {
            if(board[i][y]==val)
            {
                return false;
            }
        }
        int smi=(x/3)*3;
        int smj=(y/3)*3;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[smi+i][smj+j]==val)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static void solveSudoko(int[][] board,int i,int j)
    {
        if(i==board.length)
        {
            display(board);
            return;
        }
        int ni=0;
        int nj=0;
        if(j==board[0].length-1)
        {
            ni=i+1;
            nj=0;
        }
        else
        {
            ni=i;
            nj=j+1;
        }
        if(board[i][j]!=0)
        {
            solveSudoko(board, ni, nj);
        }
        else
        {
            for(int po=1;po<=9;po++)
            {
                if(isValid(board,i,j,po)==true)
                {
                    board[i][j]=po;
                    solveSudoko(board, ni, nj);
                    board[i][j]=0;
                }
            }
        }  
    }
    public static void main(String[] args)
    {
        //wordBreak();
        int[][] board = {{3, 0, 0, 6, 0, 0, 0, 9, 2},  
                         {5, 2, 0, 0, 0, 0, 4, 0, 8},  
                         {0, 8, 7, 0, 0, 0, 0, 3, 1},  
                         {0, 0, 3, 0, 1, 0, 0, 8, 0},  
                         {9, 0, 0, 8, 6, 3, 0, 0, 5},  
                         {0, 5, 0, 0, 9, 0, 6, 0, 0},  
                         {1, 3, 0, 0, 0, 0, 2, 5, 0},  
                         {0, 0, 0, 0, 0, 0, 0, 7, 4},  
                         {0, 0, 5, 2, 0, 6, 3, 0, 0}}; 
        solveSudoko(board, 0, 0);
    }
}
