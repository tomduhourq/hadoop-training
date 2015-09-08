package com.globant.bigdata.variance.mean;

import com.globant.bigdata.variance.ImmutableDoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.util.Iterator;

public class MeanReducer extends Reducer<ImmutableDoubleWritable, ImmutableDoubleWritable,
        Text, Text> {

    static final ImmutableDoubleWritable ONE = new ImmutableDoubleWritable(1.0);

    @Override
    public void reduce(ImmutableDoubleWritable key, Iterable<ImmutableDoubleWritable> values,
                       Context context)
            throws java.io.IOException, InterruptedException {

        Iterator<ImmutableDoubleWritable> it = values.iterator();
        ImmutableDoubleWritable sum = new ImmutableDoubleWritable();
        ImmutableDoubleWritable count = new ImmutableDoubleWritable();

        while(it.hasNext()) {
            sum = sum.plus(it.next());
            count = sum.plus(ONE);
        }

        context.write(new Text(""+key.get()), new Text(""+sum.div(count)));
    }
}