import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dis{
	int x, y;
	public Dis(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class baekjoon2206 {
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
		breakposs = new LinkedList<Dis>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				ans[i][j] = Integer.MAX_VALUE;
			}
		}
		ans[0][0] = 1;

		//벽이 없는 곳만 이동하면서 부술 수 있는 벽 표시
		que.add(new Dis(0, 0));
		bfs(true);
		//부술 수 있는 하나씩 벽을 부수면서 벽이 아닌 곳으로 이동
		while(!breakposs.isEmpty()) {
			que.add(breakposs.poll());
			bfs(false);
		}
		
		System.out.print((ans[N-1][M-1] == Integer.MAX_VALUE) ? -1 : ans[N-1][M-1]);
	}

	private static void bfs(boolean isChangeBlock) {
		while(!que.isEmpty()) {
			Dis thisDis = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = thisDis.x + disx[i];
				int ny = thisDis.y + disy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if(map[nx][ny] == 1) {
						if(ans[nx][ny] > ans[thisDis.x][thisDis.y] + 1 && isChangeBlock) {
							breakposs.add(new Dis(nx, ny));
							ans[nx][ny] = ans[thisDis.x][thisDis.y] + 1;
						}
					}
					else {
						if(ans[nx][ny] > ans[thisDis.x][thisDis.y] + 1) {
							que.add(new Dis(nx, ny));
							ans[nx][ny] = ans[thisDis.x][thisDis.y] + 1;
						}
					}
				}
			}
		}
	}
}