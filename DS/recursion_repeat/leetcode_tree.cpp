#include<vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

   vector<TreeNode* > NodeToRoot(TreeNode* root,int data)
{
    if(root==nullptr)
        return {};
    if(root->val==data)
    {
        vector<TreeNode* > base;
        base.push_back(root);
        return base;
    }
    vector<TreeNode* > left=NodeToRoot(root->left,data);
    while(left.size()!=0)
    {
        left.push_back(root);
        return left;
    }
    vector<TreeNode* > right=NodeToRoot(root->right,data);
    while(right.size()!=0)
    {
        right.push_back(root);
        return right;
    }
    return {};
}
TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) 
{
    vector<TreeNode *> list1=  NodeToRoot(root,p->val);
    vector<TreeNode *> list2=  NodeToRoot(root,q->val);
    TreeNode* LCA=nullptr;
    int i=list1.size()-1;
    int j=list2.size()-1;

    while(i>=0 && j>=0)
    {
        if(list1[i]==list2[j])
        {
            LCA= list1[i];
        }
        i--;
        j--;
    }
    return LCA;
}

//L