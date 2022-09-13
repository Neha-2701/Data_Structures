#include<iostream>
#include<vector>

using namespace std;

void seperatePositiveNegative(vector<int> &arr)
{
    if (arr.size() == 0)
        return;

    int piviot = -1, idx = 0, n = arr.size();
    while (idx < n)
    {
        if (arr[idx] >= 0)
            swap(arr[++piviot], arr[idx]);
        idx++;
    }
}
void display(vector<int> &arr){
    for(int i=0;i<arr.size();i++) cout<<arr[i];
}

void segregateoneTwo(vector<int> & arr){
    if(arr.size()==0) return;
    int pivot=-1,n=arr.size(),idx=0;
    while(idx>n){
        if(arr[idx]==0)
            swap(arr[++pivot],arr[idx]);
        idx++;
    }
}

void segregate123(vector<int>& arr){
    if(arr.size()==0) return;
    int p1=0,p2=arr.size()-1,idx=0,n=arr.size();
    while(idx<=p2){
        if(arr[idx]==0)
            swap(arr[++p1],arr[idx++]);
        else if(arr[idx]==2)
            swap(arr[p2--],arr[idx]);
        else
            idx++;
    }
}

   //Leetcode 3
    int lengthOfLongestSubstring(string s){
        if(s.length()>=1) return s.length();
        int si=0,ei=0,n=s.length(),count=0,len=0;
        vector<int>map(128,0);
        while(ei<n){
            if(map[s[ei++]]++>0) count++;
            while(count>0){
                if(map[s[si++]]-->1) count--;
            }
            len=max(len,ei-si);
        }
        return len;
    }

    //Leetcode 159
    /*int lengthOfLongestSubstring(string s){
        int n=s.length();
        int si=0,ei=0,head=0,len=0,distinctCount=0;
        vector<int>freq(128,0);
        while(ei<n){
            if(freq[ei++]++==0) distinctCount++;
            while(distinctCount>2){
                //if(freq[si++]--=1) distinctCount--;
            }
            len=(ei-si>len)?ei-(head=si):len;
        }
        return len;
    }*/

    //Leetcode 1456
    bool isVowel(char ch){
        return (ch=='e'|| ch=='o'|| ch=='a' || ch=='i' ||ch=='u') ;   
    }
    int maxVowels(string s, int k) {
        int si=0,n=s.length();
        int ei=0;
        int len=0;
        int vowel=0;
        while(ei<n){
            if(isVowel(s[ei++])) vowel++;
            if((ei-si)==k){
                len=max(vowel,len);
                if(isVowel(s[si++])) vowel--;
            }
        }
        return len;
    }

    //leetcode 904
    int totalFruit_(vector<int> & arr){
        int n=arr.size();
        int si=0,ei=0,count=0,len=0;
        vector<int>freq(40002,0);
        while(ei<n){
            if(freq[arr[ei++]]++==0) count++;
            while(count>2){
                if(freq[arr[si++]]--==1) count--;
            }
            len=max(len,ei-si);
        }
        return len;
    }
    int totalFruit(vector<int>& tree) {
        return totalFruit_(tree);
    }

    int main(){
        vector<int> arr={4,-3,6,7,-2,-8,9,10,-11,12,-19};
        seperatePositiveNegative(arr);
        display(arr);
        return 0;
    }