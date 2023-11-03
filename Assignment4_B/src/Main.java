import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        QReader input=new QReader();
        QWriter out=new QWriter();
        int groupnum=input.nextInt();
        int length1=input.nextInt();
        int length2=input.nextInt();
        queue line1=new queue(length1);
        queue line2=new queue(length2);
        boolean[] group=new boolean[groupnum];
        int[] answer=new int[groupnum];
        
        for (int i = 0; i < length1; i++) {
            line1.enQueue(input.nextInt());
        }
       
        for ( int i = 0; i < length2; i++) {
            line2.enQueue(input.nextInt());
        }
        
        int time=1;
        while(!line1.isEmpty()&&!line2.isEmpty()){
            while(group[line1.getFront()-1]){
                line1.deQueue();
                if(line1.isEmpty()){break;}
            }
            while(group[line2.getFront()-1]){
                line2.deQueue();
                if(line2.isEmpty()){break;}
            }

            if(line1.isEmpty()||line2.isEmpty()){break;}
            answer[line1.getFront()-1]=time;
            group[line1.getFront()-1]=true;
            line1.deQueue();
            answer[line2.getFront()-1]=time;
            group[line2.getFront()-1]=true;
            line2.deQueue();
            time++;
        }
        
        while(!line1.isEmpty()){
             while(group[line1.getFront()-1]){
                line1.deQueue();
                if(line1.isEmpty()){break;}
            }
            if(line1.isEmpty()){
                break;
            }
            answer[line1.getFront()-1]=time;
            group[line1.getFront()-1]=true;
            line1.deQueue();
            time++;
        }

        while(!line2.isEmpty()){
             while(group[line2.getFront()-1]){
                line2.deQueue();
                if(line2.isEmpty()){break;}
            }
            if(line2.isEmpty()){
                break;
            }
            answer[line2.getFront()-1]=time;
            group[line2.getFront()-1]=true;
            line2.deQueue();
            time++;
        }

        for (int i = 0; i < answer.length-1; i++) {
            out.print(answer[i]+" ");
        }
        out.print(answer[groupnum-1]);
        
        out.close();
        
    }

    

    public static class queue{
        private int[] S;
        private int front=0;
        private int rear=0;

        public queue(int length){
            this.S=new int[length+1];
        }

        public void enQueue(int s){
            if(this.size()<S.length-1){
            S[rear]=s;
            rear=(rear+1)%S.length;}else{
                System.out.println("Queue Overflow");
            }
        }

        public void deQueue(){
            if(!this.isEmpty()){
                front=(front+1)%S.length;}else{
                System.out.println("Queue is Empty");
            }
        }

        public int getFront(){
            return S[front];
        }

        public boolean isEmpty(){
            if(front==rear){
                return true;
            }else{return false;}
        }

        public int size(){
            int temp=(rear-front+S.length)%S.length;
            
            return temp;
        }

        public void clear(){
            while(rear!=front){
                rear=(rear-1)%S.length;
            }
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
