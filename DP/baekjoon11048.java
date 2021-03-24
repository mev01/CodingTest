import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon11048 {
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][M+1];
		int max = 0;
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				if(i == 1 && j == 1) {
					dp[i][j] = Integer.parseInt(st.nextToken());
					continue;
				}
				max = 0;
				max = Math.max(dp[i-1][j], Math.max(dp[i-1][j-1], dp[i][j-1]));
				dp[i][j] = Integer.parseInt(st.nextToken()) + max;
			}
		}
		
		System.out.print(dp[N][M]);
	}
}
