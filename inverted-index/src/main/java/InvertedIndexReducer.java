import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws java.io.IOException, InterruptedException {

        String apparitions = "";

        // Could' ve been done like values.foldLeft("")(_ + ", " + _)
        for(Text apparition : values){
            apparitions += ", " + apparition;
        }
        context.write(key, new Text(apparitions));
    }
}
