package datastructure;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by dipen on 4/11/16.
 */
public class Pair implements WritableComparable {
    private String left;
    private String right;

    public Pair(String left, String right) {
        this.left = left.trim();
        this.right = right.trim();
    }

    public Pair(){

    }

    public String getLeft() {
        return left.trim();
    }

    public void setLeft(String left) {
        this.left = left.trim();
    }

    public String getRight() {
        return right.trim();
    }

    public void setRight(String right) {
        this.right = right.trim();
    }

    @Override
    public int compareTo(Object o) {
        Pair p2 = (Pair) o;
        if(this.left.compareTo(p2.left) == 0) {
            if (this.right.equals("*"))
                return 1;
            if (p2.right.equals("*"))
                return -1;
            else
                return this.right.compareTo(p2.right);
        }
        return this.left.compareTo(p2.left);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeChars(left);
        dataOutput.writeChars("\n");
        dataOutput.writeChars(right);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        left = dataInput.readLine();
        right = dataInput.readLine();
    }

    @Override
    public String toString(){
        return "<"+left+", "+right+">";
    }

    @Override
    public int hashCode(){
        int result = 0;
        result += left.trim().hashCode()+ right.trim().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o){
        Pair check = (Pair) o;
        if(check.left.equals(this.left)&&check.right.equals(this.right))
            return true;
        return false;
    }
}
