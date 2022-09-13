import java.util.ArrayList;
import java.util.List;

public class leetcode_tree{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //Leetcode 236
    public static List<TreeNode> NodeToRootPath(TreeNode node,int data)
    {
        if(node==null)
            return null;
        if(node.val==data)
        {
            List<TreeNode> base=new ArrayList<>();
            base.add(node);
            return base;
        }
        List<TreeNode> left=NodeToRootPath(node.left,data);
        while(left!=null)
        {
            left.add(node);
            return left;
        }
        List<TreeNode> right=NodeToRootPath(node.right,data);
        while( right!=null)
        {
            right.add(node);
            return right;
        }
        return null;
    }
    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        List<TreeNode> list1=NodeToRootPath(node, p.val);
        List<TreeNode> list2=NodeToRootPath(node, q.val);
        
        
        int i=list1.size()-1;
        int j=list2.size()-1;
        TreeNode LCA=null;
        while(i>=0 && j>=0)
        {
            if(list1.get(i)==list2.get(j))
                LCA=list1.get(i);
            i--;
            j--;
        }
        return LCA; 
    }
}