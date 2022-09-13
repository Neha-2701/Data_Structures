
public class StringSet 
{
    public static void palindromic_substring(String str,boolean[][] dp)
    {
        int n=str.length();
        for(int gap=0;gap<str.length();gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(gap==0) dp[i][j]=true;
                else if(gap==1 && str.charAt(i)==str.charAt(j)) dp[i][j]=true;
                else dp[i][j]=str.charAt(i)==str.charAt(j) && dp[i+1][j-1];
            }
        }
    }

    //Leetcode 647
    public static int countSubstrings(String str)
    {
        int n=str.length();
        boolean[][] dp=new boolean[n][n];
        int count=0;
        for(int gap=0;gap<str.length();gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(gap==0) dp[i][j]=true;
                else if(gap==1 && str.charAt(i)==str.charAt(j)) dp[i][j]=true;
                else dp[i][j]=str.charAt(i)==str.charAt(j) && dp[i+1][j-1];

                if(dp[i][j]) count++;
            }
        }
        return count;
    }

    //Leetcode 5
    public String longestPalindrome(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];
        int si=0,ei=0,length=0;
        for(int gap=0;gap<s.length();gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(gap==0) dp[i][j]=1;
                else if(gap==1 && s.charAt(i)==s.charAt(j)) dp[i][j]=2;
                else dp[i][j]= dp[i+1][j-1]+2;

                if(dp[i][j]>length)
                {
                    length=dp[i][j];
                    si=i;
                    ei=j;
                }
            }
        }
        return s.substring(si, ei+1);
    }

    
}
