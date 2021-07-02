import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class beakjoon16924 {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static char[][] grid;
	static boolean[][] visited;
	static LinkedList<Cross> list;
	
	static class Cross{
		int x, y, s;

		public Cross(int x, int y, int s) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		grid = new char[N][M];
		visited = new boolean[N][M];
		list = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			grid[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(grid[i][j] == '.') visited[i][j] = true;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(grid[i][j] == '*') findCross(i, j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					System.out.print(-1);
					System.exit(0);
				}
			}
		}
		
		sb.append(list.size()).append('\n');
		for(Cross cross : list) {
			sb.append(cross.x).append(' ').append(cross.y).append(' ').append(cross.s).append('\n');
		}
		System.out.print(sb.toString());
	}

	private static void findCross(int r, int c) {
		int size = 1;
		
		loop:
		while(true) {
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + size * dr[dir];
				int nc = c + size * dc[dir];
				
				if(OutOfMap(nr, nc) || grid[nr][nc] != '*') {
					size--;
					break loop;
				}
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + size * dr[dir];
				int nc = c + size * dc[dir];
				
				visited[nr][nc] = true;
			}
			size++;
		}
		
		if(size != 0) {
			visited[r][c] = true;
			list.add(new Cross(r + 1, c + 1, size));
		}
	}

	private static boolean OutOfMap(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= M) return true;
		return false;
	}
}
