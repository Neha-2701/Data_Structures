public class array
{
    public void swap(int[] arr,int i,int j)
    {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public void reverse(int[] arr,int i,int j)
    {
        while(i<j)
            swap(arr,i++,j--);
    }
    public void rotate(int[] arr,int k)
    {
        if(arr.length==0)
            return;
        int n=arr.length;
        k=(k%n+n)%n;
        reverse(arr,0,n-1);
        reverse(arr,0,k-1);
        reverse(arr,k,n-1);
    }

    public int maxSumInRotation(int[] arr)
    {
        int sum=0,rotatedSum=0,n=arr.length;
        for(int ele:arr)
            sum+=ele;
        for(int i=0;i<arr.length;i++)
            rotatedSum+=arr[i]*i;
        int maxSum=rotatedSum;
        for(int i=0;i<arr.length;i++)
        {
            rotatedSum=rotatedSum-sum+arr[i]*n;
            maxSum=Math.max(maxSum,rotatedSum-sum+arr[i]*n);
        }
        return maxSum;
    }
    //Leetcode 76
    public String minWindow(String s, String t) {
        int ns=s.length();
        int nt=t.length();
          
        int[] freq=new int[128];
        for(int i=0;i<nt;i++) freq[t.charAt(i)]++;

        int requirment=nt,si=0,ei=0,len=(int)1e8;
        int head=0;
        while(ei<ns)
        {
            if(freq[s.charAt(ei++)]-->0) requirment--;
            while(requirment==0)
            {
                if(ei-si<len) len=ei-(head=si);
                if(freq[s.charAt(si++)]++ == 0) requirment++;
            }
        }
        return len==(int)1e8?"":s.substring(head, head+len);
    }
}