import org.apache.hadoop.io.LongWritable;

/**
 * Created by tomas.duhourq on 4/30/15.
 */
final public class LongSummableWritable extends LongWritable{

    public LongSummableWritable plus(LongSummableWritable lsw) {
        return new LongSummableWritable(this.get() + lsw.get());
    }

    public LongSummableWritable sub(LongSummableWritable lsw) {
        return new LongSummableWritable(this.get() - lsw.get());
    }

    public LongSummableWritable div(LongSummableWritable lsw){
        return new LongSummableWritable(this.get() / lsw.get());
    }

    public LongSummableWritable pow(int n) {
        long res = this.get();
        while(n != 0) {
            res *= res;
            n--;
        }
        return new LongSummableWritable(res);
    }

    public LongSummableWritable(long value) {
        super(value);
    }
}
