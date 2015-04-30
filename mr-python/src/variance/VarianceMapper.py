import sys

for line in sys.stdin:
    column = -1
    nums = line.rstrip().split(',')
    for n in nums:
        column += 1
        # map numbers to (column, num)
        print '%s\t%s' % (column, n)