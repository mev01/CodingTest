import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon2579 {
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		
		int cost = Integer.parseInt(br.readLine());
		dp[1][0] = cost;
		dp[1][1] = cost;
		for (int i = 2; i <= N; i++) {
			cost = Integer.parseInt(br.readLine());
			dp[i][0] = cost + dp[i-1][1];
			dp[i][1] = cost + Math.max(dp[i-2][0], dp[i-2][1]);
		}
		
		System.out.print(Math.max(dp[N][0], dp[N][1]));
	}
}
