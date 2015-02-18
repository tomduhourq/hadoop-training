import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFSSample {

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

        FSDataInputStream in = local.open(new Path("/home/tomas/Dev/Workspace Java/upload-file-hdfs/data/dracula.txt"));
        FSDataOutputStream out = hdfs.create(new Path("/books/dracula.txt"));

        int bytesRead = 0;
        byte[] buffer = new byte[1000];
        while ((bytesRead = in.read(buffer)) >= 0)
            out.write(buffer, 0, bytesRead);

        in.close();
        out.close();

        local.close();
        hdfs.close();
    }
}