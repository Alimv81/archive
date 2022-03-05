#include <bits/stdc++.h>

int sum(int x){
    long int sum = 0;
    for (int i = 1; i <= x; i++) sum += i;
    return sum;
}

int search(int x, int left, int right){
    int mid = (right + left) / 2;
    int s = sum(mid);

    while (left <= right){

        if (s == x) {
            return mid;
        }else if (s > x){
            right = mid - 1;
        } else {
            left = mid + 1;
        }

        mid = (right + left) / 2;
        s = sum(mid);
    }
    return mid;
}

int main(){
    system("reset");
    for (int i = 1; i < 50; i++) {
        std::cout << i << " : ";
        std::cout << search(i, 1, i) << '\n';
    }
    return 0;
}