
package AQI;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AQIMapper extends Mapper<LongWritable, Text, Text, AQIBean>{
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.toString().equals("0")) {
            return;
	}
        String line = value.toString();
        String[] fields = line.split(",");
        //System.out.println(fields[3]);
        double PM25 = Double.parseDouble(fields[3]);
        String city = fields[16];
        context.write(new Text(city), new AQIBean(PM25, city));
    }    
}
  
