import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon4574 {
	static boolean[][] Row, Col, Grid, Domino;
	static int[][] sudoku;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int Cnt = 1;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			Row = new boolean[10][10];
			Col = new boolean[10][10];
			Grid = new boolean[10][10];
			Domino = new boolean[10][10];
			
			sudoku = new int[10][10];
			
			// 도미노 넣기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				// 도미노 넣기
				int U = Integer.parseInt(st.nextToken());
				String LU = st.nextToken();
				int V = Integer.parseInt(st.nextToken());
				String LV = st.nextToken();
				
				putNumber(U, LU);
				putNumber(V, LV);
				
				if(U < V)
					Domino[U][V] = true;
				else
					Domino[V][U] = true;
			}
			
			// 숫자 넣기
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 9; i++) {
				putNumber(i, st.nextToken());
			}
			
			// 숫자 넣기
			if(Game(1, 1)) {
				sb.append("Puzzle ").append(Cnt++).append('\n');
				
				// 퍼즐 출력
				for (int i = 1; i <= 9; i++) {
					for (int j = 1; j <= 9; j++) {
						sb.append(sudoku[i][j]);
					}
					sb.append('\n');
				}
			}
		}
		
		System.out.print(sb.toString());
	}

	private static void putNumber(int num, String pos) {
		int r = pos.charAt(0) - 64;
		int c = pos.charAt(1) - '0';
		
		sudoku[r][c] = num;
		Row[r][num] = true;
		Col[c][num] = true;
		Grid[(((r - 1) / 3) * 3 + (c - 1) / 3) + 1][num] = true;
	}
	
	private static void removeNumber(int num, String pos) {
		int r = pos.charAt(0) - 64;
		int c = pos.charAt(1) - '0';
		
		sudoku[r][c] = 0;
		Row[r][num] = false;
		Col[c][num] = false;
		Grid[(((r - 1) / 3) * 3 + (c - 1) / 3) + 1][num] = false;
	}
	
	private static boolean Game(int r, int c) {
		if(c == 10) {
			if(Game(r + 1, 1)) return true;
			else return false;
		}
		if(r == 10) {
			// 종료
			return true;
		}
		if(sudoku[r][c] != 0) {
			if(Game(r, c + 1)) return true;
			else return false;
		}
		
		for (int i = 1; i <= 9; i++) {
			// i가 해당 칸에 들어 갈 수 있는 경우
			if(numberPossible(r, c, i)) {
				putNumber(i, parseToPosition(r, c));
				
				// (r, c) 상하좌우로 들어갈 수 있는 숫자 탐색
				for (int dir = 0; dir < 4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					
					if(nr < 1 || nr > 9 || nc < 1 || nc > 9 || sudoku[nr][nc] != 0) continue;
					
					for (int j = 1; j <= 9; j++) {
						if(numberPossible(nr, nc, j)) {
							if(i < j) {
								if(!Domino[i][j]) {
									Domino[i][j] = true;
									putNumber(j, parseToPosition(nr, nc));
									
									if(Game(r, c + 1)) return true;
									
									Domino[i][j] = false;
									removeNumber(j, parseToPosition(nr, nc));
								}
							}
							else{
								if(!Domino[j][i]) {
									Domino[j][i] = true;
									putNumber(j, parseToPosition(nr, nc));
									
									if(Game(r, c + 1)) return true;
									
									Domino[j][i] = false;
									removeNumber(j, parseToPosition(nr, nc));
								}
							}
						}
					}
				}
				
				removeNumber(i, parseToPosition(r, c));
			}
		}
		
		return false;
	}

	private static String parseToPosition(int r, int c) {
		char a = (char)((char)r + 64);
		String temp = String.valueOf(a) + String.valueOf(c);
		return temp;
	}

	private static boolean numberPossible(int r, int c, int num) {
		if(!Row[r][num] && !Col[c][num] && !Grid[(((r - 1) / 3) * 3 + (c - 1) / 3) + 1][num]) return true;
		return false;
	}
}
