import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dis{
	int x, y, cnt, crash;
	public Dis(int x, int y, int cnt, int crash) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.crash = crash;
	}
}

public class baekjoon2206_2 {
	static int N, M;
	static int[] disx = {-1, 0, 1, 0}, disy = {0, 1, 0, -1};
	static int[][] map, ans;
	static Queue<Dis> que, breakposs;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		ans = new int[N][M];
		que = new LinkedList<Dis>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		ans[0][0] = 1;
		que.add(new Dis(0, 0, 1, -2));
		System.out.print(bfs());
	}

	private static int bfs() {
		while(!que.isEmpty()) {
			Dis thisDis = que.poll();
			
			if(thisDis.x == N-1 && thisDis.y == M-1) {
				return thisDis.cnt;
			}
			for (int i = 0; i < 4; i++) {
				int nx = thisDis.x + disx[i];
				int ny = thisDis.y + disy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if(ans[nx][ny] <= thisDis.crash) continue;
					
					//벽이라면
					if(map[nx][ny] == 1) {
						//crash가 1이라면 가능
						if(thisDis.crash == -2) {
							ans[nx][ny] = thisDis.crash + 1;	////
							que.offer(new Dis(nx, ny, thisDis.cnt + 1, -1));
						}
					}
					else {
						ans[nx][ny] = thisDis.crash;	////
						que.offer(new Dis(nx, ny, thisDis.cnt + 1, thisDis.crash));
					}
				}
			}
		}
		
		return -1;
	}
}