def mergeSort(array):
    if len(array) <= 1:
        return

    mid = len(array) // 2

    left = array[:mid]

    right = array[mid:]

    mergeSort(left)

    mergeSort(right)

    i = j = k = 0

    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            array[k] = left[i]
            i += 1
        else:
            array[k] = right[j]
            j += 1
        k += 1

    while i < len(left):
        array[k] = left[i]
        i += 1
        k += 1

    while j < len(right):
        array[k] = right[j]
        j += 1
        k += 1


def is_bigger(a, b):
    if a[0] > b[0]:
        if a[1] > b[1]:
            return True
    return False


def findScore(index, element, array):
    count = 0
    while index >= 0:
        if is_bigger(element, array[index]):
            count += 1
        index -= 1

    return count


if __name__ == '__main__':
    arr = [[12, 4], [11, 14], [13, 5], [5, 12], [6, 8], [7, 12]]

    mergeSort(arr)
    print(*arr)

    for item in range(len(arr)):
        x = findScore(item, arr[item], arr)
        print(arr[item], x, sep=':')
