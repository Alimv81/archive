#include <iostream>
#include <algorithm>
using namespace std;
int calculateMin(int a[], int low, int high)
{
    int i,mini;
    mini = a[low];
    for(i=low;i<=high;i++)
    {
        if(a[i]<mini)
        {
            mini = a[i];
        }
    }
    return mini;
}
int calculateMax(int a[], int low, int high)
{
    int i,maxi;
    maxi = a[low];
    for(i=low;i<=high;i++)
    {
        if(a[i]>maxi)
        {
            maxi = a[i];
        }
    }
    return maxi;
}
int calculateMaxDiff(int a[], int low, int high)
{
    if(low>=high)
        return 0;

    int mid = (low+high)/2;
    int leftPartition = calculateMaxDiff(a,low,mid);
    int rightPartition = calculateMaxDiff(a,mid+1,high);
    int left = calculateMin(a,low,mid); // calculate the min value in the left partition
    int right = calculateMax(a,mid+1,high); // calculate the max value in the right partition
    return max(max(leftPartition, rightPartition), (right - left));

}
int main() {
    int arr[] = {12, 9, 18, 3, 7, 11, 6, 15, 6, 1 ,10};
    int len = sizeof(arr)/sizeof(arr[0]);
    int ans = calculateMaxDiff(arr, 0, len-1);
    cout << "Maximum Profit: " <<ans<<endl;
    return 0;
}