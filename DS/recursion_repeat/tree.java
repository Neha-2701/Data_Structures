import java.util.ArrayList;

public class tree{
    public static class Node {
        int data=0;
        Node left=null;
        Node right=null;
        Node(int data){
            this.data=data;
        }
    }
    static int idx=0;
    public static Node constructNode(int[] arr)
    {
        if(idx>=arr.length || arr[idx]==-1)
        {
            idx++;
            return null;
        }
        Node node=new Node(idx++);
        node.left=constructNode(arr);
        node.right=constructNode(arr);
        return node;
    }

    public static void display(Node node)
    {
        if(node==null) return;
        StringBuilder sb=new StringBuilder();
        sb.append(node.left==null?".":node.left.data);
        sb.append("<-"+node.data+"->");
        sb.append(node.right==null?".":node.right.data);
        display(node.left);
        display(node.right);
    }

    public static int size(Node node)
    {
        return node==null?0:size(node.left)+size(node.right)+1;
    }

    public static int height(Node node)
    {
        return node==null?0:Math.max(size(node.left), size(node.right))+1;
    }

    public static boolean find(Node node,int data)
    {
        if(node==null) return false;
        if(node.data==data) return true;
        return find(node.left, data)|| find(node.right, data);
    }

    public static boolean NodeToRootPath(Node node,int data,ArrayList<Node> ans)
    {
        if(node==null)
            return false;
        if(node.data==data)
        {
            ans.add(node);
            return true;
        }
        boolean res=NodeToRootPath(node.left, data, ans) || NodeToRootPath(node.right, data, ans);
        if(res) ans.add(node);
        return res;
    }

    public static ArrayList<Node> NodeToRoot(Node node,int data)
    {
        if(node==null) return null;
        if(node.data==data)
        {
            ArrayList<Node> base=new ArrayList<>();
            base.add(node);
            return base;
        }
        ArrayList<Node> left=NodeToRoot(node.left, data);
        if(left!=null)
        {
            left.add(node);
            return left;
        }
        ArrayList<Node> right=NodeToRoot(node.right, data);
        if(right!=null)
        {
            right.add(node);
            return right;
        }
        return null;
    }

    public static boolean RootToNodePath(Node node,int data,ArrayList<Node> ans)
    {
        if(node==null)
            return false;
        if(node.data==data)
        {
            ans.add(node);
            return true;
        }
        ans.add(node);
        boolean res=RootToNodePath(node.left, data, ans) || RootToNodePath(node.right, data, ans);
        if(!res) ans.remove(ans.size()-1);
        return res;
    }

    public static void kdown(Node node,Node block,int k,ArrayList<Integer> ans)
    {
        if(node == block || k<0 || node==null) return ;
        if(k==0)
        {
            ans.add(node.data);
            return;
        }
        kdown(node.left, block, k-1, ans);
        kdown(node.right, block, k, ans);
    }
    public static void kFar(Node node,int data,int k)
    {
        ArrayList<Node> list=new ArrayList<>();
        NodeToRootPath(node, data,list);

        Node prev=null;
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            kdown(list.get(i),prev,k-i,ans);
            prev=list.get(i);
        }
    }

    public static int kFar2(Node node,int data,int k,ArrayList<Integer> ans)
    {
        if(node==null)
            return -1;
        if(node.data==data)
        {
            kdown(node, null, k, ans);
            return 1;
        }
        int ld=kFar2(node.left, data, k, ans);
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
}