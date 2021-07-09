import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon15486 {
	static int N;
	static Counseling[] arr;
	static int[] dp;
	
	static class Counseling{
		int T, P;

		public Counseling(int t, int p) {
			super();
			T = t;
			P = p;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new Counseling[N + 1];
		dp = new int[N + 1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			arr[i] = new Counseling(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < N; i++) {
			if(i + 1 <= N) dp[i + 1] = Math.max(dp[i + 1], dp[i]);
			if(i + arr[i].T <= N) dp[i + arr[i].T] = Math.max(dp[i] + arr[i].P, dp[i + arr[i].T]);
		}
		
		System.out.print(dp[N]);
	}
}
