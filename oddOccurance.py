# Recursive function to find an odd occurring element in a list
# using binary search. This function assumes the input is valid.
def findOddOccuring(nums, low, high):
 
    # base case
    if low == high:
        return low
 
    # find the middle index
    mid = (low + high) // 2
 
    # if `mid` is odd
    if mid % 2 == 1:
 
        # if the element before `mid` is the same as the middle element, the odd
        # element lies on the right side; otherwise, it lies on the left side
        if nums[mid] == nums[mid - 1]:
            return findOddOccuring(nums, mid + 1, high)
        else:
            return findOddOccuring(nums, low, mid - 1)
 
    # `mid` is even
    else:
 
        # if the element next to `mid` is the same as the middle element, the odd
        # element lies on the right side; otherwise, it lies on the left side
        if nums[mid] == nums[mid + 1]:
            return findOddOccuring(nums, mid + 2, high)
        else:
            return findOddOccuring(nums, low, mid)
 
 
if __name__ == '__main__':
 
    nums = [2, 2, 1, 1, 3, 3, 2, 2, 4, 4, 3, 1, 1]
 
    index = findOddOccuring(nums, 0, len(nums) - 1)
    print('The odd occurring element is', nums[index])
 
