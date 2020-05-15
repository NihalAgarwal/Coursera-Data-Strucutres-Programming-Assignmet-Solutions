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
        for(int i=data.length/2; i>=0; i--){
            heapifyDown(i);
        }
    }

    private void heapifyDown(int i) {

        int l = getLeftIndex(i);
        int r = getRightIndex(i);
        int min = i;

        if(l < data.length && data[l] < data[min]){
            min = l;
        }
        if(r < data.length && data[r] < data[min]){
            min = r;
        }

        if (i != min){
            swap(i, min);
            swaps.add(new Swap(i, min));
            heapifyDown(min);
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
