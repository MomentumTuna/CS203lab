import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        QReader input=new QReader();
        QWriter out=new QWriter();
        int task=input.nextInt();
        for (int i = 0; i <task; i++) {
            int length=input.nextInt();
            
            node head=new node(-1);
            node iter=head;
            node[] sorted=new node[length];
            node[] sortedTemp=new node[length];
            for (int j = 0; j < length; j++) {
                node temp=new node(input.nextInt());
                iter.next=temp;
                iter=iter.next;
                sorted[j]=temp;
            }
            node tail=new node(-1);
            iter.next=tail;
            mergesort(sorted, sortedTemp, 0, length-1);
            
            stack store=new stack(length+1);
            
            
            int[] answer=new int[length];
            int k=0;
            iter=head.next;
            store.push(400000);
            int j=0;
            while(iter.next!=null){
                
                if(store.getTop()<=sorted[j].value){
                    while(sorted[j].isStacked){
                        j++;
                    }
                    answer[k]=store.getTop();
                    k++;
                    store.pop();
                }else{
                    while(sorted[j].isStacked){
                        j++;
                    }
                    store.push(iter.value);
                    iter.isStacked=true;
                    iter=iter.next;
                }
            }

                

            while(store.top!=0){
                answer[k]=store.getTop();
                k++;
                store.pop();
            }
            for (int h = 0; h < answer.length; h++) {
                out.print(answer[h]+" ");
            }
            out.print("\n");
        }
        out.close();
    }

    public static class node{
        node next;
        int value;
        boolean isStacked=false;

        public node(int value){
            this.value=value;
        }
    }

    public static class stack{
        int[] S;
        int top=-1;
        int size;

        public stack(int size){
            this.S=new int[size];
            this.size=size;
        }

        public stack(int[]S,int size){
            if(size<S.length){
                System.out.println("wrong input");
            }else{this.S=new int[size];
            for (int i = 0; i < S.length; i++) {
                this.S[i]=S[i];
                top++;
            }
            this.size=size;}
        }

        public void push(int input){
            if(this.top==this.size-1){
                System.out.println("StackOverflow");
            }else{
                top++;
                S[top]=input;
            }
        }

        public void pop(){
            if(top==-1){
                System.out.println("EmptyStack");
            }else{
                top--;
            }
        }

        public int getTop(){
            return this.S[this.top];
        }

        public boolean isEmpty(){
            if(this.top==-1){return true;}else{return false;}
        }
    }

    public static void mergesort(node[] array1,node[] array2,int l,int r){
        if(r-l>1){
        int mid=(l+r)/2;
        mergesort(array1,array2,l,mid);
        mergesort(array1,array2,mid+1,r);}
        merge(array1,array2,l,r);
    }
        

 public static void merge(node[] array1,node[] array2,int l, int r){
    int mid=((l+r)/2); 
    int i=0; int j=0; int q=0;
    while(i<mid-l+1&&j<r-mid){
        if(array1[l+i].value>array1[mid+1+j].value){
            array2[l+q]=array1[mid+1+j];j++;q++;
        }else{
            array2[l+q]=array1[l+i];i++;q++;
        }
    }
    while(i<mid-l+1&&j>=r-mid){
        array2[l+q]=array1[l+i];i++;q++;
    }
    while(i>=mid-l+1&&j<r-mid){
        array2[l+q]=array1[mid+1+j];j++;q++;
    }
    for (int k = 0; k < r-l+1; k++) {
        array1[l+k]=array2[l+k];
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
