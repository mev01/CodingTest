import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2239 {
	static int[][] sudoku;
	static boolean[][] Row, Col, Grid;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		sudoku = new int[9][9];
		Row = new boolean[9][10];
		Col = new boolean[9][10];
		Grid = new boolean[9][10];
		
		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = str.charAt(j) - '0';
				
				Row[i][sudoku[i][j]] = true;
				Col[j][sudoku[i][j]] = true;
				Grid[(i / 3) * 3 + (j / 3)][sudoku[i][j]] = true;
			}
		}
		
		// 한 칸씩 검사
		playGame(0, 0);
	}

	private static void playGame(int r, int c) {
		if(r == 9) {
			print();
			return;
		}
		
		// 해당 칸에 입력된 수가 있는 경우
		if(sudoku[r][c] != 0) {
			if(c == 8) playGame(r + 1, 0);
			else playGame(r, c + 1);
			
			return;
		}
		
		// 1부터 9까지 들어갈 수 있는 수 확인
		// 현재 가능한 숫자이면 sudoku에 적고 재귀함수
		for (int num = 1; num <= 9; num++) {
			if(!Row[r][num] && !Col[c][num] && !Grid[(r / 3) * 3 + (c / 3)][num]) {
				Row[r][num] = true;
				Col[c][num] = true;
				Grid[(r / 3) * 3 + (c / 3)][num] = true;
				sudoku[r][c] = num;
				
				if(c == 8) playGame(r + 1, 0);
				else playGame(r, c + 1);
				
				Row[r][num] = false;
				Col[c][num] = false;
				Grid[(r / 3) * 3 + (c / 3)][num] = false;
				sudoku[r][c] = 0;
			}
		}
		
	}

	private static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
		System.exit(0);
	}
}
