import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17143 {
	static int R, C, M, kingPos = 1, ans = 0;
	static int[] dr = {0, -1, 1, 0, 0}, dc = {0, 0, 0, 1, -1};
	static int[][] beforeMap, afterMap;
	static Shark[] sharkArr;
	
	static class Shark{
		int r, c, speed, dir, size;
		boolean isDead;

		public Shark(int r, int c, int speed, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sharkArr = new Shark[M + 1];
		beforeMap = new int[R + 1][C + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			sharkArr[i] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			beforeMap[sharkArr[i].r][sharkArr[i].c] = i;
		}
		
		while(kingPos <= C) {
			// 같은 열의 가장 가까운 상어를 낚음
			fishShark();
			// 각각의 상어들이 이동
			moveShark();
			// 왕이 오른쪽으로 이동
			kingPos++;
		}
		
		System.out.print(ans);
	}

	private static void fishShark() {
		for (int i = 1; i <= R; i++) {
			int temp = beforeMap[i][kingPos];
			if(temp != 0) {
				ans += sharkArr[temp].size;
				sharkArr[temp].isDead = true;
				beforeMap[i][kingPos] = 0;
				
				break;
			}
		}
	}

	private static void moveShark() {
		// 경계를 넘는다면 방향을 반대로 바꿔서 이동
		afterMap = new int[R + 1][C + 1];
		
		for (int i = 1; i <= M; i++) {
			if(!sharkArr[i].isDead) {
				markShark(i);
			}
		}
		
		beforeMap = afterMap;
	}

	private static void markShark(int i) {
		int r = sharkArr[i].r;
		int c = sharkArr[i].c;
		
		int nr = r + sharkArr[i].speed * dr[sharkArr[i].dir];
		int nc = c + sharkArr[i].speed * dc[sharkArr[i].dir];
		
		if(nr != r) {
			while(nr <= 0 || nr > R) {
				if(nr <= 0) {
					nr = 2 - nr;
					sharkArr[i].dir = 2;
				}
				else if(nr > R) {
					nr = 2*R - nr;
					sharkArr[i].dir = 1;
				}
			}
		}
		if(nc != c) {
			while(nc <= 0 || nc > C) {
				if(nc <= 0) {
					nc = 2 - nc;
					sharkArr[i].dir = 3;
				}
				else if(nc > C) {
					nc = 2*C - nc;
					sharkArr[i].dir = 4;
				}
			}
		}
		
		sharkArr[i].r = nr;
		sharkArr[i].c = nc;
		// 상어가 모두 이동한 이후에 각 칸에서 제일 큰 상어만 남는다
		if(afterMap[nr][nc] != 0) {
			eatAnotherShark(nr, nc, i);
		}
		else {
			afterMap[nr][nc] = i;
		}
	}

	private static void eatAnotherShark(int r, int c, int i) {
		if(sharkArr[afterMap[r][c]].size > sharkArr[i].size) {
			sharkArr[i].isDead = true;
		}
		else {
			sharkArr[afterMap[r][c]].isDead = true;
			afterMap[r][c] = i;
		}
	}
}
