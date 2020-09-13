
package AQI;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

public class AQIRunner {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        
        BasicConfigurator.configure();
        Configuration con = new Configuration();
        Job job = Job.getInstance(con);
        job.setJarByClass(AQIRunner.class);
        
        job.setMapperClass(AQIMapper.class);
        job.setReducerClass(AQIReducer.class);
        
        /*
        job.setMapperClass(InverseMapper.class); //实现map()之后的数据对的key和value交换
        job.setNumReduceTasks(1); // reducer个数
        */
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(AQIBean.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setSortComparatorClass(IntWritableDecreasingComparator.class);
        FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.137.192:9000/user/root/AQIinput"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.137.192:9000/user/root/AQIoutput10"));
        
        job.waitForCompletion(true);
    }    
}
