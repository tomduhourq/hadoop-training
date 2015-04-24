import sys

current_column = None
current_count = 0
current_sum = 0
current_list = list()
column = None


def variance(l, mean, count):
    return sum(list(map((lambda x: (x - mean) ** 2/(count - 1)), l)))


def print_tuple(col, l, sum, count):
    print '%s\t%s' % (col, str(variance(l, sum / count, count)))

for line in sys.stdin:
    column, value = line.rstrip('\t', 1)
    value = float(value)

    # If we have the same column as the other split,
    # we need to keep track of the numbers and count to get the mean.
    if current_column == column:
        current_list += value
        current_sum += value
        current_count += 1
    else:
        # current_column != None, we have finished with one column
        if current_column:
            print_tuple(column, current_list, current_sum, current_count)
        # restart counters for next column
        current_list = [value]
        current_count = 1
        current_sum = value
        current_column = column

    # Last output if needed
    if current_column == column:
        print_tuple(column, current_list, current_sum, current_count)