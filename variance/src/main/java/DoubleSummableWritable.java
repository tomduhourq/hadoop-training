import org.apache.hadoop.io.DoubleWritable;

/**
 * Created by tomas.duhourq on 4/30/15.
 */
public class DoubleSummableWritable extends DoubleWritable {

    public DoubleSummableWritable(double value) {
        super(value);
    }
    public DoubleSummableWritable() { super(0.0); }

    public DoubleSummableWritable plus(DoubleSummableWritable lsw) {
        return new DoubleSummableWritable(this.get() + lsw.get());
    }

    public DoubleSummableWritable sub(DoubleSummableWritable lsw) {
        return new DoubleSummableWritable(this.get() - lsw.get());
    }

    public DoubleSummableWritable div(DoubleSummableWritable lsw){
        return new DoubleSummableWritable(this.get() / lsw.get());
    }

    public DoubleSummableWritable pow(int n) {
        double res = this.get();
        while(n != 0) {
            res *= res;
            n--;
        }
        return new DoubleSummableWritable(res);
    }
}
