import sys

current_word = None
current_count = 0
word = None

for line in sys.stdin:
    word, count = line.strip().split('\t')

    count = int(count)

    # same word as we were processing before
    if current_word == word:
        current_count += count
    else:
        # changed word
        # compare to None
        if current_word:
            print '%s\t %s' % (current_word, current_count)
        current_count = count
        current_word = word

if current_word == word:
    print '%s\t %s' % (current_word, current_count)