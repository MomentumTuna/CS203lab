import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        QReader input=new QReader();
        QWriter out=new QWriter();
        
        int task=input.nextInt();
        for (int i = 0; i < task; i++) {
            int day=input.nextInt();
            node head=new node(-1);
            node tail=new node(400000);
            node[] value=new node[day+2];
            value[0]=head;
            value[1]=tail;
            node iter=head;
            for (int j = 0; j < day; j++) {
                node temp=new node(input.nextInt());
                int back=binarySearch(0,j+1,value,temp);
                temp.next=value[back];
                value[back-1].next=temp;
                
                iter=head; int k=0;
                while(iter.next!=null){
                    value[k]=iter;
                    k++;
                    iter=iter.next;
                }
                value[k]=iter;

                if(j%2==0){
                    out.print(value[1+j/2].value+" ");
                }
                
            }
            out.print("\n");
        }
        
        out.close();
    }

    public static int binarySearch(int l,int r,node[] gifts,node temp){
        if(r-l<=1){
            return r;
        }else{
        
        int mid=(l+r)/2;
        if(gifts[mid].value==temp.value){
            return mid;
        }else{
            if(gifts[mid].value>temp.value){
                return binarySearch(l, mid, gifts, temp);
            }else{
                return binarySearch(mid, r, gifts, temp);
            }
        }
    }
    }

    public static class node{
        int value;
        node next;

        public node(int value){
            this.value=value;
        }
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
