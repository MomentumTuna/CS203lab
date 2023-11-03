import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        QReader input=new QReader();
        QWriter out=new QWriter();
        int groupnum=input.nextInt();
        int length1=input.nextInt();
        int length2=input.nextInt();

        stack line1=new stack();
        stack line2=new stack();
        int[] line1Reverse=new int[length1];
        int[] line2Reverse=new int[length2];
        for (int i = 0; i < line1Reverse.length; i++) {
            line1Reverse[i]=input.nextInt();
        }
        for (int i = 0; i < line2Reverse.length; i++) {
            line2Reverse[i]=input.nextInt();
        }
        for (int i = 0; i < length1; i++) {
            line1.push(line1Reverse[length1-1-i]);
        }
        for (int i = 0; i < length2; i++) {
            line2.push(line2Reverse[length2-1-i]);
        }

        int[] answer=new int[groupnum];
        node headDelete=new node(-1);
        
        node tail=new node(-1);
        headDelete.next=tail;
        tail.pre=headDelete;
        node iter1=tail;
        int time=1;
        while(!line1.isEmpty()&&!line2.isEmpty()){
            iter1=headDelete;
            while(iter1.next!=null){
                if(iter1.value==line1.getTop()){
                    line1.pop();
                    iter1.pre.next=iter1.next;
                    iter1.next.pre=iter1.pre;
                    
                }
                if(iter1.value==line2.getTop()){
                    line2.pop();
                    iter1.pre.next=iter1.next;
                    iter1.next.pre=iter1.pre;
                    
                }
                iter1=iter1.next;
            }
            iter1=iter1.pre;
            if(line1.isEmpty()||line2.isEmpty()){break;}
            
            if(line1.getTop()==line2.getTop()){
                answer[line1.getTop()-1]=time;
                node temp=new node(line1.getTop());
                temp.next=iter1.next;
                iter1.next.pre=temp;
                iter1.next=temp;
                temp.pre=iter1;
                iter1=iter1.next;
                line1.pop(); line2.pop();
            }else{
                answer[line1.getTop()-1]=time;
                node temp1=new node(line1.getTop());
                temp1.next=iter1.next;
                iter1.next.pre=temp1;
                iter1.next=temp1;
                temp1.pre=iter1;
                iter1=iter1.next;
                line1.pop();
                
                answer[line2.getTop()-1]=time;
                node temp2=new node(line2.getTop());
                temp2.next=iter1.next;
                iter1.next.pre=temp2;
                temp2.pre=iter1;
                iter1.next=temp2;
                iter1=iter1.next;
                line2.pop();
            }
            time++;

        }

        while(!line1.isEmpty()){
            iter1=headDelete.next;
            while(iter1.next!=null){
                if(iter1.value==line1.getTop()){
                    line1.pop();
                    iter1.pre.next=iter1.next;
                    iter1.next.pre=iter1.pre;
                    
                }
            iter1=iter1.next;}
            iter1=iter1.pre;
            if(line1.isEmpty()){break;} 
            answer[line1.getTop()-1]=time;
                node temp1=new node(line1.getTop());
                temp1.next=iter1.next;
                iter1.next.pre=temp1;
                iter1.next=temp1;
                temp1.pre=iter1;
                iter1=iter1.next;
                line1.pop();
            
            time++;
        }

        while(!line2.isEmpty()){
            iter1=headDelete.next;
            while(iter1.next!=null){
                if(iter1.value==line2.getTop()){
                    line2.pop();
                    iter1.pre.next=iter1.next;
                    iter1.next.pre=iter1.pre;
                    iter1=iter1.next;
                }
                iter1=iter1.next;}
                iter1=iter1.pre;
                if(line2.isEmpty()){break;}
                answer[line2.getTop()-1]=time;
                node temp1=new node(line2.getTop());
                temp1.next=iter1.next;
                iter1.next.pre=temp1;
                iter1.next=temp1;
                temp1.pre=iter1;
                iter1=iter1.next;
                line2.pop();
            
            time++;
        }
        for (int i = 0; i < answer.length; i++) {
            out.print(answer[i]+" ");
        }
        out.close();
        
    }


    public static class stack{
        node head=new node(-1);
        node top=head;
    

        public stack(){
            
        }

        

        public void push(int s){
            node temp=new node(s);
            top.next=temp;
            temp.pre=top;
            top=top.next;
        }

        public void pop(){
            top=top.pre;
        }

        public int getTop(){
            return top.value;
        }

        public boolean isEmpty(){
            if(top.pre==null){return true;}else{return false;}
        }

        public void delete(int s){
            node iter=top;
            while(iter.pre!=null){
            while(iter.value!=s&&iter.pre!=null){
                iter=iter.pre;
            }
            if(iter.value!=-1){
                if(iter.equals(top)){top=top.pre;}
                iter.pre.next=iter.next;
                iter.next.pre=iter.pre;
                iter=iter.pre;
            }}
        }
    }

    public static class node{
        int value;
        node next;
        node pre;
        
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
