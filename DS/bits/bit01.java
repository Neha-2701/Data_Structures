import java.util.ArrayList;
import java.util.Scanner;

public class bit01{

    //---------------josephus problem algorithm---------------
    public static int powerOFtwo(int n){
        int i=1;
        while(i*2<=n){
            i=i*2;
        }
        return i;
    }
    public static int solution(int n){
        int hp2=powerOFtwo(n);
        int l=n-hp2;
        return 2*l+1;
    }

    //--------------gray code----------------
    public static ArrayList<String>code(int n){
        if(n==1){
            ArrayList<String>bres=new ArrayList<>();
            bres.add("0");
            bres.add("1");
            return bres;
        }
        ArrayList<String> rres=code(n-1);
        ArrayList<String> mres=new ArrayList<>();
        for(int i=0;i<rres.size();i++){
            String rstr=rres.get(i);
            mres.add("0"+rstr);
        }
        for(int i=rres.size()-1;i>=0;i++){
            String rstr=rres.get(i);
            mres.add("1"+rstr);
        }
        return mres;
    }

    //-----------------all repeating except two----------
    public static void repeat (int[] arr)
    {
        int xxory=0;
        for(int val:arr)
        {
            xxory=xxory^val;
        }
        int rsbm=xxory &(-xxory);
        int x=0;
        int y=0;
        for(int val:arr)
        {
            if((val&rsbm)==0)
            {
                x=x^val;
            }
            else{
                y=y^val;
            }
        }
        if(x<y)
        {
            System.out.println(x);
            System.out.println(y);
        }
        else
        {
            System.out.println(y);
            System.out.println(x);
        }
    }
    
    //-------duplicate and missing --------------
    public static void dupli_mis(int[] arr)
    {
        int xor=0;
        for(int i=0;i<arr.length;i++)
        {
            xor^=arr[i];
        }
        for(int i=0;i<arr.length;i++)
        {
            xor^=i;
        }
        int rsb=xor^(-xor);
        int x=0;
        int y=0;
        for(int val:arr)
        {
            if((val&rsb)==0)
            {
                x=x^val;
            }
            else
            {
                y=y^val;
            }
        }
        for(int i=1;i<=arr.length;i++)
        {
            if((i&rsb)==0)
            {
                x=x^i;
            }
            else
            {
                y=y^i;
            }
        }
        for(int val:arr)
        {
            if(val==x)
            {
                System.out.println("missing no->"+y);
                break;    
            }
            else if(val==y)
            {
                System.out.println("repeating no->"+x);
                break;
            }
        }
    }

     //-------------reduce n to 1------------------
    public static int reduce(int n)
    {
        int res=0;
        while(n!=1)
        {
            if(n%2==0)
            {
                n=n/2;
            }
            else if(n==3)
            {
                res=2;
                break;
            }
            else if((n&3)==1)
            {
                n=n-1;
            }
            else if((n&3)==3)
            {
                n=n+1;
            }
            res++;
        }
        return res;
    }

    //----------sum of all pairs------------
    public static int pair(int[] arr)
    {
        int ans=0;
        for(int val:arr)
        {
            ans=ans^(2*val);
        }
        return ans;
    }

    //---------abbreviations------------------
    public static void solve(String str)
    {
        for(int i=0;i<(1<<str.length());i++)
        {
            StringBuilder sb=new StringBuilder();
            int count=0;
            for(int j=0;j<str.length();j++)
            {
                char ch=str.charAt(j);
                int b=str.length()-1-j;
                if((i&(1<<b))==0)
                {
                    if(count==0)
                    {
                        sb.append(sb);
                    }
                    else
                    {
                        sb.append(count);
                        sb.append(ch);
                        count=0;
                    }
                }
                else
                {
                    count++;
                }
            }
            if(count>0)
            {
                sb.append(count);
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args){
        //Scanner scn=new Scanner(System.in);
        //int n=scn.nextInt();
        //System.out.println(solution(n));
        int arr[]={1,2,2,3,4,6,5};
        //repeat(arr);
        dupli_mis(arr);
    }
}
