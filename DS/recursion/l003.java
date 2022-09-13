public class l003
{
    public static int coinChangePermutation_01(int[] coins,int tar,String ans)
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
            {
                count+=coinChangePermutation_01(coins, tar-coins[i], ans+coins[i]);
            }
        }
        return count;
    }

    public static int coinChangeCombination_01(int[] coins,int idx,int tar,String ans)
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
            {
                count+=coinChangeCombination_01(coins,i, tar-coins[i], ans+coins[i]);
            }
        }
        return count;
    }

    public static int coinChangeCombinationSingle_01(int[] coins,int idx,int tar,String ans)
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
            {
                count+=coinChangeCombinationSingle_01(coins,i+1, tar-coins[i], ans+coins[i]);
            }
        }
        return count;
    }

    public static int coinChangePermutationSingle_01(int[] coins,boolean[] vis,int tar,String ans)
    {
        if(tar==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=0;i<coins.length;i++)
        {
            if(!vis[i] && tar-coins[i]>=0)
            {
                vis[i]=true;
                count+=coinChangePermutationSingle_01(coins,vis, tar-coins[i], ans+coins[i]);
                vis[i]=false;
            }
        }
        return count;
    }

    public static int coinChangePermutationSubSeq_01(int[] coins,int idx,int tar,String ans)
    {
        if(tar==0 || idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }   
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=coinChangePermutationSubSeq_01(coins,0, tar-coins[idx], ans+coins[idx]);
            count+=coinChangePermutationSubSeq_01(coins,idx+1, tar, ans);
        }
        

        return count;
    }

    public static int coinChangeCombinationSubSeq_01(int[] coins,int idx,int tar,String ans)
    {
        if(tar==0 || idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }   
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=coinChangeCombinationSubSeq_01(coins,idx, tar-coins[idx], ans+coins[idx]);
            count+=coinChangeCombinationSubSeq_01(coins,idx+1, tar, ans);
        }
        return count;
    }

    public static int coinChangeCombinationSingleSubSeq_01(int[] coins,int idx,int tar,String ans)
    {
        if(tar==0 || idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }   
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=coinChangeCombinationSingleSubSeq_01(coins,idx+1, tar-coins[idx], ans+coins[idx]);
            count+=coinChangeCombinationSingleSubSeq_01(coins,idx+1, tar, ans);
        }
        return count;
    }

    public static int coinChangePermutationSingleSubSeq_01(int[] coins,int idx,int tar,boolean[] vis,String ans)
    {
        if(tar==0 || idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }   
        int count=0;
        if(!vis[idx] && tar-coins[idx]>=0)
        {
            vis[idx]=true;
            count+=coinChangePermutationSingleSubSeq_01(coins,0, tar-coins[idx],vis, ans+coins[idx]);
            vis[idx]=false;
        }
        count+=coinChangePermutationSingleSubSeq_01(coins,idx+1, tar,vis, ans);
        return count;
    }

    public static int queenpermutation(boolean[] boxes,int tq,int idx,int qpsf,String ans)
    {
        if(tq==qpsf)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        
        for(int i=idx;i<boxes.length;i++)
        {
            count+=queenpermutation(boxes, tq, idx+1, qpsf+1, ans+"b"+i+"q"+(qpsf+1)+" ");
        }
        return count;
    }

    public static void main(String[] args)

    {
        //int[] coins={2,3,5,7};
        //int tar=10;
        boolean[] boxes= new boolean[6];
        //System.out.println(coinChangePermutation_01(coins, tar,""));
        //System.out.println(coinChangeCombination_01(coins,0, tar,""));
        //System.out.println(coinChangeCombinationSingle_01(coins,0, tar,""));
        //boolean[] vis=new boolean[4];
        //System.out.println(coinChangePermutationSingle_01(coins,vis, tar,""));
        //System.out.println(coinChangePermutationSubSeq_01(coins,0,tar,""));
        //System.out.println(coinChangeCombinationSubSeq_01(coins,0,tar,""));
        //System.out.println(coinChangeCombinationSingleSubSeq_01(coins,0,tar,""));
        //System.out.println(coinChangePermutationSingleSubSeq_01(coins,0,tar,vis,""));
        //System.out.println(queen(boxes,3,0,""));
        System.out.println(queenpermutation(boxes,2,0,0," "));
        
    }
}