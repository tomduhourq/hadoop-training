/**
 * Created by tomas.duhourq on 4/30/15.
 */
public class DoubleWritable extends org.apache.hadoop.io.DoubleWritable {

    public DoubleWritable(double value) {
        super(value);
    }
    public DoubleWritable() { super(0.0); }

    public DoubleWritable plus(DoubleWritable lsw) {
        return new DoubleWritable(this.get() + lsw.get());
    }

    public DoubleWritable sub(DoubleWritable lsw) {
        return new DoubleWritable(this.get() - lsw.get());
    }

    public DoubleWritable div(DoubleWritable lsw){
        return new DoubleWritable(this.get() / lsw.get());
    }

    public DoubleWritable pow(int n) {
        double res = this.get();
        while(n != 0) {
            res *= res;
            n--;
        }
        return new DoubleWritable(res);
    }
}
