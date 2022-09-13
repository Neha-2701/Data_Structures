import java.util.ArrayList;
import java.util.List;

public class leetcode{
    List<List<Integer>>res=new ArrayList<>();
    List<Integer>smallAns=new ArrayList<>();
    public void permute_(int[] nums,int count,Boolean[] vis)
    {
        if(count==nums.length)
        {
            res.add(smallAns);
            return;
        }
        for(int i=0;i<nums.length;i++)
        {
            if(vis[i]==false)
            {
                vis[i]=true;
                smallAns.add(nums[i]);
                permute_(nums, count+1, vis);
                smallAns.remove(nums[i]);
                vis[i]=false;
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) 
    {
        Boolean[] vis=new Boolean[nums.length];
        permute_(nums, 0, vis);
        return res;

    }

    //Leetcode 47
    public void permute__(int[] nums,int count,Boolean[] vis)
    {
        if(count==nums.length)
        {
            res.add(smallAns);
            return;
        }
        int prev=-(int)1e9;
        for(int i=0;i<nums.length;i++)
        {
            if(vis[i]==false && prev!=nums[i])
            {
                vis[i]=true;
                smallAns.add(nums[i]);
                permute_(nums, count+1, vis);
                smallAns.remove(nums[i]);
                vis[i]=false;
                prev=nums[i];
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) 
    {
        Boolean[] vis=new Boolean[nums.length];
        permute__(nums, 0, vis);
        return res;

    }
    
}