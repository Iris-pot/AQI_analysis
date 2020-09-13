/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AQIIndex;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import java.util.Map;

public class AQIIndexReducer extends Reducer<Text, Text, Text, Text>{
    
    private static final double[] LIMITS = {75.0,150.0,80.0,150.0,160.0,4.0};
    protected void reduce(Text key, Iterable<Text> values, Context context) 
                                throws IOException, InterruptedException {
        String city = key.toString();
        //创建一个日期-ap数组的map
        HashMap<String,ArrayList<Double[]>> date_ap = new HashMap<>();
        for(Text val : values){
            Double PM25 = Double.parseDouble(val.toString().split("\t")[1]);
            Double PM10 = Double.parseDouble(val.toString().split("\t")[2]);
            Double NO2  = Double.parseDouble(val.toString().split("\t")[3]);
            Double SO2 = Double.parseDouble(val.toString().split("\t")[4]);
            Double O3 = Double.parseDouble(val.toString().split("\t")[5]);
            Double CO = Double.parseDouble(val.toString().split("\t")[6]);
            String dateString = val.toString().split("\t")[0];
            Double[] AP = {PM25,PM10,NO2,SO2,O3,CO};
            if (date_ap.containsKey(dateString)) {
                    ArrayList<Double[]> ap_list = date_ap.get(dateString);
                    ap_list.add(AP);
                }
            else{                
                    ArrayList<Double[]> ap_list = new ArrayList<Double[]>();
                    ap_list.add(AP);
                    date_ap.put(dateString,ap_list);                
                }
        }
        // 计算每日各项污染物的平均水平并计算单项指数
        for(Map.Entry<String, ArrayList<Double[]>> entry : date_ap.entrySet()){
            double[] ap_sum = {0,0,0,0,0,0};
            ArrayList<Double[]> apList = entry.getValue();   
            for (int i = 0; i < apList.size(); i++) {
                Double[] ap_i = apList.get(i);
                for (int j =0;j<ap_sum.length;j++){
                        ap_sum[j] = ap_sum[j] + ap_i[j];
                    }
            }

            Double[] ap_avg_index = new Double[6];
            for (int i = 0; i<ap_sum.length;i++ ){
                ap_avg_index[i] = (ap_sum[i]/apList.size())/LIMITS[i];
            }
            apList.add(ap_avg_index);
        }
        
        Double[] city_index = new Double[12];
        HashMap<String,Integer> city_code = new HashMap();
        String[] citys = 
        {"北京","上海","成都","天津",
         "青岛","济南", "厦门","郑州",
         "乌鲁木齐","呼和浩特","海口","昆明"};
        for(int t = 0; t < citys.length; t++){
            city_code.put(citys[t], t);
        }
        for(Map.Entry<String, ArrayList<Double[]>> entry : date_ap.entrySet()){
            ArrayList<Double[]> apList2 = entry.getValue();  
            Double[] ap_avg_index = apList2.get(apList2.size()-1) ;
            for(int t = 0; t < ap_avg_index.length; t++){
                city_index[city_code.get(city)] = ap_avg_index[t];
        }
}
        //String result = results.toString("\n");   
        context.write(key, new Text((city_index[city_code.get(city)]).toString())); 
}
}


