import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class VarianceDriver extends Configured implements Tool {

    public static void main(String[] args) throws Exception{
        int exitCode = ToolRunner.run(new VarianceDriver(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] args) throws Exception {
        // Correct input verification
        if (args.length != 2) {
            System.err.println("Usage: VarianceJob <input path> <output path>");
            return 1;
        }

        Job job = new Job(getConf(), "Variance");
        job.setJarByClass(getClass());

        job.setMapperClass(VarianceMapper.class);
        job.setReducerClass(VarianceReducer.class);

        // Output will be word  comma-separated-apparitions
        job.setOutputKeyClass(DoubleSummableWritable.class);
        job.setOutputValueClass(DoubleSummableWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }
}
