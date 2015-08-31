import sys

elements = []

def calcVariance(current_column):
    sum_elemnts = sum(elements)
    count_elements = len(elements)
    media = sum_elemnts / count_elements
    diff_media_elements = [(x - media) ** 2 for x in elements]
    variance = sum(diff_media_elements) / (count_elements - 1)
    print '%s\t%s' % (current_column, variance)

for line in sys.stdin:

    line = line.strip()

    column, number = line.split('\t')

    if current_column == column:
        elements.append(float(number))
    else:
	if current_column:
            calcVariance(current_column)
    current_column = column
    elements = [float(number)]

if current_column == column:
    calcVariance(current_column)
