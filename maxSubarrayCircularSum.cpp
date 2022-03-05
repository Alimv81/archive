#include <bits/stdc++.h>

int linear_max(int a[], int n)
{
  int max_so_far = 0, max_ending_here = 0;
  int i;
  for (i = 0; i < n; i++) {
    max_ending_here = max_ending_here + a[i];
    
    if (max_so_far < max_ending_here)
      max_so_far = max_ending_here;
    if (max_ending_here < 0)
      max_ending_here = 0;
  }
  return max_so_far;
}

int MaxCircularSubarray(int a[], int n)
{
  int max_sub = linear_max(a, n);
  if(max_sub < 0)
      return max_sub;
  int max_wrap = 0, i;
  for (i = 0; i < n; i++) {
    max_wrap += a[i]; 
    a[i] = -a[i]; 
  }

  max_wrap = max_wrap + linear_max(a, n);

  return (max_wrap > max_sub) ? max_wrap : max_sub;
}


int main()
{
  int a[] = { 11, -31, 10 };
  int n = sizeof(a) / sizeof(a[0]);
  std::cout << "Maximum circular sum is " << MaxCircularSubarray(a, n) << std::endl;
  return 0;
}