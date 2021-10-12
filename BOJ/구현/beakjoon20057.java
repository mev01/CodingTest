import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon20057 {
	static int N;
	static int[] dr = {0, 1, 0, -1}, dc = {-1, 0, 1, 0};
	static int[][] sandArr = {{0, 0, 2, 0, 0}, {0, 10, 7, 1, 0}, {5, -1, -2, -3, 0}, {0, 10, 7, 1, 0}, {0, 0, 2, 0, 0}};
	static int[][] map;
	
	static class Pos{
		int r, c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int nr = N / 2, nc = N / 2, dir = 0;
		int outSand = 0, nCnt = 0, totalCnt = 1;
		while(!(nr == 0 && nc == 0)) {
			nr = nr + dr[dir];
			nc = nc + dc[dir];
			int remainingSand = map[nr][nc];
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if(sandArr[i][j] > 0) {
						Pos pos = calcPos(new Pos(nr, nc), new Pos(i, j), dir);
						
						int nSand = map[nr][nc] * sandArr[i][j] / 100 ;
						remainingSand -= nSand;
						
						if(OutOfMap(pos)) outSand += nSand;
						else map[pos.r][pos.c] += nSand;
					}
				}
			}
			
			if(OutOfMap(new Pos(nr + dr[dir], nc + dc[dir]))) outSand += remainingSand;
			else map[nr + dr[dir]][nc + dc[dir]] += remainingSand;
			map[nr][nc] = 0;
			
			
			// 다음 위치 계산
			if(++nCnt == totalCnt) {
				if(dir % 2 == 1) {
					++totalCnt;
				}
				
				nCnt = 0;
				dir = (dir + 1) % 4;
			}
		}
		
		System.out.println(outSand);
	}

	private static Pos calcPos(Pos tornadoPos, Pos sandPosBasedTornando, int dir) {
		Pos pos = new Pos(sandPosBasedTornando.r, sandPosBasedTornando.c);
		
		for (int i = 0; i < dir; i++) {
			int temp = pos.r;
			pos.r = N - pos.c - 1;
			pos.c = temp;
		}
		
		pos.r += tornadoPos.r - 2;
		pos.c += tornadoPos.c - 2;
		
		return pos;
	}

	private static boolean OutOfMap(Pos pos) {
		if(pos.r < 0 || pos.r >= N || pos.c < 0 || pos.c >= N) return true;
		return false;
	}
}
