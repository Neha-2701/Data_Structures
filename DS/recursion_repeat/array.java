import java.util.HashMap;
public class array{

    public void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public void reverse(int[] arr,int i,int j){
        while(j>i){
            swap(arr,i++,j--);
        }
    }

    public void rotate(int[] arr,int k){
        int n=arr.length;
        reverse(arr, 0, n-1);
        reverse(arr, 0, k-1);
        reverse(arr, k, n-1);
    }

    public int maxSumInRotation(int[] arr){
        int sum=0,rotatedSum=0,n=arr.length;
        for(int ele:arr) sum+=ele;
        for(int i=0;i<arr.length;i++) rotatedSum+=arr[i]*i;
        int maxSum=rotatedSum;
        for(int i=0;i<arr.length;i++)
            maxSum=Math.max(maxSum,rotatedSum-sum+arr[i]*n);
        return maxSum;
    }

    //Leetcode 76
    public String minWindow(String s,String t){
        int ns=s.length();
        int nt=t.length();

        int[] freq=new int[128];
        for(int i=0;i<nt;i++){
            freq[t.charAt(i)]++;
        }
        int requirement=nt,si=0,ei=0,len=(int)1e8;
        int head=0;
        while(ei<ns){
            if(freq[s.charAt(ei++)]-->0) requirement--;
            while(requirement==0){
                if(ei-si<len) len=ei-(head=si);
                if(freq[s.charAt(si++)]++==0) requirement++;
            }
        }
        return len==(int)1e8?"":s.substring(head, head+len);
    }

    //geeksforgeeks
    public int minWindow(String s){
        int n=s.length();
        int[] freq=new int[128];
        for(int i=0;i<n;i++) freq[s.charAt(i)]=1;
        int requirement=0,si=0,ei=0,len=(int)1e8;
        //int head=0;
        while(ei<n){
            if(freq[s.charAt(ei++)]-->0) requirement--;
            while(requirement==0){
                len=(ei-si<=len)?(ei-si):len;
                if(freq[s.charAt(si++)]++==0) requirement++;
            }
        }
        return len;
    }
    
    //leetcode 974
     public int subarraysDivByK(int[] A, int k) {
        if(A.length==0) return 0;
        int[] map=new int[k+1];
        map[0]=1;
        int sum=0,count=0;
        for(int i=0;i<A.length;i++){
            sum+=A[i];
            int SUM=(sum%k+k)%k;
            count+=map[SUM];
            map[SUM]++;
        }
        return count;
    }

    //leetcode 781
    public int numRabbits(int[] answers) {
        int n=answers.length;
        if(n==0) return 0;
        HashMap<Integer,Integer> map=new HashMap<>();
        int ans=0;
        for(int ele:answers){
            if(!map.containsKey(ele)){
                ans+=(ele+1);
                map.put(ele,1);
            }
            else
                map.put(ele,map.get(ele)+1);
            if(map.get(ele)==ele+1) map.remove(ele);
        }
        return ans;
    }

    //leetcode 930
    public int numSubarraysWithSum(int[] A, int S) {
        int n=A.length,ei=0,count=0,psum=0;
        if(n==0) return 0;
        int[] freq=new int[30000+1];
        freq[0]=1;
        while(ei<n){
            psum+=A[ei++];
            if(psum-S>=0)
            {
                count+=freq[psum-S];
            }
            freq[psum]++;
        }
        return count;
    }

    //leetcode 1004
    public int longestOnes(int[] arr, int K) {
        
        int n = arr.length, ei = 0, si = 0, len = 0;
        int zeroCount  = 0;
        while(ei < n){
            if(arr[ei++] == 0) zeroCount++;
            
            while(zeroCount > K){
                if(arr[si++] == 0) zeroCount--;    
            }
            
            len = Math.max(len, ei - si);
        } 
        return len;
    }

    //leetcode 1248
    public int numberOfSubarraysAtMost(int[] arr, int k) {
        int n = arr.length;
        int si = 0, ei = 0, oddCount = 0, res = 0;
    
        while(ei < n){
            if((arr[ei++] & 1) != 0) oddCount++;
    
            while(oddCount > k){
                if((arr[si++] & 1) != 0) oddCount--;
            }
    
            res += ei - si;
        }
    
        return res;
    }
    
    public int numberOfSubarrays(int[] arr, int k) {
        int n = arr.length;
        if(n == 0) return 0;
    
        return numberOfSubarraysAtMost(arr,k) - numberOfSubarraysAtMost(arr,k - 1);
    }
    public static void main(String[] args){
        
    }
}