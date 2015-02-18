import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by tomas on 11/02/15.
 */
public class HDFSDir {

    public static void main(String[] args) throws IOException {
        uploadInputFiles();
    }

    public static void uploadInputFiles() throws IOException {
        Configuration conf = new Configuration();

        FileSystem local = FileSystem.getLocal(conf);
        FileSystem hdfs = FileSystem.get(conf);

        Path hdfsDir = new Path("/books");
        if (!hdfs.exists(hdfsDir))
            hdfs.mkdirs(hdfsDir);

        hdfs.copyFromLocalFile(new Path("/home/tomas/Dev/Workspace Java/upload-file-hdfs/data"),
                new Path("/books"));

        local.close();
        hdfs.close();
    }
}
