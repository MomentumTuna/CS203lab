import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input=new Scanner(System.in);
        QWriter out=new QWriter();
        int task=input.nextInt();
        for (int i = 0; i < task; i++) {
            int n=input.nextInt();
            int m=input.nextInt();
            
            node[] nodes=new node[n+1];
            for (int j = 1; j < n+1; j++) {
                node temp=new node(j);
                nodes[j]=temp;
            }
            for (int j = 0; j < n-1; j++) {
                int indixa=input.nextInt();
                int indixb=input.nextInt();
                node nodea=nodes[indixa];
                node nodeb=nodes[indixb];
                nodea.haveparent=true;
                nodeb.children.add(nodea);
            }
            node root=new node(-1);
            for (int j = 1; j < n+1; j++) {
                if(!nodes[j].haveparent){
                    root=nodes[j];
                }
            }
            DFS(root,n+1);
            for (int j = 0; j<m; j++) {
                int indixc=input.nextInt();
                int indixf=input.nextInt();
                node father=nodes[indixf];
                node child=nodes[indixc];
                if(father.interval_l<=child.interval_l&&father.interval_r>=child.interval_r){
                    out.print("Yes\n");
                }else{
                    out.print("No\n");
                }
            }
        }
        out.close();
    }

    public static void DFS(node root,int length){
        int counter=0;
        stack yellow=new stack(length+1);
        yellow.push(root);
        counter++;
        root.interval_l=counter;
        root.isyellow=true;
        while(!yellow.isEmpty()){
            node father=yellow.getTop();
            boolean isnotwhite=true;
            ArrayList<node> children=father.children;
            for (int i = 0; i < children.size(); i++) {
                node temp=children.get(i);
                if(!temp.isyellow){
                    temp.isyellow=true;
                    yellow.push(temp);
                    counter++;
                    temp.interval_l=counter;
                    father.DSFchildren.add(temp);
                    isnotwhite=false;
                    break;
                }
                
            }
            if(isnotwhite){
                    counter++;
                    yellow.getTop().interval_r=counter;
                    yellow.pop();}
            
        }
    }
    
    public static boolean iscycled(node root,int length){
        queue level=new queue(length);
        level.enQueue(root);
        while(!level.isEmpty()){
            node parent=level.getFront();
            ArrayList<node> children=parent.children;
            for (int i = 0; i < children.size(); i++) {
                node child=children.get(i);
                if(child.interval_l<parent.interval_l&&child.interval_r>parent.interval_r){
                    return true;
                }
                level.enQueue(child);
            }
            level.deQueue();
        }
        return false;
    }

    public static void level_tranversal(node temp,int length){
        queue level=new queue(length);
        level.enQueue(temp);
        while(!level.isEmpty()){
            node t=level.getFront();
            System.out.printf("%d",t.indix);
            System.out.printf("(%d,%d) ",t.interval_l,t.interval_r);
            ArrayList<node> children=t.DSFchildren;
            for (int i = 0; i < children.size(); i++) {
                level.enQueue(children.get(i));
            }
            level.deQueue();
        }
    }

    
    
    public static class node{
        int indix;
        int interval_l;
        int interval_r;
        boolean isyellow;
        boolean isred;
        boolean haveparent;
        ArrayList<node> children=new ArrayList<>();
        ArrayList<node> DSFchildren=new ArrayList<>();

        public  node(int indix){
            this.indix=indix;
        }
    }

    public static class stack{
        node[] S;
        int top=-1;
        int size;

        public stack(int size){
            this.S=new node[size];
            this.size=size;
        }

        public stack(node[]S,int size){
            if(size<S.length){
                System.out.println("wrong input");
            }else{this.S=new node[size];
            for (int i = 0; i < S.length; i++) {
                this.S[i]=S[i];
                top++;
            }
            this.size=size;}
        }

        public void push(node input){
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

        public node getTop(){
            return this.S[this.top];
        }

        public boolean isEmpty(){
            if(this.top==-1){return true;}else{return false;}
        }
    }

    public static class queue{
        private node[] S;
        private int front=0;
        private int rear=0;

        public queue(int length){
            this.S=new node[length];
        }

        public void enQueue(node temp){
            S[rear]=temp;
            rear++;
        }

        public void deQueue(){
            front++;
        }

        public node getFront(){
            return S[front];
        }

        public boolean isEmpty(){
            if(front==rear){
                return true;
            }else{return false;}
        }

        public int size(){
            return rear-front+1;
        }

        public void clear(){
            while(rear!=front){
                rear--;
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
