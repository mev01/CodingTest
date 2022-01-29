import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2615 {
	static int[] dr = {-1, 0, 1, 1}, dc = {1, 1, 1, 0};
	static int[][] board;
	static boolean[][][] needToCheck;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		board = new int[19][19];
		needToCheck = new boolean[4][19][19];
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 19; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		loop:
		for (int j = 0; j < 19; j++) {
			for (int i = 0; i < 19; i++) {
				if(board[i][j] != 0) {
					if(checkFourDir(i, j, board[i][j])) {
						sb.append(board[i][j]).append('\n');
						sb.append(i + 1).append(' ').append(j + 1);
						break loop;
					}
				}
			}
		}
		
		if(sb.length() == 0) sb.append(0);
		System.out.println(sb.toString());
	}

	private static boolean checkFourDir(int i, int j, int k) {
		boolean result = false;
		
		if(!needToCheck[0][i][j]) result = result | CheckAns(i, j, 0, 0, board[i][j]);
		if(!needToCheck[1][i][j]) result = result | CheckAns(i, j, 1, 0, board[i][j]);
		if(!needToCheck[2][i][j]) result = result | CheckAns(i, j, 2, 0, board[i][j]);
		if(!needToCheck[3][i][j]) result = result | CheckAns(i, j, 3, 0, board[i][j]);
		
		return result;
	}

	private static boolean CheckAns(int r, int c, int dir, int cnt, int stone) {
		if((r >= 0 && r < 19 && c < 19) && stone == board[r][c]) {
			needToCheck[dir][r][c] = true;
			return CheckAns(r + dr[dir], c + dc[dir], dir, cnt + 1, stone);
		}
		else {
			if(cnt == 5) return true;
		}
		
		return false;
	}

}
