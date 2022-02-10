import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon17276 {
	static int N;
	static int[] dr = {1, 1, 1, 0, -1, -1, -1, 0}, dc = {1, 0, -1, -1, -1, 0, 1, 1};
	static int[][] map, rotatedMap, startPoint = new int[8][2];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			rotatedMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					rotatedMap[i][j] = num;
				}
			}
			
			startPoint[1][1] = N / 2;
			startPoint[2][1] = N - 1;
			startPoint[3][0] = N / 2; startPoint[3][1] = N - 1;
			startPoint[4][0] = N - 1; startPoint[4][1] = N - 1; 
			startPoint[5][0] = N - 1; startPoint[5][1] = N / 2; 
			startPoint[6][0] = N - 1; 
			startPoint[7][0] = N / 2; 
			
			int cnt = getRotateCnt(d);
			for (int i = 0; i < 4; i++) {
				Rotate(i, (i + cnt) % 7);
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(rotatedMap[i][j]).append(' ');
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb.toString());
	}

	private static int getRotateCnt(int d) {
		int cnt = d < 0 ? (int)Math.abs(d) / 45 * 7 : d / 45;
		return cnt;
	}

	private static void Rotate(int s, int e) {
		int r1 = startPoint[s][0], c1 = startPoint[s][1];
		int r2 = startPoint[e][0], c2 = startPoint[e][1];
		
		while(r1 >= 0 && r1 < N && c1 >= 0 && c1 < N) {
			rotatedMap[r2][c2] = map[r1][c1];
			
			r1 += dr[s];
			c1 += dc[s];
			r2 += dr[e];
			c2 += dc[e];
		}
	}

}
