import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2468 {
	static int[] disX = {-1,0,1,0}, disY = {0,1,0,-1};
	static int[][] map;
	static int N, ans = 0;

	static void DFS(int x, int y, int level) {
		map[x][y] -= 1;
		for (int k = 0; k < 4; k++) {
			if(x+disX[k]>=0 && x+disX[k]<N && y+disY[k]>=0 && y+disY[k]<N) {
				if(map[x+disX[k]][y+disY[k]] >= level)
					DFS(x+disX[k], y+disY[k], level);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int maxNum = 0, ans = 0;
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(maxNum < map[i][j]) maxNum = map[i][j];
			}
		}
		
		for (int level = maxNum; level >= 1; level--) {
			int tmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] >= level) {
						DFS(i, j, level);
						tmp++;
					}
				}
			}
			if(ans<tmp) ans = tmp;
			else break;
		}
		System.out.println(ans);
	}
}