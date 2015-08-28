import sys

current_key = None
current_sum = 0
current_count = 0

for line in sys.stdin:
    key, data = line.strip().split('\t')
    value, amount = data.split('_')

    value = float(value)
    amount = float(amount)

    # same word as we were processing before
    if current_key == key:
        current_sum += value
        current_count += amount
    else:
        # changed key
        # first, compare to None
        if current_key:
            print '%s\t%s' % (current_key, current_sum/current_count)
        current_count = amount
        current_key = key
        current_sum = value

if current_key == key:
    print '%s\t%s' % (current_key, current_sum/current_count)
