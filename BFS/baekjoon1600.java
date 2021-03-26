import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1600 {
	static int K, N, M;
	static int[] disx = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2}, disy = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1};
	static int[][] map, ans;
	static Queue<Dis> que;
	
	static class Dis{
		int x, y, cnt, crash;
		public Dis(int x, int y, int cnt, int crash) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.crash = crash;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		ans = new int[M][N];
		que = new LinkedList<Dis>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			Arrays.fill(ans[i], Integer.MAX_VALUE);
		}
		ans[0][0] = 0;
		que.add(new Dis(0, 0, 0, 0));
		System.out.print(bfs());
	}

	private static int bfs() {
		if(M-1 == 0 && N-1 == 0) {
			return 0;
		}
		while(!que.isEmpty()) {
			Dis thisDis = que.poll();
			
			for (int i = 0; i < (thisDis.crash < K ? 12 : 4); i++) {
				int nx = thisDis.x + disx[i];
				int ny = thisDis.y + disy[i];
				int ncrash = (i >= 4 ? thisDis.crash+1 : thisDis.crash);
				
				//범위가 맞지 않다면
				if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
				//해당 자리가 벽이라면
				if(map[nx][ny] == 1) continue;
				//내가 더 많이 왔다면
				if(ans[nx][ny] <= ncrash) continue;
				
				if(nx == M-1 && ny == N-1) {
					return thisDis.cnt + 1;
				}
				
				ans[nx][ny] = ncrash;
				que.offer(new Dis(nx, ny, thisDis.cnt + 1, ncrash));
			}
		}
		
		return -1;
	}
}