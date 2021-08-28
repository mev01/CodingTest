import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon20061 {
	static int score = 0;
	static boolean[][][] Board;
	
	static class Tile{
		int type;
		int r1, c1, r2, c2;
		
		public Tile(int type, int r1, int c1) {
			this.type = type;
			this.r1 = r1;
			this.c1 = c1;
		}
		
		public Tile(int type, int r1, int c1, int r2, int c2) {
			this.type = type;
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Board = new boolean[2][6][4];
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			switch (t) {
			case 1:
				insertTile(0, new Tile(t, 0, y));
				insertTile(1, new Tile(t, 0, x));
				break;
			case 2:
				insertTile(0, new Tile(2, 0, y, 0, y + 1));
				insertTile(1, new Tile(3, 0, x, 1, x));
				break;
			case 3:
				insertTile(0, new Tile(3, 0, y, 1, y));
				insertTile(1, new Tile(2, 0, x, 0, x + 1));
				break;
			}
		}
		
		int cnt = 0;
		for (int type = 0; type < 2; type++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if(Board[type][i][j]) ++cnt;
				}
			}
		}
		
		sb.append(score).append('\n').append(cnt).append('\n');
		System.out.print(sb.toString());
	}

	private static void insertTile(int boardType, Tile tile) {
		for (int i = 0; i < 6; i++) {
			boolean isChange = false;
			
			if(tile.type == 1 && (i == 5 || Board[boardType][i + 1][tile.c1])) {
				Board[boardType][i][tile.c1] = true;
				isChange = true;
			}
			else if(tile.type == 2 && (i == 5 || Board[boardType][i + 1][tile.c1] || Board[boardType][i + 1][tile.c2])) {
				Board[boardType][i][tile.c1] = true;
				Board[boardType][i][tile.c2] = true;
				isChange = true;
			}
			else if(tile.type == 3 && (i == 4 || Board[boardType][i + 2][tile.c1])) {
				Board[boardType][i][tile.c1] = true;
				Board[boardType][i + 1][tile.c1] = true;
				isChange = true;
			}
			
			if(isChange) {
				deleteFullTile(boardType);
				updateTile(boardType);
				deleteTileIfLightColor(boardType);
				updateTile(boardType);
				break;
			}
		}
	}

	private static void deleteFullTile(int boardType) {
		for (int i = 5; i >= 0; i--) {
			boolean flag = true;
			
			for (int j = 0; j < 4; j++) {
				if(!Board[boardType][i][j]) flag = false;
			}
			
			if(flag) {
				++score;
				for (int j = 0; j < 4; j++) {
					Board[boardType][i][j] = false;
				}
			}
		}
	}
	
	private static void updateTile(int boardType) {
		int r = 5;
		
		for (int i = 5; i >= 0; i--) {
			boolean flag = false;
			
			for (int j = 0; j < 4; j++) {
				if(Board[boardType][i][j]) flag = true;
			}
			
			if(flag && r != i) {
				for (int j = 0; j < 4; j++) {
					if(Board[boardType][i][j]) {
						Board[boardType][i][j] = false;
						Board[boardType][r][j] = true;
					}
				}
				r--;
			}
			else if(flag) r--;
		}
	}
	
	private static void deleteTileIfLightColor(int boardType) {
		int cnt = 0;
		
		for (int i = 0; i < 2; i++) {
			boolean flag = false;
			
			for (int j = 0; j < 4; j++) {
				if(Board[boardType][i][j]) flag = true;
			}
			
			if(flag) cnt++;
		}
		
		for (int i = 5; i > 5 - cnt; i--) {
			for (int j = 0; j < 4; j++) {
				Board[boardType][i][j] = false;
			}
		}
	}
}
