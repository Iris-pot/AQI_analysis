
package AQI;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class AQIBean implements WritableComparable<AQIBean>{
    double PM25;
    double IAQI;
    String city;
    
    public AQIBean(){
    }
    
    public AQIBean(double PM25, String city){
        super();
        this.PM25 = PM25;
        this.city = city;
        
        //数组初始化
        //pollutant_levels存储PM25浓度等级，IAQI_levels存储空气质量分指数IAQI等级
        int[] pollutant_levels = {0, 35, 75, 115, 150, 250, 350, 500};
        int[] IAQI_levels = {0, 50, 100, 150, 200, 300, 400, 500};
        
        //计算IAQI
        for (int i = 0; i < 7; i++){
            if (PM25 > pollutant_levels[i] && PM25 < pollutant_levels[i+1]){
                this.IAQI = (IAQI_levels[i+1] - IAQI_levels[i]) * (PM25 - pollutant_levels[i]) / (pollutant_levels[i+1] - pollutant_levels[i]) + IAQI_levels[i];
            }
        }
    }

    public double getPM25() {
        return PM25;
    }

    public void setPM25(double PM25) {
        this.PM25 = PM25;
    }

    public double getIAQI() {
        return IAQI;
    }

    public void setIAQI(double IAQI) {
        this.IAQI = IAQI;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(PM25);
        out.writeDouble(IAQI);
        out.writeUTF(city);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        PM25 = in.readDouble();
        IAQI = in.readDouble();
        city = in.readUTF();
    }    
    @Override
    public int compareTo(AQIBean a) {
	// TODO Auto-generated method stub
	return (this.IAQI > a.getIAQI())?-1:1;
	}
    
    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return city+"\t"+IAQI;
	}
    
}
