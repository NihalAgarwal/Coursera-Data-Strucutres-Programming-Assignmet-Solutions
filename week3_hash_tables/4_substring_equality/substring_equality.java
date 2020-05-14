import java.util.*;
import java.io.*;

public class substring_equality {

	private static final int p1 = 1000000007;
	private static final int p2 = 1000000009;
	private static final int x = 263;
	private long[] h1, h2;

	public class Solver {
		private String s;
		public Solver(String s) {
			this.s = s;
		}

		public boolean ask(int a, int b, int l, long[] y, long[] z) {


			long r1 = ((h1[a+l]%p1 - (y[l-1]*h1[a])%p1)+ p1) % p1;
			long r2 = ((h2[a+l]%p2 - (z[l-1]*h2[a])%p2)+ p2) % p2;

			long r3 = ((h1[b+l]%p1 - (y[l-1]*h1[b])%p1)+ p1) % p1;
			long r4 = ((h2[b+l]%p2 - (z[l-1]*h2[b])%p2)+ p2) % p2;

//			System.out.println(" Before: "+r1+" "+r2+" "+r3+" "+r4);

			if(r1 == r3){
				if(r2 == r4){
					return true;
				}
			}
			return false;
		}
	}

	public void hashFunc(String s, long m1, long m2){
		h1 = new long[s.length()+1];
		h2 = new long[s.length()+1];
		h1[0] = h2[0] = 0;

		for(int i=1; i<=s.length(); i++){
			h1[i] = (h1[i-1]*x + s.charAt(i-1))%p1;
			h2[i] = (h2[i-1]*x + s.charAt(i-1))%p2;
		}
	}

	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String s = in.next();
		int q = in.nextInt();
		Solver solver = new Solver(s);

		hashFunc(s, p1, p2);
		long[] y = new long[s.length()];
		y[0] = x;
		long[] z = new long[s.length()];
		z[0] = x;
		for(int i=1; i< s.length(); i++){
			y[i] = (y[i-1]*x )%p1;
			z[i] = (z[i-1]*x )%p2;
		}
//		System.out.println(Arrays.toString(y));
//		System.out.println();
//		System.out.println(Arrays.toString(h1));
//		System.out.println(Arrays.toString(h2));
//		System.out.println();
//		System.out.println();
		for (int i = 0; i < q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int l = in.nextInt();

			out.println(solver.ask(a, b, l, y, z) ? "Yes" : "No");
		}
		out.close();
	}

	static public void main(String[] args) throws IOException {
	    new substring_equality().run();
	}

	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
}
