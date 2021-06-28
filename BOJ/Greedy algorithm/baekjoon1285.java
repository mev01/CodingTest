import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1285 {
	static int N, ans = Integer.MAX_VALUE;
	static int[][] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				if(str.charAt(j) == 'H') arr[i][j] = 0;
				else arr[i][j] = 1;
			}
		}
		
		// 뒤집을 행 결정
		for (int r = 0; r < (1 << N); r++) {
			int sum = 0;
			
			// 각 열 확인하면서 T가 더 작은 방향으로 뒤집거나 뒤집지 않기
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				
				for (int i = 0; i < N; i++) {
					if(((r & (1 << i)) != 0) ^ arr[i][j] == 1) cnt++;
				}
				
				sum += Math.min(cnt, N - cnt);
			}
			ans = Math.min(ans, sum);
		}
		
		System.out.print(ans);
	}
}
