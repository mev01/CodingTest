import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon19237 {
	static int N, M, K, livingSharkNum;
	static int[] dr = {0, -1, 1, 0, 0}, dc = {0, 0, 0, -1, 1};
	static int[][] map;
	static Shark[] sharkArr;
	static Smell[][] smellMap;
	
	static class Shark{
		int r, c, dir;
		boolean isOut = false;
		int dirArr[][] = new int[5][4];
		
		public Shark(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	static class Smell{
		int sharkNum, time;

		public Smell(int sharkNum, int time) {
			super();
			this.sharkNum = sharkNum;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		livingSharkNum = M;
		map = new int[N][N];
		sharkArr = new Shark[M + 1];
		smellMap = new Smell[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num != 0) {
					sharkArr[num] = new Shark(i, j, 0);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharkArr[i].dir = Integer.parseInt(st.nextToken());
		}
		
		for (int num = 1; num <= M; num++) {
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					sharkArr[num].dirArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int time = 0;
		leaveSmell();
		while(livingSharkNum > 1) {
			moveShark();
			declineSmell();
			leaveSmell();
			map = new int[N][N];
			
			++time;
			if(time == 1001) break;
		}
		
		System.out.println(time == 1001 ? -1 : time);
	}

	private static void moveShark() {
		loop:
		for (int num = 1; num <= M; num++) {
			Shark shark = sharkArr[num];
			int[] dirArr = shark.dirArr[shark.dir];
			
			if(shark.isOut) continue loop;
			
			for(int dir = 0; dir <= 3; dir++) {
				int nr = shark.r + dr[dirArr[dir]];
				int nc = shark.c + dc[dirArr[dir]];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(smellMap[nr][nc] == null) {
					shark.r = nr;
					shark.c = nc;
					shark.dir = dirArr[dir];
					
					if(map[nr][nc] >= 1) {
						shark.isOut = true;
						livingSharkNum--;
					}
					else map[nr][nc] = num;
					
					continue loop;
				}
			}
			
			for(int dir = 0; dir <= 3; dir++) {
				int nr = shark.r + dr[dirArr[dir]];
				int nc = shark.c + dc[dirArr[dir]];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(smellMap[nr][nc].sharkNum == num) {
					shark.r = nr;
					shark.c = nc;
					shark.dir = dirArr[dir];
					
					if(map[nr][nc] >= 1) {
						shark.isOut = true;
						livingSharkNum--;
					}
					else map[nr][nc] = num;
					
					continue loop;
				}
			}
		}
	}

	private static void declineSmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(smellMap[i][j] != null) {
					smellMap[i][j].time -= 1;
					if(smellMap[i][j].time == 0) smellMap[i][j] = null;
				}
			}
		}
	}

	private static void leaveSmell() {
		for (int i = 1; i <= M; i++) {
			if(!sharkArr[i].isOut)
				smellMap[sharkArr[i].r][sharkArr[i].c] = new Smell(i, K);
		}
	}
}
