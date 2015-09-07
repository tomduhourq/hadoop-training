package com.globant.bigdata.invertedindex;

import org.apache.hadoop.mapreduce.Reducer;

import java.util.Iterator;


public class VarianceReducer extends Reducer<ImmutableDoubleWritable, ImmutableDoubleWritable,
        ImmutableDoubleWritable, ImmutableDoubleWritable> {

    static final ImmutableDoubleWritable ONE = new ImmutableDoubleWritable(1.0);

    @Override
    public void reduce(ImmutableDoubleWritable key, Iterable<ImmutableDoubleWritable> values, Context context)
            throws java.io.IOException, InterruptedException {
        // We need to count how many elements we got, their sum,
        // take the avg, and calculate the variance.

        Iterator<ImmutableDoubleWritable> i = values.iterator();
        ImmutableDoubleWritable count = new ImmutableDoubleWritable(0.0);
        ImmutableDoubleWritable sum = new ImmutableDoubleWritable(0.0);
        while(i.hasNext()){
            count = count.plus(ONE);
            sum = sum.plus(i.next());
        }

        final ImmutableDoubleWritable average = sum.div(count);

        i = values.iterator();
        ImmutableDoubleWritable variance = new ImmutableDoubleWritable(0.0);
        while(i.hasNext()){
            variance = variance.plus(getElement(i, count, average));
        }
        context.write(key, variance);
    }

    private ImmutableDoubleWritable getElement(
                Iterator<ImmutableDoubleWritable> i,
                ImmutableDoubleWritable count,
                ImmutableDoubleWritable average) {
        return i.next().sub(average).pow(2).div(count.sub(ONE));
    }
}
