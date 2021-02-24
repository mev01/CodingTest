import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17070_DP {
	static int N;
	static int[][] map;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		makeMap();
		makePipeDP();
		System.out.println(dp[N][N][0]+dp[N][N][1]+dp[N][N][2]);
	}
	
	private static void makePipeDP() {
		dp[1][2][0] = 1;
		for (int x = 1; x <= N; x++) {
			for (int y = 3; y <= N; y++) {
				if(map[x][y] == 0) {
					comingLeft(x, y);
					comingUp(x,y);
					comingDiag(x,y);
				}
			}
		}
	}

	private static void comingDiag(int x, int y) {
		if(map[x-1][y] == 0 && map[x][y-1] == 0) {
			dp[x][y][1] = dp[x-1][y-1][0] + dp[x-1][y-1][1] +dp[x-1][y-1][2];
		}
	}

	private static void comingUp(int x, int y) {
		dp[x][y][2] = dp[x-1][y][1] + dp[x-1][y][2];
	}

	private static void comingLeft(int x, int y) {
		dp[x][y][0] = dp[x][y-1][0] + dp[x][y-1][1];
	}

	private static void makeMap() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		dp = new int[N+1][N+1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
	}

}
