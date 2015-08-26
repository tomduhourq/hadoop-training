import sys
import string


def cleanse(w):
    return w.translate(None, string.punctuation)

# As we are streaming the data from the file, it comes from sys.stdin.
for line in sys.stdin:
    words = line.strip().split()
    for word in words:
        clean_word = cleanse(word)
        if clean_word == '':
            continue
        print '%s\t%s' % (clean_word, 1)



