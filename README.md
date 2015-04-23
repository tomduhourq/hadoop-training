# hadoop-training

This is a repo intended to learn the principles of Hadoop with training provided by Globant.

Configuration of local hdfs and cluster runs by your own, however you can check my guide on installing Hadoop on a single node cluster:
- [My guide](https://github.com/tomduhourq/dotfiles/blob/master/install/Hadoop.md)
- [Hadoop 2.6.0 single node installation for Ubuntu](https://www.youtube.com/watch?v=3UvR9nDsU7s)

To run the examples, you should check the paths provided in the projects and change them to your needs.

To run any of the examples:

```bash
  $ mvn clean install
  $ $HADOOP_COMMON_HOME/bin/hadoop jar path/to/jar mainClass arg1 arg2 ...
```
This won't contain at any point none of these files:

- Hdfs-site.xml
- Yarn-site.xml
- Mapred-site.xml

This whole project is intended to work with

- Hadoop 2.6.0 - The engine to test MapReduce jobs in hdfs

- Java - JDK 1.7 and 1.8

