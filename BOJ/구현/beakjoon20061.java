import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon20061 {
	static int score = 0;
	static boolean[][] greenBoard, blueBoard;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		greenBoard = new boolean[6][4];
		blueBoard = new boolean[6][4];
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			switch (t) {
			case 1:
				insertTile(greenBoard, t, y);
				insertTile(blueBoard, t, x);
				break;
			case 2:
				insertTile(greenBoard, 2, y);
				insertTile(blueBoard, 3, x);
				break;
			case 3:
				insertTile(greenBoard, 3, y);
				insertTile(blueBoard, 2, x);
				break;
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if(greenBoard[i][j]) ++cnt;
				if(blueBoard[i][j]) ++cnt;
			}
		}
		
		sb.append(score).append('\n').append(cnt).append('\n');
		System.out.print(sb.toString());
	}

	private static void insertTile(boolean[][] board, int type, int dis) {
		for (int i = 0; i < 6; i++) {
			if(type == 1 && (i == 5 || board[i + 1][dis])) {
				board[i][dis] = true;
				break;
			}
			else if(type == 2 && (i == 5 || board[i + 1][dis] || board[i + 1][dis + 1])) {
				board[i][dis] = true;
				board[i][dis + 1] = true;
				break;
			}
			else if(type == 3 && (i == 4 || board[i + 2][dis])) {
				board[i][dis] = true;
				board[i + 1][dis] = true;
				break;
			}
		}
		
		deleteFullTile(board);
		deleteTileIfLightColor(board);
	}

	private static void deleteFullTile(boolean[][] board) {
		for (int i = 0; i < 6; i++) {
			boolean fullTile = true;
			
			for (int j = 0; j < 4; j++) {
				if(!board[i][j]) fullTile = false;
			}
			
			if(fullTile) {
				++score;
				updateTile(board, i);
			}
		}
	}
	
	private static void deleteTileIfLightColor(boolean[][] board) {
		for (int i = 0; i < 2; i++) {
			boolean isTile = false;
			
			for (int j = 0; j < 4; j++) {
				if(board[i][j]) isTile = true;
			}
			
			if(isTile) updateTile(board, 5);
		}
	}
    
    private static void updateTile(boolean[][] board, int r) {
		for (int i = r - 1; i >= 0; i--) {
			board[i + 1] = board[i];
		}
		board[0] = new boolean[4];
	}
}
