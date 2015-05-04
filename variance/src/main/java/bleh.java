import org.apache.hadoop.io.DoubleWritable;
/**
 * Created by tomas.duhourq on 5/4/15.
 */
public class bleh {
    public static void main(String[] args) {
        final String s = "-1.3245123";
        final double n = Double.parseDouble(s);
        final DoubleSummableWritable d = new DoubleSummableWritable(n);
        System.out.println(d.get());
    }
}
