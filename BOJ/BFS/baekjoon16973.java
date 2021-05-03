import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon16973 {
	static int N, M, H, W, sr, sc, fr, fc;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[] pr = new int[4], pc = new int[4];
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pos> que;
	
	static class Pos{
		int r, c, cnt;

		public Pos(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		fr = Integer.parseInt(st.nextToken());
		fc = Integer.parseInt(st.nextToken());
		
		pc[1] = W - 1;
		pr[3] = H - 1;
		pr[2] = H - 1;
		pc[2] = W - 1;
		
		System.out.print(findMoveTimes());
	}

	private static int findMoveTimes() {
		que = new LinkedList<Pos>();
		que.offer(new Pos(sr, sc, 0));
		visited[sr][sc] = true;
		
		while(!que.isEmpty()) {
			Pos pos = que.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				Pos npos = new Pos(pos.r + dr[dir], pos.c + dc[dir], pos.cnt + 1);
				
				// 격자판 밖인지
				if(isOutOfMap(npos)) continue;
				// 방문한 적 있는지
				if(visited[npos.r][npos.c]) continue;
				// 직사각형 면이 벽에 부딪혔는지
				if(isWall(npos, dir)) continue;
				// 목표에 도달 했다면
				if(npos.r == fr && npos.c == fc) return npos.cnt;
				
				que.offer(npos);
				visited[npos.r][npos.c] = true;
			}
		}
		
		return -1;
	}

	private static boolean isOutOfMap(Pos npos) {
		for (int i = 0; i < 4; i++) {
			int r = npos.r + pr[i];
			int c = npos.c + pc[i];
			
			if(!(r > 0 && r <= N && c > 0 && c <= M)) return true;
		}
		return false;
	}

	private static boolean isWall(Pos npos, int dir) {
		int r, c, lr, lc;
		
		if(dir >= 2) {
			r = npos.r + pr[(dir + 1) % 4];
			c = npos.c + pc[(dir + 1) % 4];
			lr = npos.r + pr[dir];
			lc = npos.c + pc[dir];
			
			dir -= 1;
		}
		else {
			r = npos.r + pr[dir];
			c = npos.c + pc[dir];
			lr = npos.r + pr[(dir + 1) % 4];
			lc = npos.c + pc[(dir + 1) % 4];
			
			dir += 1;
		}
		
		for (; r <= lr && c <= lc; r += dr[dir], c += dc[dir]) {
			if(map[r][c] == 1) return true;
		}
		
		return false;
	}
}
