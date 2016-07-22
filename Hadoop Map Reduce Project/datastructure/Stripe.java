package datastructure;

import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Writable;

/**
 * Created by dipen on 4/11/16.
 */
public class Stripe extends MapWritable {
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(Writable key : this.keySet()){
            str.append(" "+key+" = "+this.get(key));
        }
        str.append("]");
        return str.toString();
    }
}
