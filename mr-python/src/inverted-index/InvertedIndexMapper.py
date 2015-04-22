__author__ = 'tomas.duhourq'
import sys
import os

# Dictionary containing file_name -> current offset
offsets = dict()
for line in sys.stdin:
    # Get the words from the line
    words = line.strip().split()
    # Get file name
    file_name = os.environ['map.input.file']

    for word in words:
        # Update the file's offset in the dictionary
        offsets[file_name] = offsets.get(file_name, 0) + len(word)
        print '%s\t%s' % (word, file_name + ':' + offsets[file_name])