import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon9084 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] coin = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			int[] dp = new int[M + 1];
			dp[0] = 1;
			
			for(int i = 0 ; i < N; i++) {
				for(int j = coin[i]; j <= M; j++) {
					dp[j] += dp[j - coin[i]];
				}
			}
			
			sb.append(dp[M]).append('\n');
		}
		
		System.out.println(sb.toString());
	}

}
