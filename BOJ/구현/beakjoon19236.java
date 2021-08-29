import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon19236 {
	static int ans = 0;
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1}, dc = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	
	static class Fish {
		int dir, r, c;

		public Fish(int dir, int r, int c) {
			super();
			this.dir = dir;
			this.r = r;
			this.c = c;
		}
	}
	
	static class Shark{
		int dir, score, r, c;

		public Shark(int dir, int score, int r, int c) {
			super();
			this.dir = dir;
			this.score = score;
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] board = new int[4][4];
		Fish[] fishArr = new Fish[17];
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				fishArr[num] = new Fish(dir, i, j);
				board[i][j] = num;
			}
		}
		
		Shark shark = new Shark(fishArr[board[0][0]].dir, board[0][0], 0, 0);
		fishArr[board[0][0]] = null;
		board[0][0] = -1;
		Play(board, fishArr, shark);
		
		System.out.print(ans);
	}

	private static void Play(int[][] preBoard, Fish[] preFishArr, Shark preShark) {
		moveFish(preBoard, preFishArr);
		moveShark(preBoard, preFishArr, preShark);
	}

	private static void moveFish(int[][] board, Fish[] fishArr) {
		for (int num = 1; num <= 16; num++) {
			Fish fish = fishArr[num];
			if(fish == null) continue;
			
			for (int dis = 0; dis < 8; dis++) {
				int dir = fish.dir + dis;
				if(dir > 8) dir = (dir + 1) % 9;
				
				int nr = fish.r + dr[dir];
				int nc = fish.c + dc[dir];
				
				if(isOutOfMap(nr, nc)) continue;
				if(board[nr][nc] == -1) continue;
				
				int nNum = board[nr][nc];
				
				if(fishArr[nNum] != null) {
					int tempR = fish.r;
					int tempC = fish.c;
					
					fishArr[nNum].r = tempR;
					fishArr[nNum].c = tempC;
					board[tempR][tempC] = nNum;
				}
				else {
					board[fish.r][fish.c] = 0;
				}
				
				fish.r = nr;
				fish.c = nc;
				fish.dir = dir;
				board[nr][nc] = num;
				
				break;
			}
		}
	}

	private static boolean isOutOfMap(int nr, int nc) {
		if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) return true;
		return false;
	}

	private static void moveShark(int[][] preBoard, Fish[] preFishArr, Shark preShark) {
		int nr = preShark.r + dr[preShark.dir];
		int nc = preShark.c + dc[preShark.dir];
		
		while(!isOutOfMap(nr, nc)) {
			if(preBoard[nr][nc] > 0) {
				int[][] board = new int[4][4];
				Fish[] fishArr = new Fish[17];
				Shark shark = new Shark(preShark.dir, preShark.score, preShark.r, preShark.c);
				
				for (int i = 0; i < 4; i++) {
					board[i] = preBoard[i].clone();
				}
				for (int i = 1; i < 17; i++) {
					if(preFishArr[i] == null) fishArr[i] = null;
					else fishArr[i] = new Fish(preFishArr[i].dir, preFishArr[i].r, preFishArr[i].c);
				}
				
				shark.score += board[nr][nc];
				
				board[shark.r][shark.c] = 0;
				shark.r = nr;
				shark.c = nc;
				shark.dir = fishArr[board[nr][nc]].dir;
				
				fishArr[board[nr][nc]] = null;
				board[nr][nc] = -1;
				
				Play(board, fishArr, shark);
			}
			
			nr += dr[preShark.dir];
			nc += dc[preShark.dir];
		}
		
		ans = Math.max(ans, preShark.score);
	}
}
