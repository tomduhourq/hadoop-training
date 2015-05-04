import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class VarianceMapper extends Mapper<LongWritable, Text,
        DoubleSummableWritable, DoubleSummableWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws java.io.IOException, InterruptedException {
        // Get the values from each of the columns
        String[] columnValues = value.toString().split(",");

        // We cannot afford to do Arrays.asList(value.toString().split(",")).index(n)
        // to obtain the column number because numbers can be repeated in our lines.
        double col = -1.0;
        // Create tuples with (columnNumber, value)
        for(String val: columnValues) {
            col += 1.0;
            context.write(
                    new DoubleSummableWritable(col),
                    new DoubleSummableWritable(Double.parseDouble(val))
            );
        }
    }
}
