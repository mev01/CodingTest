import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14500 {
	static int[][] map;
	static int[] dirX = {-1,0,1,0}, dirY = {0,1,0,-1};
	static int N, M, ans = 0, count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		
		for (int x = 1; x <= N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 1; y <= M; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= M; y++) {
				DFS(x, y, 0, 0, 1, 0);
			}
		}
		
		System.out.println(ans);
	}
	
	private static void DFS(int x, int y, int prex, int prey, int cnt, int sum) {
		if(cnt==4){
			if(ans < sum+map[x][y]) ans = sum+map[x][y];
			count++;
			return;
		}
		if(cnt == 1){
			int tmp1 = Math.max(map[x][y]+map[x][y+1]+map[x+1][y]+map[x][y-1],map[x][y]+map[x-1][y]+map[x][y-1]+map[x+1][y]);
			int tmp2 = Math.max(map[x][y]+map[x][y-1]+map[x-1][y]+map[x][y+1], map[x][y]+map[x-1][y]+map[x][y+1]+map[x+1][y]);
			ans = Math.max(ans, Math.max(tmp1, tmp2));
		}
		for (int i = 0; i < 4; i++) {
			if(x+dirX[i]>=1 && x+dirX[i]<=N && y+dirY[i]>=1 && y+dirY[i]<=M)
				if(!(x+dirX[i] == prex && y+dirY[i] == prey))
					DFS(x+dirX[i], y+dirY[i], x, y, cnt+1, sum+map[x][y]);
		}
		
	}
}