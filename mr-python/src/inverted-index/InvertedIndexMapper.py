import sys
import os

for line in sys.stdin:
    # Get the words from the line
    words = line.strip().split()
    # Get file name and offset
    file_name = os.environ.get('map.input.file', None)
    # Get the starting offset of the split
    offset = os.environ.get('map.input.start', None)

    for word in words:
        # Accumulate the length of the word
        offset += len(word)
        print '%s\t%s' % (word, file_name + ':' + offset)