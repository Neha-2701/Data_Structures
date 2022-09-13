import java.util.ArrayList;
public class l002{

    ///------------------maze-path-complete-approach------------------

    public static ArrayList<String>mazePath_HV(int sr,int sc,int er,int ec)
    {
        if(sr==er&&sc==ec)
        {
            ArrayList<String>base=new ArrayList<>();
            base.add(" ");
            return base;
        }
        ArrayList<String>myAns=new ArrayList<>();
        if(sc+1<=ec)
        {
            ArrayList<String>Horizontal=mazePath_HV(sr, sc+1, er, ec);
            for(String s:Horizontal)
            {
                myAns.add("H"+s);
            }
        }
        if(sr+1<=er)
        {
            ArrayList<String>Vertical=mazePath_HV(sr+1,sc,er,ec);
            
                for(String s:Vertical)
                {
                    myAns.add("V"+s);
                }     
        }
        if(sr+1<=er && sc+1<=ec)
        {
            ArrayList<String>Diagonal=mazePath_HV(sr+1,sc+1,er,ec);
            
                for(String s:Diagonal)
                {
                    myAns.add("D"+s);
                }     
        }
        return myAns;
    }

    ///-----------maze-path-easy-way-to-do-the-same---------------

    public static int mazepath_HVD_02(int sr,int sc,int er,int ec,String ans)
    {
        if(sr==er && sc==ec)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        if(sr+1<=er)
        {
            count+=mazepath_HVD_02(sr+1,sc,er,ec,ans+"V");
        }
        if(sc+1<=ec)
        {
            count+=mazepath_HVD_02(sr,sc+1,er,ec,ans+"H");
        }
        if(sr+1<=er && sc+1<=ec)
        {
            count+=mazepath_HVD_02(sr+1,sc+1,er,ec,ans+"D");
        }
        return count;
    }

    ///---------------maze-path-with-multi-jumps---------------

    public static int mazepath_HVD_jump(int sr,int sc,int er,int ec,String ans)
    {
        if(sr==er && sc==ec)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int jump=1;sc+jump<=ec;jump++)
        {
            count+=mazepath_HVD_02(sr,sc+jump,er,ec,ans+"H"+jump);
        }
        for(int jump=1;sr+jump<=er;jump++)
        {
            count+=mazepath_HVD_02(sr+jump,sc,er,ec,ans+"V"+jump);
        }
        for(int jump=1;sr+jump<=er && sc+jump<=ec;jump++)
        {
            count+=mazepath_HVD_02(sr+jump,sc+jump,er,ec,ans+"D"+jump);
        }
        return count;
    }

    

    public static void main(String[] args)
    {
        System.out.println("hello");
        //System.out.println(mazePath_HV(0,0,2,2));
        //System.out.println(mazepath_HVD_02(0,0,2,2,""));
        //System.out.println(mazepath_HVD_jump(0,0,2,2,""));
        //System.out.println(nokiaKeypad("267", 0, ""));
    }
}