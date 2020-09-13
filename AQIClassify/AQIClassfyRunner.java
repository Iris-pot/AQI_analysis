
package AQIClassify;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

public class AQIClassfyRunner {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        
        BasicConfigurator.configure();
        Configuration con = new Configuration();
        Job job = Job.getInstance(con);
        job.setJarByClass(AQIClassfyRunner.class);
        
        job.setMapperClass(AQIClassfyMapper.class);
        job.setReducerClass(AQIClassfyReducer.class);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.137.192:9000/user/root/AQIinput"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.137.192:9000/user/root/AQICountoutput"));
        
        job.waitForCompletion(true);
    }        
}
