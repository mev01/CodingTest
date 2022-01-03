import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon1080 {
	static int N, M, ans = 0;
	static int[][] arr, nArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		nArr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			
			for(int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			
			for(int j = 0; j < M; j++) {
				nArr[i][j] = str.charAt(j) - '0';
			}
		}
		
		for(int i = 0; i < N - 2; i++) {
			for(int j = 0; j < M - 2; j++) {
				if(arr[i][j] != nArr[i][j]) {
					ans++;
					Reverse(i, j);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] != nArr[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(ans);
	}

	private static void Reverse(int r, int c) {
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				arr[i][j] = 1 - arr[i][j];
			}
		}
	}

}
