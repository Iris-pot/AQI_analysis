
package AQIClassify;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AQIClassfyMapper extends Mapper<LongWritable, Text, Text, Text> {
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.toString().equals("0")) {
            return;
	}
        String line = value.toString();
        String[] fields = line.split(",");
        double AQI = Double.parseDouble(fields[10]);
        String year = fields[12];
        String month = fields[13];
        String day = fields[14];
        String date = year + month + day;
        String city = fields[16];
        String date_city = date+" "+city;
        
        // 限制时间条件
        if ("2019".equals(year) && "2".equals(month) && ("北京".equals(city) || "上海".equals(city) || "成都".equals(city))){
            context.write(new Text(city), new Text(date+" "+String.valueOf(AQI)));
        }
    }        
}
