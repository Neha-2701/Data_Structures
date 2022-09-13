

class questionTree{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //Leetcode 98
    class BSTpair{
        int maximum=-(int)1e8;
        int minimum=(int)1e8;
        boolean isBST=true;
    }

    public BSTpair isValidBST(TreeNode node)
    {
        if(node==null) return new BSTpair();

        BSTpair left=isValidBST(node.left);
        BSTpair right=isValidBST(node.right);

        BSTpair myAns=new BSTpair();
        if(!left.isBST || !right.isBST || left.maximum>=node.val || right.maximum<=node.val )
        {
            myAns.isBST=false;
            return myAns;
        }
        myAns.maximum=Math.max(right.maximum,node.val);
        myAns.minimum=Math.min(left.minimum,node.val);

        return myAns;
    }

    
}