import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class VarianceMapper extends Mapper<LongWritable, Text, LongSummableWritable, LongSummableWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws java.io.IOException, InterruptedException {
        // Get the values from each of the columns
        String[] columnValues = value.toString().split(",");

        // We cannot afford to do Arrays.asList(value.toString().split(",")).index(n)
        // to obtain the column number because numbers can be repeated in our lines.
        int col = -1;
        // Create tuples with (columnNumber, value)
        for(String val: columnValues) {
            col += 1;
            context.write(
                    new LongSummableWritable(col),
                    // It would be nice to have a LongWritable constructor with string
                    new LongSummableWritable(Long.parseLong(val))
            );
        }
    }
}
