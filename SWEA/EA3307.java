import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA3307 {
	static int[] arr, dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			arr = new int[N];
			dp = new int[N];
			int max = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				dp[i] = 1;
				
				for (int j = 0; j < i; j++) {
					if(arr[j] < arr[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				if(max < dp[i])	max = dp[i];
			}
			sb.append("#"+tc+" "+max+'\n');
		}
		System.out.print(sb.toString());
	}
}
