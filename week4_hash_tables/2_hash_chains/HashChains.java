import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private LinkedList<String>[] elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
         out.flush();
    }

    private void processQuery(Query query) {

        int index = query.s != null ? hashFunc(query.s): 0;
//        System.out.println(query.type);
        switch (query.type) {

            case "add":
                if(elems[index] == null){
                    elems[index] = new LinkedList<>();
                    elems[index].add(query.s);
                }
                else{
                    if(!elems[index].contains(query.s)) elems[index].addFirst(query.s);
                }
//                System.out.println(Arrays.toString(elems));
                break;

            case "del":
                if(elems[index] != null){
                    elems[index].remove(query.s);
                }
                break;

            case "find":
                boolean foud = false;
                if(elems[index] != null){
                    if(elems[index].contains(query.s)){
                        foud = true;
                    }
                }
                writeSearchResult(foud);
                break;

            case "check":
                if(elems[query.ind] != null){
                    for(String s : elems[query.ind]){
                        System.out.print(s+" ");
                    }
                }
                System.out.println();
                break;

            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
//        switch (query.type) {
//            case "add":
//                if (!elems.contains(query.s))
//                    elems.add(0, query.s);
//                break;
//            case "del":
//                if (elems.contains(query.s))
//                    elems.remove(query.s);
//                break;
//            case "find":
//                writeSearchResult(elems.contains(query.s));
//                break;
//            case "check":
//                for (String cur : elems)
//                    if (hashFunc(cur) == query.ind)
//                        out.print(cur + " ");
//                out.println();
//                // Uncomment the following if you want to play with the program interactively.
//                // out.flush();
//                break;
//            default:
//                throw new RuntimeException("Unknown query: " + query.type);
//        }
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        elems = new LinkedList[bucketCount];
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
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
