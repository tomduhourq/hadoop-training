import sys

elements = []
current_col = None

def variance(col):
    s = sum(elements)
    l = len(elements)
    mean = s / l
    var = [(x - mean)**2 for x in elements]
    print '%s\t%s' % (col, sum(var) / (l - 1))

for line in sys.stdin:
    key, value = line.strip().split('\t', 1)

    column = int(key)
    value = float(value)

    # same column as we were processing before
    if current_col == column:
        elements.append(value)
    else:
        # changed column
        # first, compare to None
        if current_col:
            print '%s\t%s' % (current_col, variance(current_col))
        elements = [value]
        current_col = column

if current_col == column:
    print '%s\t%s' % (current_col, variance(current_col))