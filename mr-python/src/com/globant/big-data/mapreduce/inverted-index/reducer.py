import sys

current_word = None
current_offset = None
word = None

for line in sys.stdin:
    word, offset = line.strip().split('\t')
    if current_word == word:
        current_offset += ', ' + offset
    else:
        # If we haven't got None
        if current_word:
            print '%s\t%s' % (current_word, current_offset)
        current_offset = offset
        current_word = word
# Output for the last word if needed
if current_word == word:
    print '%s\t%s' % (current_word, current_offset)