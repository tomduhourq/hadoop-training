package com.globant.bigdata.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	private static final LongWritable ONE = new LongWritable( 1 );

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString().toLowerCase();
		String[] words = line.split( " " );
		for (String word : words ) {
			context.write( new Text(word), ONE );
		}
	}
}