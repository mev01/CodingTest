import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon12582 {
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		dp[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1] + 1;
			if(i % 2 == 0 && dp[i] > dp[i/2] + 1) dp[i] = dp[i/2] + 1;
			if(i % 3 == 0 && dp[i] > dp[i/3] + 1) dp[i] = dp[i/3] + 1;
		}
		
		sb.append(dp[N]).append('\n');
		while(N != 1) {
			sb.append(N).append(' ');
			if(dp[N - 1] + 1 == dp[N]) N--;
			else if(N % 2 == 0 && dp[N] == dp[N/2] + 1) N = N/2;
			else if(N % 3 == 0 && dp[N] == dp[N/3] + 1) N = N/3;
		}
		
		sb.append(1);
		System.out.print(sb.toString());
	}
}
