import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    class ThreadTime{
        long starting_time;
        int thread_number;
        long execution_time;
        ThreadTime(int t1, long t2, long t3){
            this.thread_number = t1;
            this.starting_time = t2;
            this.execution_time = t3;
        }

        public String toString(){
            return"{T: "+this.thread_number+" S: "+this.starting_time+" E: "+this.execution_time+" }";
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
//        if (jo)

        PriorityQueue<ThreadTime> p = new PriorityQueue<>(new Comparator<ThreadTime>() {
            @Override
            public int compare(ThreadTime o1, ThreadTime o2) {
                long temp1 = (o1.starting_time+o1.execution_time) - (o2.starting_time+o2.execution_time);

                if ( temp1 == 0){
                    return o1.thread_number-o2.thread_number;
                }
                return temp1 < 0 ? -1:1;
            }
        });
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        int i =0;
        for(; i<numWorkers && i<jobs.length; i++){
            p.add(new ThreadTime(i, 0, jobs[i]));
            assignedWorker[i] = i;
            startTime[i] = 0;
        }
//        System.out.println(p);
        for (; i< jobs.length; i++){
            ThreadTime temp = p.poll();
//            System.out.println(temp);
            p.add(new ThreadTime(temp.thread_number, temp.starting_time+temp.execution_time, jobs[i]));
            assignedWorker[i] = temp.thread_number;
            startTime[i] = temp.starting_time+temp.execution_time;
//            System.out.println(p);
        }
//        System.out.println(p.size());

    }
//private void assignJobs() {
//    // TODO: replace this code with a faster algorithm.
//    assignedWorker = new int[jobs.length];
//    startTime = new long[jobs.length];
//    long[] nextFreeTime = new long[numWorkers];
//    for (int i = 0; i < jobs.length; i++) {
//        int duration = jobs[i];
//        int bestWorker = 0;
//        for (int j = 0; j < numWorkers; ++j) {
//            if (nextFreeTime[j] < nextFreeTime[bestWorker])
//                bestWorker = j;
//        }
//        System.out.println(Arrays.toString(nextFreeTime)+" "+bestWorker);
//        assignedWorker[i] = bestWorker;
//        startTime[i] = nextFreeTime[bestWorker];
//        nextFreeTime[bestWorker] += duration;
//    }
//}

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
