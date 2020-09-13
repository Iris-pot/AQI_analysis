
package AQI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class CityAQISortTest{

    public static void main(String[] args) {
        
        String[] cityArr = {"上海","乌鲁木齐","北京","厦门","呼和浩特","天津",
            "成都","昆明","济南","海口","郑州","青岛"};
        Double[] AQIArr = {55.18360058440512,105.50207880024911,70.24525967238151,37.90453666222592,
            55.0762874278499,72.85804564495083,74.10958049198001,32.625129602441895,106.56531048165962,
            22.56553826042011,119.8591981616064,78.55928168903378};
        
        List<CityAQI> list = new ArrayList<CityAQI>();
        for (int i=0;i<cityArr.length;i++){
            CityAQI caqi = new CityAQI(cityArr[i], AQIArr[i]);
            list.add(caqi);
        }

        Collections.sort(list, new Comparator<CityAQI>() {
            @Override
            public int compare(CityAQI o1, CityAQI o2) {
                String s1 = o1.getcity();
                String s2 = o2.getcity();
  
                int temp = s1.compareTo(s2);
  
                if(temp != 0){
                    return  temp;
                }
  
                double m1 = o1.getaqi();
                double m2 = o2.getaqi();
  
                return m2>m1?1:-1;
            }
        }
  
   System.out.println(list);
  }
  
     // 订单类
  public static class CityAQI{
 
   public String city;
   public double aqi;
         
   public CityAQI(String city,  double aqi) {
    this.city = city;
    this.aqi = aqi;
   }
         
   public String getcity() {
    return city;
   }
  
   public double getaqi() {
    return aqi;
   }
  
   @Override
   public String toString() {
    return "CityAQI{" +
      "city='" + city + '\'' +
      ", aqi=" + aqi +
      '}';
   }
  }
 }
