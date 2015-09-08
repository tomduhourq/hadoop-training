import com.globant.bigdata.variance.ImmutableDoubleWritable;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImmutableDoubleWritableTest {

    private ImmutableDoubleWritable i1;
    private ImmutableDoubleWritable i2;
    private ImmutableDoubleWritable problematic;

    @Before
    public void setUp(){
        i1 = new ImmutableDoubleWritable(2.0);
        i2 = new ImmutableDoubleWritable(5.0);
        problematic = new ImmutableDoubleWritable();
    }

    @Test
    public void toStringTest() {
        Assert.assertEquals("Value: 2.0", i1.toString());
        Assert.assertEquals("Value: 5.0", i2.toString());
    }

    @Test
    public void plusTest() {
        ImmutableDoubleWritable res = i1.plus(i2);
        Assert.assertEquals(12.0, res.plus(i2).get());
    }

    @Test
    public void subTest() {
        Assert.assertEquals(-3.0, i1.sub(i2).get());
    }

    @Test
    public void divTest() {
        Assert.assertEquals(Double.POSITIVE_INFINITY, i1.div(problematic).get());
    }

    @Test
    public void powTest() {
        Assert.assertEquals(32.0, i1.pow(5).get());
    }

    @Test
    public void complicatedTest() { Assert.assertEquals(9.0, i2.sub(i1).pow(2).get());}
}
