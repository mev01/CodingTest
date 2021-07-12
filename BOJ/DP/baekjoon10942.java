import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10942 {
	static int N, M;
	static int[] arr;
	static boolean[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dp = new boolean[N + 1][N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 1; j + i <= N; j++) {
				if(i == 0) {
					dp[j][j + i] = true;
				}
				else if(i == 1) {
					if(arr[j] == arr[j + i]) 
						dp[j][j + i] = true;
				}
				else {
					if(arr[j] == arr[j + i] && dp[j + 1][j + i - 1]) 
						dp[j][j + i] = true;
				}
			}
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(dp[s][e]) sb.append(1);
			else sb.append(0);
			sb.append('\n');
		}
		
		System.out.print(sb.toString());
	}
}
