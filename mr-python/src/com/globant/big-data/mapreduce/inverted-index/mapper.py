import sys
import os
import string
import re

regex = re.compile('.*/')
current_line = 0
current_file = None


def cleanse(w):
    return w.translate(None, string.punctuation)

for line in sys.stdin:
    # Obtain file name
    file_name = regex.sub('', os.environ["mapreduce_map_input_file"])

    # Check if we haven't changed file
    if file_name != current_file:
        current_line = 0
        current_file = file_name

    current_line += 1
    words = line.strip().split()

    for word in words:
        clean_word = cleanse(word)
        if clean_word == '':
            continue
        print '%s\t%s:%s' % (word, file_name, current_line)





