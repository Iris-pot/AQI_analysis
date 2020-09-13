/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AQIIndex;

import com.sun.corba.se.spi.orb.StringPair;
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.ArrayWritable;



public class AQIIndexMapper extends Mapper<LongWritable, Text, Text, Text>{
  
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.toString().equals("0")) {
            return;
	}
        String line = value.toString();
        String[] fields = line.split(",");
        //double AQI = Double.parseDouble(fields[10]);
        String year = fields[12];
        String month = fields[13];
        String day = fields[14];
        String date = year + month + day;
        String city = fields[16];
        String PM25 = fields[3];
        String PM10 = fields[4];
        String NO2  = fields[5];
        String SO2 = fields[6];
        String O3 = fields[8];
        String CO = fields[9];
        
        String date_air = date+"\t"+PM25+"\t"+PM10+"\t"+NO2+"\t"+SO2+"\t"+O3+"\t"+CO;
    
        // 限制时间条件
        context.write(new Text(city), new Text(date_air));
        }
    }        

