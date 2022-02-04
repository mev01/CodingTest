import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class beakjoon7569 {
	static int M, N, H, ans = 0;
	static int[] dh = {-1, 1, 0, 0, 0, 0}, dr = {0, 0, -1, 0, 1, 0}, dc = {0, 0, 0, 1, 0, -1};
	static int[][][] board;
	static Queue<Tomato> que;
	
	static class Tomato{
		int h, r, c, day;

		public Tomato(int h, int r, int c, int day) {
			super();
			this.h = h;
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		board = new int[H][N][M];
		que = new LinkedList<Tomato>();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j2 = 0; j2 < M; j2++) {
					int state = Integer.parseInt(st.nextToken());
					
					board[i][j][j2] = state;
					if(state == 1) que.offer(new Tomato(i, j, j2, 0));
				}
			}
		}
		
		while(!que.isEmpty()) {
			Tomato tomato = que.poll();
			ans  = tomato.day;
			
			for (int dir = 0; dir < 6; dir++) {
				int nh = tomato.h + dh[dir];
				int nr = tomato.r + dr[dir];
				int nc = tomato.c + dc[dir];
				
				if(isOutOfBoard(nh, nr, nc)) continue;
				if(board[nh][nr][nc] != 0) continue;
				
				board[nh][nr][nc] = 1;
				que.offer(new Tomato(nh, nr, nc, tomato.day + 1));
			}
		}
		
		boolean isPoss = true;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if(board[i][j][j2] == 0) isPoss = false;
				}
			}
		}
		
		System.out.println(isPoss ? ans : -1);
	}

	private static boolean isOutOfBoard(int h, int r, int c) {
		if(h < 0 || h >= H || r < 0 || r >= N || c < 0 || c >= M) return true;
		return false;
	}

}
