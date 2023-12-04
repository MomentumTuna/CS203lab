import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        QReader input=new QReader();
        QWriter out=new QWriter();
        int citySize=input.nextInt();
        node[] city=new node[citySize+1];
        for (int i = 1; i <citySize+1; i++) {
            city[i]=new node();
            city[i].indix=i;
        }
        for (int i = 0; i < citySize-1; i++) {
            int u=input.nextInt();
            int v=input.nextInt();
            city[u].children.add(city[v]);
            city[v].children.add(city[u]);
        }
        
        int n=input.nextInt();
        int[]indix=new int[n];

        for (int i = 0; i < indix.length; i++) {
            indix[i]=input.nextInt();
        }
        int level=1;
        ArrayList<node> level1=city[1].children;
        ArrayList<Integer> answer=new ArrayList<>();
        city[1].isparent=true;
        for (int i = 0; i < level1.size(); i++) {
            node temp=level1.get(i);
            if(!temp.isparent){
            temp.isparent=true;
            node pre=city[1];
            distanceSearch(pre, answer, temp, level, indix);}
        }
        Collections.sort(answer);
        out.println(answer.get(answer.size()-1));
        out.close();
        
    }

    public static void distanceSearch(node pre,ArrayList<Integer> answer,node temp,int level,int[]indices){
        temp.isparent=true;
        if(temp.children.size()==1){
            if(!temp.isgiant){
                answer.add(pre.level+pre.wait);
            }else{
                answer.add(temp.level+temp.wait);
            }
        }else{
        level++;
        int counter=0;
        ArrayList<node> children=temp.children;
        for (int i = 0; i < children.size(); i++) {
            node childTemp=children.get(i);
            if(childTemp.isparent){}else{
            if(binarySearch(indices, childTemp.indix)!=-1){
                childTemp.level=level;
                childTemp.isgiant=true;
                counter++;}
            }
        }
        
        for (int i = 0; i < children.size(); i++) {
            node childTemp=children.get(i);
            if(!childTemp.isparent){
            if(childTemp.isgiant){
                if(childTemp.level<=pre.level+pre.wait){
                    childTemp.wait=counter-1+pre.wait-(childTemp.level-pre.level)+1;
                }else{
                    childTemp.wait=counter-1;
                }
                distanceSearch(childTemp, answer, childTemp, level, indices);
            }else{
                distanceSearch(pre, answer, childTemp, level, indices);
            }}
        }
    }


    }

    public static int binarySearch(int[] nums,int target) {
		int left = 0;
		int right = nums.length;  //初始条件
		while(left < right) {
			int mid = left +(right-left)/2;
			if(nums[mid] == target) {
				return mid;
			}else if(nums[mid] < target) {
				left  = mid + 1;  //向右查找
			}else {
				right  = mid;  //向左查找
			}
		}
		
		if(left != nums.length && nums[left] == target) {
			return left;
		}
		return -1;
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
        if(array1[l+i].level>array1[mid+1+j].level){
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

    public static class queue{
        private node[] S;
        private int front=0;
        private int rear=0;

        public queue(int length){
            this.S=new node[length];
        }

        public void enQueue(node s){
            S[rear]=s;
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

    public static class node{
        ArrayList<node> children=new ArrayList<>();
        boolean isparent;
        boolean isgiant;
        int level=0;
        int indix;
        int wait=0;
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