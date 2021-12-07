class Tree(object):
    def __init__(self, data):
        self.key = data
        self.left = None
        self.right = None


def inorder(temp):
    if not temp:
        return

    inorder(temp.left)
    print(temp.key, end=" ")
    inorder(temp.right)


def insert(temp, key):
    q = [temp]

    while len(q):
        temp = q.__getitem__(0)
        q.pop(0)

        if not temp.left:
            temp.left = Tree(key)
            break
        else:
            q.append(temp.left)

        if not temp.right:
            temp.right = Tree(key)
            break
        else:
            q.append(temp.right)


if __name__ == "__main__":
    root = Tree(1)
    insert(root, 4)
    insert(root, 9)
    insert(root, 6)
    insert(root, 5)
    inorder(root)
