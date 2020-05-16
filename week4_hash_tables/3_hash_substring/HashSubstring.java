import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

//    int x=263, int p=1000000007
    private static long hashFunc(String s, int x, int p){
        long res = 0;
        for(int i =0; i< s.length(); i++){
            res = (res*x + s.charAt(i)) % p ;
        }
        return res;
    }


    private static List<Integer> getOccurrences(Data input) {

        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        int x = 263, p = 1000000007;
        long y = 1;
        for (int i = 0; i < m; i++)
            y = (y*x)%p;
        System.out.println(y);
        List<Integer> occurrences = new ArrayList<Integer>();

        long p_hash = hashFunc(s,x, p);
        long m_hash = hashFunc(t.substring(0, m), x, p);

//        System.out.println(Integer.MAX_VALUE);
        for (int i = 0; i  <= n-m; ++i) {
	        boolean equal = true;
//            System.out.println(m_hash+" "+p_hash);
	        // Check if the hashes are equal
            if(p_hash == m_hash) {
                for (int j = 0; j < m; ++j) {
                    if (s.charAt(j) != t.charAt(i + j)) {
                        equal = false;
                        break;
                    }
                }
                if (equal) occurrences.add(i);
            }

            if(i < n-m) m_hash = (m_hash*x + t.charAt(i+m) - t.charAt(i)*y)%p;
            if(m_hash < 0) m_hash = (m_hash+p)%p;
	    }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

