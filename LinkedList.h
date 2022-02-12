#ifndef UNTITLED_LINKEDLIST_H
#define UNTITLED_LINKEDLIST_H

class LinkedList {
public:
    int value = 0;
    LinkedList* next = nullptr;

    explicit LinkedList(int);
    ~LinkedList() = default;
};

LinkedList::LinkedList(int v) {
    this->value = v;
}

void insert(LinkedList* head,int ivalue) {
    while (head->next != nullptr){
        head = head->next;
    }
    head->next = new LinkedList(ivalue);
}

void print(LinkedList* head) {
    while (head != nullptr){
        std::cout << head->value << " ";
        head = head->next;
    }
    std::cout << std::endl;
}

void connect(LinkedList *a, LinkedList *b) {
    a->next = b;
}

void deleting(LinkedList *head, LinkedList *a) {
    if (head == a){
        *head = *head->next;
        return;
    }
    while (head->next != a){
        head = head->next;
    }
    *head->next = *head->next->next;
}


#endif //UNTITLED_LINKEDLIST_H
