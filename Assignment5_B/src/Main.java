import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
       QReader input=new QReader();
       QWriter out=new QWriter();
        String []pattern0=input.next().split("");
        String []pattern=new String[pattern0.length+1];
        for (int i = 0; i < pattern0.length; i++) {
            pattern[i+1]=pattern0[i];
        }
        int length=pattern.length;
        int []nextArray=new int[length];
        int k=0;
        for (int i = 2; i < nextArray.length; i++) {
            while(k>0&&!pattern[k+1].equals(pattern[i])){
                k=nextArray[k];
            }
            if(pattern[k+1].equals(pattern[i])){k++;
        }
            nextArray[i]=k;}
        for (int i = 1; i < nextArray.length; i++) {
            out.println(nextArray[i]);
        }
        out.close();
        
}
}
class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");
 
    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
 
    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }
 
    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }
 
    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }
 
    public int nextInt() {
        return Integer.parseInt(next());
    }
 
    public long nextLong() {
        return Long.parseLong(next());
    }
}
 
class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
 
    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }
 
    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }
 
    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
