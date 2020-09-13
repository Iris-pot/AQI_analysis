
package AQI;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AQIReducer extends Reducer<Text, AQIBean, Text, Text>{
    
    @Override
    protected void reduce(Text key, Iterable<AQIBean> values, Context context) throws IOException, InterruptedException {
        
        double IAQI_sum = 0;
        double count = 0;
        for (AQIBean value : values) {
            IAQI_sum += value.getIAQI();
            count += 1;
        }
        double IAQI_average = IAQI_sum / count;

        context.write(new Text(key), new Text(String.valueOf(IAQI_average)));
    }
}
