import org.apache.hadoop.mapreduce.Reducer;

import java.util.Iterator;


public class VarianceReducer extends Reducer<DoubleSummableWritable, DoubleSummableWritable,
        DoubleSummableWritable, DoubleSummableWritable> {

    static final DoubleSummableWritable ONE = new DoubleSummableWritable(1.0);

    @Override
    public void reduce(DoubleSummableWritable key, Iterable<DoubleSummableWritable> values, Context context)
            throws java.io.IOException, InterruptedException {
        // We need to count how many elements we got, their sum,
        // take the avg, and calculate the variance.

        Iterator<DoubleSummableWritable> i = values.iterator();
        DoubleSummableWritable count = new DoubleSummableWritable(0.0);
        DoubleSummableWritable sum = new DoubleSummableWritable(0.0);
        while(i.hasNext()){
            count = count.plus(ONE);
            sum = sum.plus(i.next());
        }

        final DoubleSummableWritable average = sum.div(count);

        i = values.iterator();
        DoubleSummableWritable variance = new DoubleSummableWritable(0.0);
        while(i.hasNext()){
            variance = variance.plus(getElement(i, count, average));
        }
        context.write(key, variance);
    }

    private DoubleSummableWritable getElement(
                Iterator<DoubleSummableWritable> i,
                DoubleSummableWritable count,
                DoubleSummableWritable average) {
        return i.next().sub(average).pow(2).div(count.sub(ONE));
    }
}
