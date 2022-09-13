import java.util.PriorityQueue;
import java.util.Collections;

public class basicsOfPQ
{
    void set1(int[] arr)
    {
        //PriorityQueue<Integer> pq=new PriorityQueue<>();        //minPQ
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->      //maxPQ
        {
            return b-a;
        });
        for(int ele:arr)
        {
            pq.add(ele);
        }
        while(pq.size()!=0)
        {
            System.out.println(pq.remove()+" ");
        }
    }

    public static class pair implements Comparable<pair>
    {
        int val1=0;
        int val2=0;

        pair(int val1,int val2)
        {
            this.val1=val1;
            this.val2=val2;
        }
        @Override
        public int compareTo(pair o)
        {
            return this.val1-o.val1;
        }
    }

    public static void set2(int[][] arr)
    {
        PriorityQueue<pair> pq=new PriorityQueue<>();
        for(int[] ele:arr)
        {
            pq.add(new pair(ele[0],ele[1]));
        }
        while(pq.size()!=0)
        {
            pair p=pq.peek();
            System.out.println(p.val1+" "+p.val2);
            pq.remove();
        }
    }
}