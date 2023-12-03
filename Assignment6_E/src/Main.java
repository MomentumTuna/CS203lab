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

        queue cityList=new queue(citySize);
        cityList.enQueue(city[1]);
        int n=input.nextInt();
        int[]indix=new int[n];
        node[] giantedCity=new node[n];
        int j=0;

        for (int i = 0; i < indix.length; i++) {
            indix[i]=input.nextInt();
        }

        while(!cityList.isEmpty()){
            node temp=cityList.getFront();
            
            ArrayList<node> children=temp.children;
            
            for (int i = 0; i < children.size(); i++) {
                node childTemp=children.get(i);
                if(childTemp.isparent){
                    temp.level=childTemp.level+1;
                }else{
                    cityList.enQueue(childTemp);
                }
            }

            
                if(binarySearch(indix, temp.indix)!=-1){
                    giantedCity[j]=temp;
                    j++;
                }
                
            
            temp.isparent=true;
            cityList.deQueue();
        }

        node[] giantedCitytemp=new node[n];
        mergesort(giantedCity, giantedCitytemp, 0, n-1);

        int k=0;
        while(k<n-1){
            
            int temp=0;
            while(k<n-1&&giantedCity[k].level==giantedCity[k+1].level){
                k++;
                temp++;
            }
            giantedCity[k].level=giantedCity[k].level+temp;
            if(k==n-1){break;}
            if(giantedCity[k].level<giantedCity[k+1].level){
                k++;
            }else{
                if(giantedCity[k].level>giantedCity[k+1].level){
                    giantedCity[k+1].level=giantedCity[k].level;
                }
            }

        }

        out.print(giantedCity[n-1].level-1);
        out.close();
        
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
        int level=0;
        int indix;
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
