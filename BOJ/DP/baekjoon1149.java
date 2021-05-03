import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1149 {
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		dp = new int[T][3];
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < T; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][j] += Math.min(dp[i-1][(j + 1) % 3], dp[i-1][(j + 2) % 3]);
			}
		}
		
		System.out.print(Math.min (Math.min( dp[T - 1][0], dp[T - 1][1]), dp[T - 1][2]) );
	}
}
