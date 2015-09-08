package com.globant.bigdata.variance;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class VarianceReducer extends Reducer<ImmutableDoubleWritable, ImmutableDoubleWritable,
        Text, Text> {

    static final ImmutableDoubleWritable ONE = new ImmutableDoubleWritable(1.0);

    @Override
    public void reduce(ImmutableDoubleWritable key, Iterable<ImmutableDoubleWritable> values, Context context)
            throws java.io.IOException, InterruptedException {
        // We need to count how many elements we got, their sum,
        // take the avg, and calculate the variance.

        Iterator<ImmutableDoubleWritable> i = values.iterator();
        ImmutableDoubleWritable count = new ImmutableDoubleWritable();
        ImmutableDoubleWritable sum = new ImmutableDoubleWritable();
        List<ImmutableDoubleWritable> l = new ArrayList();

        while(i.hasNext()) {
            ImmutableDoubleWritable next = i.next();

            // This must be avoided if the data set is really big.
            l.add(next);
            count = count.plus(ONE);
            sum = sum.plus(next);
        }

        final ImmutableDoubleWritable average = sum.div(count);

        ImmutableDoubleWritable variance = new ImmutableDoubleWritable();

        for(ImmutableDoubleWritable elem : l) {
            variance = variance.plus(getElement(elem, average));
        }

        // The output should be: column variance
        context.write (
                new Text("Column: " + key.get()),
                new Text("Variance: " + variance.div(count).get())
        );
    }

    private ImmutableDoubleWritable getElement (
                ImmutableDoubleWritable i,
                ImmutableDoubleWritable average) {
        return i.sub(average).pow(2);
    }
}
