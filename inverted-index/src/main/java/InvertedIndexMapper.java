import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


/*
 * Sample Data words and words
 * it is what it is
 * what is it
 * it is a banana
 */

public class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws java.io.IOException, InterruptedException {

        //Get the input file name
        FileSplit fs = ((FileSplit) context.getInputSplit());
        String filePathString = fs.getPath().getName();

        //Split the words separated by blanks
        String[] words = value.toString().split(" ");

        //Why the fuck can't we have a foreach?
        for(String word: words) {
            // Until I find a better way output is word fileName:start
            context.write(new Text(word), new Text(filePathString+":"+
                    (fs.getLength() - fs.getStart())
            ));
        }
    }
}
