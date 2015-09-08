package com.globant.bigdata.variance;

public class ImmutableDoubleWritable extends org.apache.hadoop.io.DoubleWritable {

    public ImmutableDoubleWritable(double value) {
        super(value);
    }

    public ImmutableDoubleWritable() { super(0.0); }

    public ImmutableDoubleWritable plus(ImmutableDoubleWritable that) {
        return new ImmutableDoubleWritable(this.get() + that.get());
    }

    public ImmutableDoubleWritable sub(ImmutableDoubleWritable that) {
        return new ImmutableDoubleWritable(this.get() - that.get());
    }

    public ImmutableDoubleWritable div(ImmutableDoubleWritable that){
        return new ImmutableDoubleWritable(this.get() / that.get());
    }

    public ImmutableDoubleWritable pow(int n) {
        return new ImmutableDoubleWritable(Math.pow(this.get(), n));
    }

    public boolean equals(ImmutableDoubleWritable that){
        return this.get() == that.get();
    }

    @Override
    public String toString(){
        return "Value: " + this.get();
    }
}
