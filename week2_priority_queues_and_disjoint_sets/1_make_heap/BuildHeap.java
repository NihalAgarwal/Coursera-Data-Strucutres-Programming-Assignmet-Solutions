import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
//    private int[] data_copy;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private static int getLeftIndex(int i){
        return (i*2)+1;
    }

    private static int getRightIndex(int i){
        return (i*2)+2;
    }

    private static int getParentIndex(int i){
        return (i-1)/2;
    }

    private void swap(int i, int j){
        int temp = data[i];
        data[i] =  data[j];
        data[j] = temp;
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }




    private void generateSwaps() {
        swaps = new ArrayList<Swap>();
        int p, temp;
        int a  = Integer.MAX_VALUE;
        int b  = Integer.MAX_VALUE;
//        System.out.println(Arrays.toString(data));
        for(int i=data.length-1; i>=0; i--){
            temp = i;
            p = getParentIndex(i);
            while(p >= 0 && data[p] > data[temp]){
                swap(temp,p);
                swaps.add(new Swap(p,temp));
                temp = p;
                p = getParentIndex(temp);
//                b -=1;
            }
//            if(a == b){
//                break;
//            }
//            a = b;
//            System.out.println(Arrays.toString(data));
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
