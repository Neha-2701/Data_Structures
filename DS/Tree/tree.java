import java.util.ArrayList;
import java.util.LinkedList;
public class tree{
    public static class Node{
        int data=0;
        Node left=null;
        Node right=null;
        Node(int data){
            this.data=data;
        }
    }
    static int idx=0;
    public static Node constructTree(int[] arr)
    {
        if(idx>=arr.length || arr[idx]==-1)
        {
            idx++;
            return null;
        }
        Node node=new Node(arr[idx++]);
        node.left=constructTree(arr);
        node.right=constructTree(arr);
        return node;
    }

    public static void display(Node node)
    {
        if(node==null)
        {
            return;
        }
        StringBuilder sb=new StringBuilder();
        sb.append(node.left==null?".":node.left.data);
        sb.append("<-"+node.data+"->");
        sb.append(node.right==null?".":node.right.data);
        System.out.println(sb);
        display(node.left);
        display(node.right);
    }

    public static int size(Node node){
        return node==null?0:size(node.left)+size(node.right)+1;
    }

    public static int height(Node node){
        return node==null?-1:height(node.left)+height(node.right);
    }

    public static boolean find(Node node,int data)
    {
        if(node==null)
        {
            return false;
        }
        if(node.data==data)
        {
            return true;
        }
        return find(node.left,data)||find(node.right,data);
    }

    public static boolean nodeToRootPath(Node node,int data,ArrayList<Node> ans)
    {
        if(node==null)
        {
            return false;
        }
        if(node.data==data)
        {
            ans.add(node);
            return true;
        }
        boolean res=nodeToRootPath(node.left, data, ans) || nodeToRootPath(node.right, data, ans);
        if(res)
        {
            ans.add(node);
        }
        return res;
    }

    public static ArrayList<Node> nodeToRootPath02(Node node,int data)
    {
        if(node==null)
            return null;
        if(node.data==data)
        {
            ArrayList<Node>base=new ArrayList<>();
            base.add(node);
            return base;
        }
        ArrayList<Node> left=nodeToRootPath02(node.left, data);
        if(left!=null)
        {
            left.add(node);
            return left;
        }
        ArrayList<Node> right=nodeToRootPath02(node.right, data);
        if(right!=null)
        {
            right.add(node);
            return right;
        }
        return null;
    }

    public static boolean rootToNodePath(Node node,int data,ArrayList<Node> ans)
    {
        if(node==null)
        {
            return false;
        }
        if(node.data==data)
        {
            ans.add(node);
            return true;
        }
        ans.add(node);
        boolean res=rootToNodePath(node.left, data, ans) || rootToNodePath(node.right, data, ans);
        if(!res)
        {
            ans.remove(ans.size()-1);
        }
        return res;
    }

    public static void LCA (Node node,int a,int b)
    {
        ArrayList<Node> list1=new ArrayList<>();
        ArrayList<Node> list2=new ArrayList<>();

        nodeToRootPath(node, a, list1);
        nodeToRootPath(node, b, list2);

        int i=list1.size()-1;
        int j=list2.size()-1;

        Node LCA=null;

        while(i>=0 && j>=0)
        {
            if(list1.get(i)==list2.get(j))
            {
                LCA=list1.get(i);
            }
            i--;
            j--;
        }
        System.out.println(LCA.data);
    }

    public static void kdown(Node node,Node block,int k,ArrayList<Integer>ans)
    {
        if(node==null || node==block || k<0)
        {
            return;
        }
        if(k==0)
        {
            ans.add(node.data);
            return;
        }
        kdown(node.left, block, k-1, ans);
        kdown(node.right, block, k-1, ans);
    }

    public static void kFar(Node node,int data,int k)
    {
        ArrayList<Node> list=new ArrayList<>();
        nodeToRootPath(node, data, list);
        
        ArrayList<Integer> ans=new ArrayList<>();
        Node prev=null;
        for(int i=0;i<list.size();i++)
        {
            kdown(list.get(i),prev,k-i,ans);
            prev=list.get(i);
        }
    }

    public static int kFar2(Node node,int data,int k,ArrayList <Integer>ans)
    {
        if(node==null)
            return -1;
        if(node.data==data)
        {
            kdown(node, null, k, ans);
            return 1;
        }
        int ld=kFar2(node.left,data,k,ans);
        if(ld!=-1)
        {
            kdown(node, node.left, k-ld, ans);
            return ld+1;
        }
        int rd=kFar2(node.right, data, k, ans);
        if(rd!=-1)
        {
            kdown(node, node.right, k-rd, ans);
            return rd+1;
        }
        return -1;
    }

    public static int diameter_01(Node node)
    {
        if(node==null) return 0;
        int ld=diameter_01(node.left);
        int rd=diameter_01(node.right);

        int lh=height(node.left);
        int rh=height(node.right);

        return Math.max(Math.max(ld, rd), lh+rh+2);
    }

    public static int[] diameter_02(Node node)
    {
        if(node==null) return new int[] {0,-1};
        int[] lres=diameter_02(node.left);
        int[] rres=diameter_02(node.right);

        int dia = Math.max(Math.max(lres[0],rres[0]),lres[1] + rres[1] + 2);
        int hei=Math.max(lres[1],rres[1])+1;

        return new int[] {dia,hei};
    }

    static int diaAns=0;
    public static int diameter_03(Node node)
    {
        if(node==null) return -1;
        int lh=diameter_03(node.left);
        int rh=diameter_03(node.right);

        diaAns=Math.max(diaAns,lh+rh+1);
        return Math.max(lh,rh)+1;
    }

    public static int diameter_04(Node node,int[] diaAns)
    {
        if(node==null) return -1;

        int lh=diameter_04(node.left, diaAns);
        int rh=diameter_04(node.right, diaAns);

        diaAns[0]=Math.max(diaAns[0],lh+rh+2);
        return Math.max(lh,rh)+1;
    }

    public boolean hasPathSum(Node node,int sum)
    {
        if(node==null) return false;
        if(node.left==null && node.right==null && sum-node.data==0)
        {
            return true;
        }
        return hasPathSum(node.left, sum-node.data) || hasPathSum(node.right, sum-node.data);
    }

    public void pathSum(Node node,int sum,ArrayList<ArrayList<Integer>> res,ArrayList<Integer> ans)
    {
        if(node==null)
        {
            return;
        }
        if(node.left==null && node.right==null && sum-node.data==0)
        {
            ArrayList<Integer> base=new ArrayList<>();
            base.add(node.data);
            res.add(base);
        }
        ans.add(node.data);
        pathSum(node.left, sum-node.data, res, ans);
        pathSum(node.right, sum-node.data, res, ans);
        ans.remove(ans.size()-1);
    }
//////------------doubt-----------------
    static int maxLL=-(int)1e8;
    public static int leafToleaf(Node node)
    {
        if(node==null) return -(int)1e8;
        if(node.left==null && node.right==null)
        {
            return node.data;
        }
        int NodeToleafleft=leafToleaf(node.left);
        int NodeToleafright=leafToleaf(node.right);

        if(node.left!=null && node.right!=null )
        {
            maxLL=Math.max(maxLL,NodeToleafleft+NodeToleafright+node.data);
        }
        return Math.max(NodeToleafleft,NodeToleafright)+node.data;
    }

    //------------------BFS-------------------------
    public static void BFS_01(Node node)
    {
        LinkedList<Node>que=new LinkedList<>();
        que.addLast(node);
        while(que.size()!=0)
        {
            Node vtx=que.removeFirst();
            System.out.print(vtx.data+" ");
            if(vtx.left!=null)
                que.addLast(vtx.left);
            if(vtx.right!=null)
                que.addLast(vtx.right);
        }
    }

    public static void BFS_02(Node node)
    {
        LinkedList<Node>que=new LinkedList<>();
        que.addLast(node);
        que.addLast(null);

        while(que.size()!=1)
        {
            Node vtx=que.removeFirst();
            System.out.print(vtx.data+" ");
            if(vtx.left!=null)
                que.addLast(vtx.left);
            if(vtx.right!=null)
                que.addLast(vtx.right);
            if(que.getFirst()==null)
            {
                System.out.println();
                que.removeFirst();
                que.addLast(null);
            }
        }
    }

    public static void BFS_03(Node node)
    {
        LinkedList<Node>que=new LinkedList<>();
        que.addLast(node);
        int level=0;

        while(que.size()!=1)
        {
            int size=que.size();
            System.out.print("level"+level+"-> ");
            while(size-->0)
            {
                Node vtx=que.removeFirst();
                System.out.print(vtx.data+" ");
                if(vtx.left!=null)
                    que.addLast(vtx.left);
                if(vtx.right!=null)
                    que.addLast(vtx.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void widthDiagonal(Node node,int level,int[] res)
    {
        if(node ==null)  return ;

        res[0]=Math.min(level,res[0]);
        widthDiagonal(node.left, level-1, res);
        widthDiagonal(node.right, level+1, res);
    }
    public static void DiagonalView_(Node node,int level,ArrayList<Integer>[] ans)
    {
        if(node==null) return ;
        ans[level].add(node.data);
        DiagonalView_(node.left, level-1, ans);
        DiagonalView_(node.right, level, ans);
    }
    public static void DiagonalView(Node node)
    {
        int[] res=new int[1];
        widthDiagonal(node, 0, res);
        ArrayList<Integer>[] ans=new ArrayList[0-res[0]+1];
        for(int i=0;i<ans.length;i++)
            ans[i]=new ArrayList<>();
        DiagonalView_(node, -res[0], ans);
    }

    Node prev=null;
    public boolean isValidBST(Node root)
    {
        if(root==null) return true;
        if(!isValidBST(root.left)) return false;
        if(prev!=null && prev.data>=root.data) return false;
        prev=root;
        if(!isValidBST(root.right)) return false;
        return true;
    }

    public class BSTpair{
        boolean isBST=true;
        long min=(long)1e18;
        long max=-(long)1e18;
    }

    public BSTpair isValidBST_(Node root)
    {
        if(root==null) return new BSTpair();
        BSTpair left=isValidBST_(root.left);
        BSTpair right=isValidBST_(root.right);

        BSTpair myAns=new BSTpair();
        if(!left.isBST || !right.isBST || left.max>=root.data || right.min<=root.data)
            myAns.isBST=false;
           
        myAns.min=Math.min(left.min,root.data);
        myAns.max=Math.max(right.max,root.data);

        return myAns;
    }

} 