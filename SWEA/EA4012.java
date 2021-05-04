import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EA4012 {
	static int N, flavA, flavB, ans = Integer.MAX_VALUE;
	static int[] foodA, foodB, ingre = new int[2];
	static int[][] map;
	static boolean[] check;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			check = new boolean[N];
			foodA = new int[N/2];
			foodB = new int[N/2];
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			foodComb(0, 0);
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.print(sb.toString());
	}
	private static void foodComb(int cnt, int idx) {
		if(cnt == N/2) {
			int disa = 0, disb = 0;
			for (int i = 0; i < N; i++) {
				if(check[i]) foodA[disa++] = i;
				else foodB[disb++] = i;
			}
			
			flavA = 0;
			flavB = 0;
			ingreComb(0, 0);
			
			ans = Math.min(ans, Math.abs(flavA - flavB));
			return;
		}
		for (int i = idx; i < N; i++) {
			check[i] = true;
			foodComb(cnt+1, i+1);
			check[i] = false;
		}
	}
	private static void ingreComb(int cnt, int idx) {
		if(cnt == 2) {
			flavA += map[foodA[ingre[0]]][foodA[ingre[1]]] + map[foodA[ingre[1]]][foodA[ingre[0]]];
			flavB += map[foodB[ingre[0]]][foodB[ingre[1]]] + map[foodB[ingre[1]]][foodB[ingre[0]]];

			return;
		}
		for (int i = idx; i < N/2; i++) {
			ingre[cnt] = i;
			ingreComb(cnt+1, i+1);
		}
	}
}
