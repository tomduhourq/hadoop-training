import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/** Driver class responsible for running
 * the mapreduce job.
 *
 * To run it on the console,
 * $ mvn clean install
 * $ export HADOOP_CLASSPATH=climate-max-1.0-SNAPSHOT.jar
 * $ hadoop MaxTemperature path/to/1902.txt path/to/output
 */
public class MaxTemperature {

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.err.println("Usage: MaxTemperature <input path> <output path>");
            System.exit(-1);
        }

        // Representation of the job
        Job job = new Job();
        job.setJarByClass(MaxTemperature.class);
        job.setJobName("Max temperature");

        // Concerning the path from where Hadoop is going to ingest data
        // and put it after it has been processed.
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Set the classes from where to take the MapReduce behavior
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        // Classes of outputs. The map outputs match these ones or can be
        // modified with job.setMapOutputKeyClass() and job.setMapOutputValueClass()
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Submit the job, waitForCompletion(true) writes progress information
        // in the console.
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

