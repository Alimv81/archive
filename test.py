def lunch():
    global All, subsets
    for i in range(1, len(All)+1):
        find_subsets([], i)
    switch = input("do you want to save data in txt file?(yes/no)")
    if switch == "yes":
        with open("output.txt", "w") as file:
            for i in subsets:
                file.write(str(i)+"\n")


def find_subsets(ll, length):
    global All, subsets
    if len(ll) == length:
        ll.sort()
        if ll not in subsets:
            subsets.append(ll)
        return
    for i in All:
        if i not in ll:
            find_subsets(ll + [i], length)


if __name__ == '__main__':
    subsets = []
    All = [x for x in input().split(" ")]
    lunch()
    print(*subsets)
