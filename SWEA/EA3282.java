import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA3282 {
	static int N, K;
	static int[] vol, cost;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			vol = new int[N+1];
			cost = new int[N+1];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				vol[i] = Integer.parseInt(st.nextToken());
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[N+1][K+1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= K; j++) {
					if(vol[i] <= j)
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - vol[i]] + cost[i]);
					else
						dp[i][j] = dp[i - 1][j];
				}
			}
			
			sb.append("#"+tc+" "+dp[N][K]+'\n');
		}
		System.out.print(sb.toString());
	}
}
