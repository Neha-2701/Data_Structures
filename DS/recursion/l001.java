import java.util.ArrayList;
public class l001 {
     ///-----------------------------set-1-----------------------------

    public static void printIncreasing(int a,int b)
    {
        if(a == b+1)
        {
            return;
        }
        System.out.println(a);
        printIncreasing(a+1, b);
    }
 
    public static void printDecreasing(int a,int b)
    {
        if(a == b+1)
        {
            return;
        }
        printDecreasing(a+1, b);
        System.out.println(a);
    }
 
    public static void printOddEven(int a,int b)
    {
        if(a==b+1)
        {
            return;
        }
        if(a%2!=0)
        {
            System.out.println(a);
        }
        printOddEven(a+1, b);
        if(a%2==0)
        {
            System.out.println(a);
        }     
    }
 
    public static int factorial(int n)
    {
        return n==0 ? 1:factorial(n-1)*n;
    }
 
    public int power(int a,int b)
    {
        return b==0 ? 1:power(a,b-1);
    }
 
    public int power_better(int a,int b)
    {
        if(b==0)
        {
            return 1;
        }
        int ans=power_better(a, b/2);
        ans*=ans;
        return b%2 !=0 ? ans * a : ans;
    }
 
    ///-------------------------set-2---------------------------------
 
    public static void display(int[] arr,int idx)
    {
        if(idx==arr.length) return;
        System.out.println(arr[idx]);
        display(arr,idx+1);
    }
 
    public static int maximum(int[] arr,int idx)
    {
        if(idx==arr.length)
        {
            return -(int)1e9;
        }
        return Math.max(arr[idx],maximum(arr, idx+1));
    }
 
    public static boolean find(int[] arr,int idx,int data)
    {
        if(idx==arr.length) return false;
        if(idx==data) return true;
        return find(arr,idx+1,data);
    }
 
    public static int firstIndex(int[] arr,int idx,int data)
    {
        if(idx==arr.length) return -1;
        if(arr[idx]==data) return idx;
        return firstIndex(arr, idx+1, data);
    }
 
    public static int lastIndex(int[] arr,int idx,int data)
    {
        if(idx==arr.length) return -1;
        int ans=lastIndex(arr, idx+1, data);
        if(ans!=-1) return ans;
 
        return arr[idx]==data?idx:-1;
    }
 
    public static int[] allIndex(int[] arr,int idx,int data,int count)
    {
        if(idx==arr.length)
        {
            int[] base=new int[count];
            return base;
        }
        if(arr[idx]==data)
        {
            count++;
        }
        int[] smallAns=allIndex(arr, idx, data, count);
        if(arr[idx]==data)
        {
            smallAns[count-1]=idx;
        }
        return smallAns;
    }
    ///----------------subsequence-of-a-string----------------

    public static ArrayList <String> subsequence(String str,int idx)
    {
        if(idx==str.length())
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
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

    ///--------subsequence-of-a-string-with-less-complexicity-------------

    public static int subsequence(String str,String ans)
    {
        if(str.length()==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        count+=subsequence(str.substring(1), ans);
        count+=subsequence(str.substring(1), ans+str.charAt(0));
        return count;
    }

    ///------------------keypad-combinations--------------------------

    static String[] words={":;","##","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz","&%","#@$"};
    public static int nokiaKeypad(String str,int idx,String ans)
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
            count+=nokiaKeypad(str, idx+1, ans+word.charAt(i));
        }
        return count;
    }

    ///--------------keypad-combinations-with-arraylist--------------

    public static ArrayList<String>nokiaKeypad_(String str,int idx,String ans)
    {
        if(idx==str.length())
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        int chi=str.charAt(idx)-'0';
        String word=words[chi];


        ArrayList<String> smallAns=nokiaKeypad_(str, idx+1, ans);
        ArrayList<String> myAns=new ArrayList<>(smallAns);
        for(String s:myAns)
        {
            for(int i=0;i<word.length();i++)
            {
                myAns.add(word.charAt(i)+s);
            }
        }
        return myAns;
    }
}