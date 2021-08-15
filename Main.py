from Testing import OOP

List = list()

for i in range(3):
    List.append(OOP())

for i in List:
    print(i.x, end=" ")

print()

p = List[0].__or__(List[1])

print(p)
