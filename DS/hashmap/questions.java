import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.ArrayList;

public class questions {
    //Leetcode 378
    public int kthSmallest(int[][] matrix, int k) {
        int n=matrix.length;
        int m=matrix[0].length;
        PriorityQueue<Integer> pq=new PriorityQueue<>();
       for(int i=0;i<m*n;i++)
        {
            int idx=matrix[i/n][i%m];
            pq.add(idx);
        }
        while(k-->1)
        {
            pq.remove();
        }
        return pq.peek();
    }
    //Leetcode 349
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int ele:nums1)
        {
            map.put(ele,map.getOrDefault(ele,0)+1);
        }
        ArrayList<Integer> ans=new ArrayList<>();
        for(int ele:nums2)
        {
            if(map.containsKey(ele))
            {
                ans.add(ele);
                map.remove(ele);
            }
        }
        int[] arr=new int[ans.size()];
        int i=0;
        for(int ele:ans)
        {
            arr[i++]=ele;
        }
        return arr;
    }

    //Leetcode 350
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int ele:nums1)
        {
            map.put(ele,map.getOrDefault(ele,0)+1);
        }
        ArrayList<Integer> ans=new ArrayList<>();
        for(int ele:nums2)
        {
            if(map.containsKey(ele))
            {
                ans.add(ele);
                map.put(ele,map.get(ele)-1);
                if(map.get(ele)==0) map.remove(ele);
            }
        }
        int i=0;
        int[] arr=new int[ans.size()];
        for(int ele: ans)
            arr[i++]=ele;
        return arr;
            
    }

    //Leetcode 347
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int ele: nums)
            map.put(ele,map.getOrDefault(ele,0)+1);
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->{
            return a[1]-b[1];
        });
        for(int ele:map.keySet())
        {
            pq.add(new int[]{ele,map.get(ele)});
            if(pq.size()>k) pq.remove();
        }
        int[] ans=new int[k];
        int idx=0;
        while(pq.size()!=0)
        {
            int[] d=pq.remove();
            ans[idx++]=d[0];
        }
        return ans;   
    }
}
