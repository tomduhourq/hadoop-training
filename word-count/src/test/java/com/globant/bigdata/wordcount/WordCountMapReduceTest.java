package com.globant.bigdata.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class WordCountMapReduceTest {
	MapDriver<LongWritable, Text, Text, LongWritable> mapDriver;
	ReduceDriver<Text, LongWritable, Text, LongWritable> reduceDriver;

	private static final LongWritable ONE = new LongWritable(1);

	@Before
	public void setUp() {
		WordCountMapper mapper = new WordCountMapper();
		WordCountReducer reducer = new WordCountReducer();
		mapDriver = MapDriver.newMapDriver(mapper);
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
	}

	@Test
	public void testMapper() throws IOException {
		LongWritable inKey = new LongWritable(0);
		Text inValue = new Text("bar foo bar");
		mapDriver.withInput(inKey, inValue);

		Text outKey1 = new Text("bar");
		LongWritable outValue1 = ONE;
		Text outKey2 = new Text("foo");
		LongWritable outValue2 = ONE;
		Text outKey3 = new Text("bar");
		LongWritable outValue3 = ONE;
		mapDriver.withOutput(outKey1, outValue1);
		mapDriver.withOutput(outKey2, outValue2);
		mapDriver.withOutput(outKey3, outValue3);
		mapDriver.runTest(true);
	}

	@Test
	public void testReducer() throws IOException {
		Text inKey = new Text("bar");
		List<LongWritable> inListValues = new ArrayList<LongWritable>();
		inListValues.add(ONE);
		inListValues.add(ONE);
		reduceDriver.withInput(inKey, inListValues);

		Text outKey = new Text(inKey.toString());
		LongWritable outValue = new LongWritable(2);
		reduceDriver.withOutput(outKey, outValue);
		reduceDriver.runTest();
	}
}