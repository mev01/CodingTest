import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon13398 {
	static int ans;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][2];	// [0]은 한번도 수를 제거하지 않은 경우 [1]은 한번 수를 제거한 경우
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = arr[0];
		ans = arr[0];
		
		for (int i = 1; i < N; i++) {
			// dp[i - 1][0]이 0보다 크지 않다면 새로 시작하는게 더 크다
			dp[i][0] = dp[i - 1][0] > 0 ? dp[i - 1][0] + arr[i] : arr[i];
			// 한 번도 수를 제거하지 않은 경우 + arr[i]를 더하지 않을 경우
			// 한 번 수를 제거한 경우 + arr[i]를 더하는 경우
			// 두 경우를 비교하여 큰 값을 넣는다.
			dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
			
			ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
		}
		
		System.out.print(ans);
	}
}
