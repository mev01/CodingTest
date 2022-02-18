import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon11055 {
	static int N, ans;
	static int[] num, dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		num = new int[N + 1];
		dp = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if(num[j] < num[i] && dp[i] < dp[j] + num[i]) {
					dp[i] = dp[j] + num[i];
				}
				ans = Math.max(ans, dp[i]);
			}
		}
		
		System.out.println(ans);
	}

}
