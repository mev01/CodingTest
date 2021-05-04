import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA1949 {
	static int N, K, ans = 0;
	static int[] dirx = {-1, 0, 1, 0}, diry = {0, 1, 0, -1};
	static int[][] map, visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int max = 0;
			
			ans = 0;
			map = new int[N][N];
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > max) max = map[i][j];
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == max){
						visited[i][j] = 1;
						DFS(i, j, 1);
						visited[i][j] = 0;
					}
				}
			}
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.print(sb.toString());
	}
	private static void DFS(int x, int y, int cnt) {
		if(ans < cnt) ans = cnt;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dirx[dir];
			int ny = y + diry[dir];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0){	//범위 체크
				if(map[nx][ny] < map[x][y]){	//내림차순이면 탐색
					visited[nx][ny] = visited[x][y];
					DFS(nx, ny, cnt+1);
					visited[nx][ny] = 0;
				}
				else if(map[nx][ny] - K < map[x][y] && visited[x][y] != 2){	//한번 깎지 않았고 깎아서 내림차순이 되면 탐색
					int temp =  map[nx][ny];
					
					map[nx][ny] = map[x][y] - 1;
					visited[nx][ny] = 2;
					DFS(nx, ny, cnt+1);
					map[nx][ny] = temp;
					visited[nx][ny] = 0;
				}
			}
			
			
		}
	}
}
