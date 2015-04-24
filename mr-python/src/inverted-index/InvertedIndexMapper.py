import sys
import os
import re

regex = re.compile('.*/')
regex_words = re.compile('\W+')
current_line = 0

for line in sys.stdin:

    current_line += 1
    line = line.strip()
    words = regex_words.split(line)
    for word in words:
        file_name = regex.sub('', os.environ["mapreduce_map_input_file"])
        print '%s\t%s:%s' % (word, file_name, current_line)





