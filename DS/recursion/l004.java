public class l004 {
    public static int queenCombination(boolean[] box,int idx,int qpsf,int tnq,String ans)
    {
        if(qpsf==tnq)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i<box.length;i++)
        {
            count+=queenCombination(box, i+1, qpsf+1, tnq, ans+"b"+i+"Q"+(qpsf)+" ");
        }
        return count;
    }

    public static int queenPermutation(boolean[] box,int qpsf,int tnq,String ans)
    {
        if(qpsf==tnq)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=0;i<box.length;i++)
        {
            if(!box[i])
            {
                box[i]=true;
                count+=queenPermutation(box,  qpsf+1, tnq, ans+"b"+i+"Q"+(qpsf)+" ");
                box[i]=false;
            }
        }
        return count;
    }

    public static int queenCombination2D(boolean[][] box,int idx,int qpsf,int tnq,String ans)
    {
        if(qpsf==tnq)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        int n=box.length;
        for(int i=idx;i<n*n;i++)
        {
            int r=i/n;
            int c=i%n;
            count+=queenCombination2D(box, i+1, qpsf+1, tnq, ans+"("+r+","+c+")");
        }
        return count;
    }

    public static int queenPermutation2D(boolean[][] box,int qpsf,int tnq,String ans)
    {
        if(qpsf==tnq)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        int n=box.length;
        for(int i=0;i<n*n;i++)
        {
            int r=i/n;
            int c=i%n;
            if(!box[r][c])
            {
                box[r][c]=true;
                count+=queenPermutation2D(box,  qpsf+1, tnq, ans+"b"+i+"Q"+(qpsf)+" ");
                box[r][c]=false;
            }
        }
        return count;
    }

    public static boolean isSafetoPlaceQueen(boolean[][] box,int r,int c)
    {
        int[][] dirA={{0,-1},{-1,0},{-1,-1},{-1,1}};
        for(int d=0;d<dirA.length;d++)
        {
            for(int rad=0;rad<=box.length;rad++)
            {
                int x=r+rad*dirA[d][0];
                int y=c+rad*dirA[d][1];

                if(x>=0 && y>=0 && x<box.length && y<box[0].length)
                {
                    if(box[x][y])
                        return false;
                }
                else
                {
                    break;
                }
            }
        }
        return true;
    }

    public static int Nqueen_01(boolean[][] box,int idx,int qpsf,int tnq,String ans)
    {
        if(qpsf==tnq)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        int n=box.length;
        for(int i=idx;i<n*n;i++)
        {
            int r=i/n;
            int c=i%n;
            if(!box[r][c] && isSafetoPlaceQueen(box, r, c))
            {
                box[r][c]=true;
                count+=Nqueen_01(box,i+1, qpsf+1, tnq, ans+"b"+i+"Q"+(qpsf)+" ");
                box[r][c]=false;
            }
        }
        return count;
    }

    static boolean[] rowA,colA,diagA,adiagA;
    public static int Nqueen_03(int n,int idx,int qpsf,int tnq,String ans)
    {
        if(qpsf==tnq)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=idx;i<n*n;i++)
        {
            int r=i/n;
            int c=i%n;
            if(!rowA[r] && !colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
            {
                rowA[r]=!rowA[r];
                colA[c]=!colA[c];
                diagA[r+c]=!diagA[r+c];
                adiagA[r-c+n-1]=!adiagA[r-c+n-1];

                count+=Nqueen_03(n, i+1, qpsf+1, tnq, ans+"("+r+","+c+")");

                rowA[r]=!rowA[r];
                colA[c]=!colA[c];
                diagA[r+c]=!diagA[r+c];
                adiagA[r-c+n-1]=!adiagA[r-c+n-1];
            }
        }
        return count;
    }

    public static int Nqueen_04(int n,int r,int tnq,String ans)
    {
        if(tnq==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int c=0;c<n;c++)
        {
            if(!colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
            {
                colA[c]=!colA[c];
                diagA[r+c]=!diagA[r+c];
                adiagA[r-c+n-1]=!adiagA[r-c+n-1];

                count+=Nqueen_04(n, r+1, tnq-1, ans+"("+r+","+c+")");

                colA[c]=!colA[c];
                diagA[r+c]=!diagA[r+c];
                adiagA[r-c+n-1]=!adiagA[r-c+n-1];
            }
        }
        return count;
    }

    public static int Nqueen_04_subseq(int n,int r,int tnq,String ans)
    {
        if(tnq==0||r>=n)
        {
            if(tnq==0)
            {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count=0;
        for(int c=0;c<n;c++)
        {
            if(!colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
            {
                colA[c]=!colA[c];
                diagA[r+c]=!diagA[r+c];
                adiagA[r-c+n-1]=!adiagA[r-c+n-1];

                count+=Nqueen_04(n, r+1, tnq-1, ans+"("+r+","+c+")");

                colA[c]=!colA[c];
                diagA[r+c]=!diagA[r+c];
                adiagA[r-c+n-1]=!adiagA[r-c+n-1];
                count+=Nqueen_04_subseq(n, r+1, tnq, ans+"("+r+","+c+")");
            }
        }
        return count;
    }

    static int colN=0,daigN=0,adaigN=0;
    public static int Nqueen_05_bits(int n,int r,int tnq,String ans)
    {
        if(tnq==0)
        {
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int c=0;c<n;c++)
        {
            if((colN&(1<<c))==0 && (daigN&(1<<(r+c)))==0 && (adaigN&(1<<(r-c+n-1)))==0)
            {
                colN^=(1<<c);
                daigN^=(1<<(r+c));
                adaigN^=(1<<(r-c+n-1));
                
                count+=Nqueen_05_bits(n, r+1, tnq-1, ans+"("+r+","+c+")");

                colN^=(1<<c);
                daigN^=(1<<(r+c));
                adaigN^=(1<<(r-c+n-1));
            }
        }
        return count;
    }


    public static void nqueen()
    {
        boolean[][] box=new boolean[4][4];
        int tnq=4;
        int n=box.length;
        int m=box[0].length;

        rowA=new boolean[n];
        colA=new boolean[m];
        diagA =new boolean[n+m-1];
        adiagA=new boolean[n+m-1];
        //System.out.println(Nqueen_03(n, 0, 0, tnq,""));
        //System.out.println(Nqueen_04(n, 0, tnq,""));
        //System.out.println(Nqueen_04_subseq(n, 0, tnq,""));
        System.out.println(Nqueen_05_bits(4,4, 4, ""));
    }



   
    public static void main(String[] args)
    {
        //boolean[] box=new boolean[6];
        //int tnq=3;
        //System.out.println(queenCombination(box,0, 0, tnq, " "));
        //System.out.println(queenPermutation(box, 0, tnq, " "));
        //boolean[][] box=new boolean[4][4];
        //int tnq=4;
        //System.out.println(queenCombination2D(box,0, 0, tnq, " "));
        //System.out.println(queenPermutation2D(box, 0, tnq, " "));
        //System.out.println(Nqueen_01(box,0, 0, tnq, " "));
        nqueen();
    }
}
