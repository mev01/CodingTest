import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon11060 {
	static int N;
	static int[] arr, dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N && j <= i + arr[i]; j++) {
				if(dp[i] != Integer.MAX_VALUE) dp[j] = Math.min(dp[j], dp[i] + 1);
			}
		}
		
		System.out.print(dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1]);
	}
}
