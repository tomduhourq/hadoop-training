import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Simple mapreduce job to count words in a file passed by parameter,
 * with output path as a second parameter on hdfs.
 * Created by tomas on 24/02/15.
 */
public class WordCount2 {
    // First, my mapper
    public static class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

        static final List<String> forbiddenPatterns = Arrays.asList(",",".","-");

        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException{

            String line = value.toString().toLowerCase();
            final IntWritable ONE = new IntWritable(1);
            Text word = new Text();

            // Get rid of unwanted patterns in our words
            for (String pattern : forbiddenPatterns){
                line = line.replaceAll(pattern, "");
            }

            // map in itself
            StringTokenizer itr = new StringTokenizer(line);
            while(itr.hasMoreTokens()){
                word.set(itr.nextToken());
                context.write(word,ONE);
            }
        }
    }

    // Second, my reducer
    public static class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException{

            // for each key, get the total repetitions of that specific key.
            int sum = 0;
            for(IntWritable val: values){
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    // Last, the driver (in this case represented by my main method)
    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();
        Job job = new Job(conf, "word count");

        job.setMapperClass(MyMapper.class);
        job.setCombinerClass(MyReducer.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
