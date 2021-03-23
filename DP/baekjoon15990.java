import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon15990 {
	static int max = 0;
	static int[] input;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		input = new int[T];
		for (int tc = 0; tc < T; tc++) {
			input[tc] = Integer.parseInt(br.readLine());
			if(max < input[tc]) max = input[tc];
		}
		
		dp = new int[max+1][3];
		dp[1][0] = 1;
		dp[2][1] = 1;
		dp[3][0] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		
		for (int i = 4; i <= max; i++) {
			dp[i][0] = (dp[i-1][1] + dp[i-1][2]) % 1000000009;
			dp[i][1] = (dp[i-2][0] + dp[i-2][2]) % 1000000009;
			dp[i][2] = (dp[i-3][0] + dp[i-3][1]) % 1000000009;
		}
		
		for (int tc = 0; tc < T; tc++) {
			sb.append(((dp[input[tc]][0] + dp[input[tc]][1]) % 1000000009 + dp[input[tc]][2]) % 1000000009).append('\n');
		}
		
		System.out.print(sb.toString());
	}
}
