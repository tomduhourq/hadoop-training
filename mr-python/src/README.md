## Hadoop Streaming

To run each of the examples

````bash
$ hadoop \
jar $HADOOP_INSTALL/share/hadoop/tools/lib/hadoop-streaming-<version>.jar \
-D mapred.map.tasks=5 \     ← optional 
-D mapred.reduce.tasks=2 \  ← optional 
-files /hadoop-training/mr-python/src/com/globant/big-data/mapreduce/<choose>/mapper.py,/hadoop-training/mr-python/src/com/globant/big-data/mapreduce/<choose>/reducer.py \
-mapper "python ./mapper.py" \
-combiner "python ./combiner.py" ← optional
-reducer "python ./reducer.py" \
-input <path-for-data-in-hdfs> \
-output <path-in-hdfs> 
````
