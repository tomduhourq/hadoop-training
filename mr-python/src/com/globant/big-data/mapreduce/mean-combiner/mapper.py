import sys

for line in sys.stdin:
    height, weight, age = line.strip().split()
    print '%s\t%s_%s' % (1, height, 1)
    print '%s\t%s_%s' % (2, weight, 1)
    print '%s\t%s_%s' % (3, age, 1)
