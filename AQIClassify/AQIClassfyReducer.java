
package AQIClassify;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import java.util.Map;

public class AQIClassfyReducer extends Reducer<Text, Text, Text, Text>{
    //@Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String city = key.toString();
        //创建一个日期-aqi数组的map
        HashMap<String,ArrayList<Double>> date_aqi = new HashMap<>();
        for(Text val : values){
            double aqi = Double.parseDouble(val.toString().split(" ")[1]);
            String dateString = val.toString().split(" ")[0];
            if (date_aqi.containsKey(dateString)) {
                    ArrayList<Double> aqi_list = date_aqi.get(dateString);
                    aqi_list.add(aqi);
                }
            else{                
                    ArrayList<Double> aqi_list = new ArrayList<>();
                    aqi_list.add(aqi);
                    date_aqi.put(dateString, aqi_list);                
                }
        }
        // 计算每日平均AQI
        
 
        for(Map.Entry<String, ArrayList<Double>> entry : date_aqi.entrySet()){
            double aqi_sum = 0;
            ArrayList<Double> aqiList = entry.getValue();   
             for (int i = 0; i < aqiList.size(); i++) {
                    aqi_sum = aqi_sum + aqiList.get(i);
                }
            double aqi_avg = aqi_sum/aqiList.size();
            aqiList.add(aqi_avg);
        }
        
        //System.out.println(aqi_avg);
        
        int[][] city_count = new int[3][6];
        HashMap<String,Integer> city_code = new HashMap();
        String[] citys = {"北京","上海","成都"};
        for(int t = 0; t < citys.length; t++){
            city_code.put(citys[t], t);
        }
        for(Map.Entry<String, ArrayList<Double>> entry : date_aqi.entrySet()){
            ArrayList<Double> aqiList2 = entry.getValue();  
            Double aqi_avg = aqiList2.get(aqiList2.size()-1) ;
        if (aqi_avg<=50) {
            city_count[city_code.get(city)][0] += 1;
        }
        else if (aqi_avg<=100) {
            city_count[city_code.get(city)][1] += 1;
        }
        else if (aqi_avg<=150) {
            city_count[city_code.get(city)][2] += 1;
        }
        else if (aqi_avg<=200) {
            city_count[city_code.get(city)][3] += 1;
        }
        else if (aqi_avg<=300) {
            city_count[city_code.get(city)][4] += 1;
        }
        else{
            city_count[city_code.get(city)][5] += 1;
        }
        }
        
        String[] results = new String[3];
        for (int i = 0;i<citys.length;i++){
            results[i] = Arrays.toString(city_count[i]);
        }
        //String result = results.toString("\n");   
        context.write(new Text(city), new Text(results[city_code.get(city)]));
    }    
}
