package com.globant.bigdata.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	private LongWritable result = new LongWritable();

	@Override
	public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		for (LongWritable value : values) {
			sum += value.get();
		}
		result.set(sum);
		context.write(key, result);
	}
}
