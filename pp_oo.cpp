#include<bits/stdc++.h>

class MyClass {
    int x;
public:
    MyClass(int a):x(a){}
    friend std::ostream& operator<<(std::ostream& os, const MyClass& ob){
        os << ob.x;
        return os;
    }
} object(12);

template<typename... Args> void foo(Args... args) {
    auto f = [=](auto& i)mutable {
        std::cout << typeid(i).name() << ": " << i << std::endl;
    };
    (f(args), ...);
}


int main(){
    foo(12, "89", static_cast<char>(91), 78, 23, "gh", "n", 67.90, object);
    return 0;
}