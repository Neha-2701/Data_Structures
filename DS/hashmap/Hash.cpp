#include<iostream>
#include<unordered_map>

using namespace std;
void set1()
{
    unordered_map<string,int> map;
    map["IND"]=10;




    for(pair<string,int>key : map) cout<<key.first<<" "<<key.second;
}