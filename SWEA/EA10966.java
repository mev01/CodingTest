import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dis{
	int x, y, cnt;
	public Dis(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}
public class EA10966 {
	static int N, M, ans = 0;
	static int[] dirx = {-1, 0, 1, 0}, diry = {0, 1, 0, -1};
	static char[][] map;
	static int[][] numMove;
	static Queue<Dis> que;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			ans = 0;
			map = new char[N][M];
			numMove = new int[N][M];
			que = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 'W'){
						numMove[i][j] = -1;
						que.offer(new Dis(i, j, 0));
						
					}
				}
			}
			
			BFS();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 'L'){
						ans += numMove[i][j];
					}
				}
			}
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.print(sb.toString());
	}

	private static void BFS() {	
		while(!que.isEmpty()){
			Dis dis = que.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = dis.x + dirx[dir];
				int ny = dis.y + diry[dir];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 'L'){
					if(numMove[nx][ny] == 0){
						numMove[nx][ny] = dis.cnt + 1;
						que.offer(new Dis(nx, ny, dis.cnt + 1));
					}
					else if(numMove[nx][ny] >= 1 && numMove[nx][ny] > dis.cnt + 1){
						numMove[nx][ny] = dis.cnt + 1;
						que.offer(new Dis(nx, ny, dis.cnt + 1));
					}
				}
			}
		}
	}

}
