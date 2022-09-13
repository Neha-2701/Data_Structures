using namespace std;

int offToOn(int num,int k)
{
    int mask=1<<k;
    return num|mask;
}

int OnToOff(int num,int k)
{
    int mask=~(1<<k);
    return num&mask;
}

int hamming_Weight(int num)
{
    int count=0;
    for(int i=0;i<32;i++)
    {
        int mask=(1<<i);
        if(num&mask!=0) count++;
    }
    return count;
}

int main()
{
    
}