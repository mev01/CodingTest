import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * LIS N^2 알고리즘을 왼쪽, 오른쪽에서 각각 실행
 */

public class baekjoon11054 {
	static int N;
	static int[] num;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		dp = new int[2][N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		FindIncreasingSeq();
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, dp[0][i] + dp[1][i]);
		}
		
		System.out.print(ans - 1);
	}

	private static void FindIncreasingSeq() {
		for (int i = 0; i < N; i++) {
			int temp = 0;
			for (int j = 0; j < i; j++) {
				// 해당 수가 더 작고 temp보다 크다면
				if(num[i] > num[j] && temp < dp[0][j]) temp = dp[0][j];
			}
			
			dp[0][i] = temp + 1;
		}
		
		for (int i = N - 1; i >= 0; i--) {
			int temp = 0;
			for (int j = N - 1; j > i; j--) {
				// 해당 수가 더 작고 temp보다 크다면
				if(num[i] > num[j] && temp < dp[1][j]) temp = dp[1][j];
			}
			
			dp[1][i] = temp + 1;
		}
	}
}
