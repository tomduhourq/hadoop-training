import org.apache.hadoop.mapreduce.Reducer;

import java.util.Iterator;


public class VarianceReducer extends Reducer<LongSummableWritable, LongSummableWritable, LongSummableWritable, LongSummableWritable> {

    static final LongSummableWritable ONE = new LongSummableWritable(1L);

    @Override
    public void reduce(LongSummableWritable key, Iterable<LongSummableWritable> values, Context context)
            throws java.io.IOException, InterruptedException {
        // We need to count how many elements we got, their sum,
        // take the avg, and calculate the variance.

        Iterator<LongSummableWritable> i = values.iterator();
        LongSummableWritable count = new LongSummableWritable(0);
        LongSummableWritable sum = new LongSummableWritable(0);
        while(i.hasNext()){
            count = count.plus(ONE);
            sum = sum.plus(i.next());
        }

        final LongSummableWritable average = sum.div(count);

        i = values.iterator();
        LongSummableWritable variance = new LongSummableWritable(0);
        while(i.hasNext()){
            variance = variance.plus(getElement(i, count, average));
        }
        context.write(key, variance);
    }

    private LongSummableWritable getElement(
                Iterator<LongSummableWritable> i,
                LongSummableWritable count,
                LongSummableWritable average) {
        return i.next().sub(average).pow(2).div(count.sub(ONE));
    }
}
