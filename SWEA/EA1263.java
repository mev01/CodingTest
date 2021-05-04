import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA1263 {
	static int N;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dp = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
					if(dp[i][j] == 0) dp[i][j] = 1000000;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if(i == k) continue;
					for (int j = 0; j < N; j++) {
						if(j == i || j == k) continue;
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int temp = 0;
				for (int j = 0; j < N; j++) {
					if(dp[i][j] == 1000000) continue;
					temp += dp[i][j];
				}
				
				min = Math.min(min, temp);
			}
			
			sb.append("#"+tc+" "+min+'\n');
		}
		System.out.print(sb.toString());
	}
}
