/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AQIIndex;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;


public class AQIIndexRunner {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        
        BasicConfigurator.configure();
        Configuration con = new Configuration();
        Job job = Job.getInstance(con);
        job.setJarByClass(AQIIndexRunner.class);
        
        job.setMapperClass(AQIIndexMapper.class);
        job.setReducerClass(AQIIndexReducer.class);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.137.192:9000/user/root/AQIinput"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.137.192:9000/user/root/AQIIndexoutput"));
        
        job.waitForCompletion(true);
    }        
}
