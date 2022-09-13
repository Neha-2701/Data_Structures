#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode *random;

    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

//leetcode-876
ListNode* middle(ListNode* head)
{
    if(head==nullptr||head->next==nullptr)
    {
        return head;
    }
    ListNode *slow=head;
    ListNode *fast=head;
    while(fast->next!=nullptr && fast==nullptr)
    {
        slow->next=slow;
        fast->next->next=fast;
    }
    return slow;
}

ListNode* middle_02(ListNode* head)
{
    if(head==nullptr||head->next==nullptr)
    {
        return head;
    }
    ListNode *slow=head;
    ListNode *fast=head;
    while(fast->next!=nullptr && fast->next->next!=nullptr)
    {
        slow=slow->next;
        fast=fast->next->next;
    }
    return slow;
}

//leetcode-206
ListNode* reverseList(ListNode *head)
{
    if(head==nullptr||head->next==nullptr)
    {
        return head;
    }
    ListNode *prev=nullptr;
    ListNode *curr=head;
    ListNode *forw=nullptr;
    while(curr!=nullptr)
    {
        forw=curr->next;
        curr->next=prev;
        prev=curr;
        curr=forw;
    }
    return prev;
}

ListNode* reversedata(ListNode *head)
{
    ListNode *mid=middle_02(head);
    ListNode *thead=mid->next;
    mid->next=nullptr;

    thead=reverseList(thead);
    ListNode *c1=nullptr;
    ListNode *c2=nullptr;

    while(c1!=nullptr && c2!=nullptr)
    {
        int temp=c1->val;
        c1->val=c2->val;
        c2->val=temp;

        c1=c1->next;
        c2=c2->next;
    }
    thead=reverseList(thead);
    mid->next=thead;
}

//leetcode-234
bool ispalindrome(ListNode *head)
{
    if(head==nullptr||head->next==nullptr)
    {
        return head;
    }
    ListNode *mid=middle_02(head);
    ListNode *thead=mid->next;
    mid->next=nullptr;

    thead=reverseList(thead);
    ListNode *c1=head;
    ListNode *c2=thead;

    bool res=true;
    while(c1!=nullptr && c2!=nullptr)
    {
        if(c1->val!=c2->val)
        {
            res= false;
            break;
        }
        c1=c1->next;
        c2=c2->next;
    }
    thead=reverseList(thead);
    mid->next=thead;
    return res;
}

//leetcode 143
void reorderList(ListNode *head)
{
    if(head==nullptr||head->next==nullptr)
    {
        return;
    }
    ListNode *mid=middle_02(head);
    ListNode *thead=mid->next;
    mid->next=nullptr;

    thead=reverseList(thead);
    ListNode *c1=head;
    ListNode *c2=thead;

    ListNode *f1=nullptr;
    ListNode *f2=nullptr;

    while(c1!=nullptr && c2!=nullptr)
    {
        f1=c1->next;
        f2=c2->next;
        c1->next=c2;
        c2->next=f1;
        c1=f1;
        c2=f2;
    }
}

ListNode *th1=nullptr;
ListNode *tt1=nullptr;
ListNode *th2=nullptr;
ListNode *tt2=nullptr;

void addFirst(ListNode *node)
{
    if(th1==nullptr)
    {
        th1=node;
        tt1=node;
    }
    else
    {
        node->next=th1;
        th1=node;  
    }   
}

void addLast(ListNode *node)
{
    if(th2==nullptr)
    {
        th2=node;
        tt2=node;
    }
    else
    {
        tt2->next=node;
        th2=node;  
    }   
}

ListNode *orderList(ListNode *head)
{
    if(head==nullptr||head->next==nullptr)
    {
        return;
    }
    int cnt=0;
    ListNode *curr=head;
    while(curr!=nullptr)
    {
        ListNode *forw=curr->next;
        curr->next=nullptr;
        if(cnt==0)
            addLast(curr);
        else
        {
            addFirst(curr);
        }
        curr=forw;
        cnt=(cnt+1)%2;
    }
    tt2->next=th1;
    return th2;
}

//leetcode-21
ListNode *mergeTwoList(ListNode *l1,ListNode *l2)
{
    if(l1==nullptr||l2==nullptr)
    {
        return (l1==nullptr)?l2:l1;
    }
    ListNode *dummy=new ListNode(-1);
    ListNode *prev=dummy;

    ListNode *c1=l1;
    ListNode *c2=l2;

    while(c1!=nullptr && c2!=nullptr)
    {
        if(c1->val <c2->val)
        {
            prev->next=c1;
            c1=c1->next;
        }
        else
        {
            prev->next=c2;
            c2=c2->next;
        }
        prev=prev->next;  
    }
    if(c1!=nullptr)
    {
        prev->next=c1;
    }
    else
    {
        prev->next=c2;
    }
    return dummy->next;
}

ListNode *sortList(ListNode *head)
{
    if(head==nullptr || head->next==nullptr)
        return head;
    ListNode *mid=middle_02(head);
    ListNode *nhead=mid->next;
    mid->next=nullptr;

    return mergeTwoList(sortList(head),sortList(nhead));
}
int len(ListNode* head)
{
    int l=0;
    while(head!=nullptr)
    {
        l++;
        head=head->next;
    }
    return l;
}
ListNode *reverseKGroup(ListNode *head,int k)
{
    if(head==nullptr||head->next==nullptr||k<=1)
    {
        return head;
    }
    int l=len(head);
    if(l<k)
    {
        return head;
    }
    ListNode *curr=head;
    ListNode *oh=nullptr;
    ListNode *ot=nullptr;

    int K=k;
    while(curr!=nullptr && l>=k)
    {
        while(k-->0)
        {
            ListNode *next=curr->next;
            curr->next=nullptr;
            addFirst(curr);
            curr=next;
        }
        if(oh==nullptr)
        {
            oh=th1;
            ot=tt1;
        }
        else
        {
            ot->next=th1;
            oh=tt1;
            th1=nullptr;
            tt1=nullptr;
            l-=k;
            K=k;
        }
        ot->next=curr;
        return oh;
    }
}

ListNode* reverseBetween(ListNode* head,int m,int n)
{
    if(head==nullptr||head->next==nullptr||m==n)
    {
        return head;
    }
    ListNode *curr=head;
    ListNode *prev=nullptr;

    int idx=1;
    while(curr!=nullptr)
    {
        while(idx>=m && idx<=n)
        {
            ListNode *next=curr->next;
            curr->next=nullptr;
            addFirst(curr);
            curr=next;
            idx++;
        }
        if(idx>n)
        {
            if(prev==nullptr)
            {
                head=th1;
            }
            else
            {
                prev->next=th1;
            }
            tt1->next=curr;
            break; 
        }
        prev=curr;
        curr=curr->next;
        idx++;
    }
    return head;
}
//leetcode 141
bool hasCycle(ListNode* head)
{
    if(head==nullptr|| head->next==nullptr)
    {
        return false;
    }
    ListNode *slow=head;
    ListNode *fast=head;

    while(fast!=nullptr && fast->next!=nullptr)
    {
        slow=slow->next;
        fast=fast->next->next;
        if(slow==fast)
            break;
    }
    return slow==fast;
}

//leetcode 142
ListNode *detectCycle(ListNode* head)
{
    if(head==nullptr|| head->next==nullptr)
    {
        return nullptr;
    }
    ListNode *slow=head;
    ListNode *fast=head;

    while(fast!=nullptr && fast->next!=nullptr)
    {
        slow=slow->next;
        fast=fast->next->next;
        if(slow==fast)
            break;
    }
    if(slow!=fast)
    {
        return nullptr;
    }
    slow=head;
    while(slow!=fast)
    {
        slow=slow->next;
        fast=fast->next;
    }
    return slow;
}

//insight
ListNode *detectCycle1(ListNode* head)
{
    if(head==nullptr|| head->next==nullptr)
    {
        return nullptr;
    }
    ListNode *slow=head;
    ListNode *fast=head;

    int m=0,n=0,x=0,y=0,disofSlow=0;
    while(fast!=nullptr && fast->next!=nullptr)
    {
        slow=slow->next;
        fast=fast->next->next;
        disofSlow++;
        if(slow==fast)
            break;
    }
    if(slow!=fast)
    {
        return nullptr;
    }
    slow=head;
    ListNode *node=fast;
    while(slow!=fast)
    {
        if(fast==node)
        {
            m++;
        }
        x++;
        slow=slow->next;
        fast=fast->next;
    }
    y=disofSlow-x;
    return slow;
}

//leetcode 160
ListNode *getIntersectionNode(ListNode *headA,ListNode *headB)
{
    if(headA==nullptr|| headB==nullptr)
    {
        return nullptr;
    }
    ListNode *prev=headA;
    ListNode *curr=headA;

    while(curr!=nullptr)
    {
        prev=curr;
        curr=curr->next;
    }
    prev->next=headB;
    ListNode *ans=detectCycle1(headA);
    prev->next=nullptr;
    return ans;
}

ListNode *removenNthfromEnd(ListNode *head,int n)
{
   if(n==0||head==nullptr)
   {
       return head;
   } 
   ListNode *c1=head;
   ListNode *c2=head;
   while(n-->0)
   {
       c2=c2->next;
   }
   if(c2==nullptr)
   {
       return head->next;
   }
   while(c2->next!=nullptr)
   {
       c1=c1->next;
       c2=c2->next;
   }
   c1->next=c1->next->next;
   return head;
}

//leetcode-61
ListNode *rotateRight(ListNode *head,int k)
{
    if(head==nullptr || head->next==nullptr || k==0)
    {
        return head;
    }
    int len=0;
    ListNode *curr=head;
    while(curr!=nullptr)
    {
        curr=curr->next;
        len++;
    }
    k%=len;
    if(k==0)
    {
        return head;
    }
    ListNode *c1=head;
    ListNode *c2=head;
    while(k-->0)
    {
        c2=c2->next;
    }
    while(c2->next!=nullptr)
    {
        c1=c1->next;
        c2=c2->next;
    }
    c2->next=head;
    head=c1->next;
    c1->next=nullptr;
    return head;
}

//segregate odd even,geeks for geeks
ListNode *segregateOddEven(ListNode *head)
{
    if(head==nullptr||head->next==nullptr)
    {
        return head;
    }
    ListNode *even=new ListNode(-1);
    ListNode *ep=even;

    ListNode *odd=new ListNode(-1);
    ListNode *op=odd;

    ListNode *curr=head;
    while(curr!=nullptr)
    {
        if(curr->val%2==0)
        {
            ep->next=curr;
            ep=ep->next;
        }
        else
        {
            op->next=curr;
            op=op->next;
        }
        curr=curr->next;
    }
    ep->next=odd->next;
    return even->next;
}

ListNode *insert(ListNode *head,int insertVal)
{
    ListNode *node=new ListNode(insertVal);
    if(head==nullptr)
    {
        node->next=node;
        return node;
    }
    ListNode *prev=head;
    ListNode *curr=head->next;

    bool connect=false;
    while(curr!=head)
    {
        if(insertVal>=prev->val && insertVal<=curr->val)
        {
            connect=true;
        }
        else if(prev->val > curr->val)
        {
            if(insertVal >prev->val || insertVal < curr->val)
            {
                connect=true;
            }
        }
        if(connect)
        {
            break;
        }
        prev=curr;
        curr=curr->next;
    }
    prev->next=node;
    node->next=curr;

    return head;
}

//leetcode 138
void copyNode(ListNode *head)
{
    ListNode *curr=head;
    while(curr!=nullptr)
    {
        ListNode *next=curr->next;
        ListNode *node=new ListNode(curr->val);

        curr->next=node;
        node->next=next;

        curr=next; 
    }
}
void copyRandomPointer(ListNode *head)
{
    ListNode *curr=head;
    while(curr!=nullptr)
    {
        if(curr->random!=nullptr)
        {
            curr->next->random=curr->random->next;
        }
        curr=curr->next->next;
    }
}
ListNode *extractMyLL(ListNode *head)
{
    ListNode *dummy=new ListNode(-1);
    ListNode *prev=dummy;

    ListNode *curr=head;
    while(curr!=nullptr)
    {
        prev->next=curr->next;
        curr->next=curr->next->next;

        prev=prev->next;
        curr=curr->next;
    }
    return dummy->next;
}
ListNode *copyRandomList(ListNode *head)
{
    if(head==nullptr)
    {
        return head;
    }
    copyNode(head);
    copyRandomPointer(head);
    return extractMyLL(head);
}

