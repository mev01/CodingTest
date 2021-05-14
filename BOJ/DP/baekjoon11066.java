import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 연쇄 행렬 곱셈 문제
 * dp[i][j] = min(dp[i][k] + dp[k+1][j] + (i부터 j까지 원소의 합) ) (i <= k < j)
 * dp[i][j] = 0 (i == j)
 */

public class baekjoon11066 {
	static int[] file, sum;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int K = Integer.parseInt(br.readLine());
			
			file = new int[K + 1];
			sum = new int[K + 1];
			dp = new int[K + 1][K + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				file[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= K; i++) {
				sum[i] = sum[i - 1] + file[i];
			}
			
			for (int i = 1, j = 2; j <= K; j++) {
				for (int t = 0; t + j <= K; t++) {int temp = Integer.MAX_VALUE;
					for (int k = i + t; k < j + t; k++) {
						temp = Math.min(temp, dp[i + t][k] + dp[k + 1][j + t] + sum[j + t] - sum[i + t - 1]);
					}
					
					dp[i + t][j + t] = temp;
				}
			}
			
			sb.append(dp[1][K]).append('\n');
		}
		System.out.println(sb.toString());
	}
}
