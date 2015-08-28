import sys

for line in sys.stdin:
    column = -1
    nums = line.strip().split(',')
    for n in nums:
        column += 1
        # map numbers to (column, num)
        print '%s\t%s_%s' % (column, n, 1)