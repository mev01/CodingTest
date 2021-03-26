import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon12100 {
	static int N, ans = 0;
	static int[] dirx = {-1, 1, 0, 0}, diry = {0, 0, -1, 1};
	static Block[][] map;
	
	static class Block{
		int val;
		boolean crash;
		
		public Block(int val, boolean crash) {
			super();
			this.val = val;
			this.crash = crash;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		//map구성
		map = new Block[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = new Block(temp, false);
			}
		}
		
		for (int dir = 0; dir < 4; dir++) {
			move(map, dir, 0);
		}
		
		System.out.print(ans);
	}
	private static void move(Block[][] preMap, int predir, int cnt) {
		if(cnt == 5) {
			//답 구하기
			getAnswer(preMap);
			return;
		}
		
		// map 복사
		Block[][] nMap = copyMap(preMap);
		// map predir방향으로 밀기
		moveblock(nMap, predir);
		//그전 배열과 지금 배열이 같다면 
		if(isSame(preMap, nMap)) {
			getAnswer(preMap);
			return;
		}
		// 모든 방향으로 재귀
		for (int dir = 0; dir < 4; dir++) {
			move(nMap, dir,cnt + 1);
		}
	}
	private static void getAnswer(Block[][] preMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = Math.max(ans, preMap[i][j].val);
			}
		}
	}
	private static boolean isSame(Block[][] preMap, Block[][] nMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(preMap[i][j].val != nMap[i][j].val) return false;
			}
		}
		return true;
	}
	private static void moveblock(Block[][] nMap, int dir) {
		int sx, sy, step;
		if(dir % 2 == 0) {	// 위쪽, 왼쪽
			sx = 0;
			sy = 0;
			step = 1;
		}
		else {	// 오른쪽, 아래쪽
			sx = N - 1;
			sy = N - 1;
			step = -1;
		}
		
		for (int i = sx; i >= 0 && i < N; i += step) {
			for (int j = sy; j >= 0 && j < N; j += step) {
				if(nMap[i][j].val != 0) {
					pushBlock(nMap, i, j, dir);
				}
			}
		}
	}
	private static void pushBlock(Block[][] nMap, int x, int y, int dir) {
		int nx = x + dirx[dir];
		int ny = y + diry[dir];
		
		while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
			if(nMap[nx][ny].val > 0) {
				if(nMap[nx][ny].val == nMap[x][y].val && !nMap[nx][ny].crash) {	// 같은 값의 블록과 충돌
					nMap[nx][ny].val += nMap[x][y].val;
					nMap[x][y].val = 0;
					nMap[nx][ny].crash = true;
				}
				break;
			}
			nMap[nx][ny].val = nMap[x][y].val;
			nMap[x][y].val = 0;
			x = nx;
			y = ny;
			nx = nx + dirx[dir];
			ny = ny + diry[dir];
		}
	}
	private static Block[][] copyMap(Block[][] preMap) {
		Block[][] temp = new Block[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = new Block(preMap[i][j].val, false);
			}
		}

		return temp;
	}
}
