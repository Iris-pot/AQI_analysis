
package AQIIndex;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;

public class TextArrayWritable extends ArrayWritable {
    private Text[] myValue = new Text[0];
    
    public Text[] getMyValue() {
        return myValue;
    }
 public TextArrayWritable() {
 super(Text.class);
 }
  
 public TextArrayWritable(String[] strings) {
 super(Text.class);
 Text[] texts = new Text[strings.length];
 for (int i = 0; i < strings.length; i++) {
 texts[i] = new Text(strings[i]);
 }
 set(texts);
 }
  @Override
    public String toString() {
        
        StringBuffer result = new StringBuffer();
        
        for(int i =0; i < this.getMyValue().length; i++){
            if(i == this.getMyValue().length -1)
                result.append(this.getMyValue()[i].toString());
            else
                result.append(this.getMyValue()[i].toString()).append(",");
        }
        
        return result.toString();
    }
}