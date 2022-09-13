struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
   TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 };

 //Leetcode 99
TreeNode *prev_=nullptr,*a=nullptr,*b=nullptr;
bool recoverTree_(TreeNode *root)
{
    if(root==nullptr) return false;
    if(recoverTree_(root->left)) return true;

    if(prev_!=nullptr && prev_->val >= root->val)
    {
        b=root;
        if(a==nullptr)
        {
            a=prev_;
        }
        else
        {
            return true;
        }
    }
    prev_=root;
    if(recoverTree_(root->right)) return true;

    return false;
}

void recoverTree(TreeNode* root) {
    recoverTree_(root);
    if(a!=nullptr)
    {
        int temp=a->val;
        a->val=b->val;
        b->val=temp;
    }
}

//Leetcode 701
TreeNode *insertIntoBST(TreeNode *root,int val)
{
    if(root==nullptr) return new TreeNode(val);

    if(root->val>val)
        root->left=insertIntoBST(root->left,val);

    if(root->val<val)
        root->right=insertIntoBST(root->right,val);

    return root;
}

//Leetcode 450........... //pass ni hua ye

int maximum(TreeNode *node)
{
    return node->right!=nullptr?maximum(node->right):node->val;
}

TreeNode *deleteNode(TreeNode *root,int val)
{
    if(root==nullptr) return nullptr;

    if(root->val>val)
        root->left=deleteNode(root->left,val);
    else if(root->val<val)
        root->right=deleteNode(root->right,val);
    else
    {
        if(root->left==nullptr || root->right==nullptr)
        {
            TreeNode *node=root->left!=nullptr ?root->left :root->right;
            delete root;
            return node;
        }
        int mVal=maximum(root->left);
        root->val=mVal;
        root->left=deleteNode(root->left,val);
    }
    return root;  
}

//leetcode 285
void allSolution(TreeNode *node,int data)
{
    TreeNode *curr=node;
    TreeNode *pred=nullptr;
    TreeNode *succ=nullptr;

    while(curr!=nullptr)
    {
        if(curr->val==data)
        {
            if(curr->left!=nullptr)
            {
                pred=curr->left;
                while(pred->right!=nullptr)
                {
                    pred=pred->right;
                }
            }
            if(curr->right!=nullptr)
            {
                succ=curr->right;
                while(succ->left!=nullptr)
                {
                    succ=succ->left;
                }
            }
            break;
        }
        else if(curr->val<data)
        {
            pred=curr;
            curr=curr->right;
        }
        else
        {
            succ=curr;
            curr=curr->left;
        }
    }
}


