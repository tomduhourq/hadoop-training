import sys

for line in sys.stdin:
    nums = line.rstrip().split(',')
    for n in nums:
        # map numbers to (column, num)
        print '%s\t%s' % (str(nums.index(n)), n)