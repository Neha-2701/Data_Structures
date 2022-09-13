struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
 };

//Leetcode 876
ListNode* middleNode(ListNode* head) {
    ListNode* slow=head;
    ListNode* fast=head;
    while(fast!=nullptr && fast->next!=nullptr){
        slow=slow->next;
        fast=fast->next->next;
    }
    return slow;
}

ListNode* middleNode_01(ListNode* head) {
    ListNode* slow=head;
    ListNode* fast=head;
    while(fast->next!=nullptr && fast->next->next!=nullptr){
        slow=slow->next;
        fast=fast->next->next;
    }
    return slow;
}

//Leetcode 206
ListNode* reverseList(ListNode* head) {
    ListNode* curr=head;
    ListNode* prev=nullptr;
    ListNode* forw=nullptr;
    while(curr!=nullptr){
        forw=curr->next;
        curr->next=prev;
        prev=curr;
        curr=forw;
    }
    return prev;
}

void reverseData(ListNode* head){
    if(head==nullptr || head->next==nullptr) return;
    ListNode* mid=middleNode_01(head);
    ListNode* thead=mid->next;

    thead=reverseList(thead);
    ListNode* c1=head;
    ListNode* c2=thead;

    while(c1!=nullptr && c2!=nullptr){
        int temp=c1->val;
        c1->val=c2->val;
        c2->val=temp;

        c1=c1->next;
        c2=c2->next;
    }
    thead=reverseList(thead);
    mid->next=thead;
}

//Leetcode 234
bool isPalindrome(ListNode* head) {
    if(head==nullptr || head->next==nullptr) return true;
    ListNode* mid=middleNode_01(head);
    ListNode* thead=mid->next;
    
    thead=reverseList(thead);
    ListNode* c1=head;
    ListNode* c2=thead;
    while(c1!=nullptr && c2!=nullptr){
        if(c1->val!=c2->val) return false;
        c1=c1->next;
        c2=c2->next;
    }
    return true;  
}

//Leetcode 143
void reorderList(ListNode* head) {
    if(head==nullptr || head->next==nullptr) return;
    ListNode* mid=middleNode_01(head);
    ListNode* thead=mid->next;
    mid->next=nullptr;
    thead=reverseList(thead);
    
    ListNode* c1=head;
    ListNode* c2=thead;
    ListNode* f1=nullptr;
    ListNode* f2=nullptr;
        
    while(c1!=nullptr && c2!=nullptr){
        f1=c1->next;
        f2=c2->next;
            
        c1->next=c2;
        c2->next=f1;
        
        c1=f1;
        c2=f2;
    }
}

//leetcode 1669
ListNode* mergeInBetween(ListNode* list1, int a, int b, ListNode* list2) {
    int count=0;
    ListNode* prev=nullptr;
    ListNode* curr=list1;
    while(count!=a){
        prev=curr;                                                                             
        curr=curr->next;
        count++;
    }
    prev->next=list2;
    ListNode* currr=list2;
    while(currr->next!=nullptr){
        currr=currr->next;
    }
    while(count!=b){
        curr=curr->next;
        count++;
    }
    currr->next=curr->next;
    return list1;
}

