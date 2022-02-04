import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon18808 {
	static int N, M, K, R, C;
	static int[][] board, sticker;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		loop:
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			sticker = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		
			for(int rot = 0; rot < 4; rot++) {
				for (int i = 0; i < N - R + 1; i++) {
					for (int j = 0; j < M - C + 1; j++) {
						if(canStick(i, j)) {
							Stick(i, j);
							continue loop;
						}
					}
				}
				
				sticker = Rotate();
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] == 1) {
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}

	private static boolean canStick(int r, int c) {
		for (int i = r; i < r + R; i++) {
			for (int j = c; j < c + C; j++) {
				if(board[i][j] == 1 && sticker[i - r][j - c] == 1) return false;
			}
		}
		
		return true;
	}

	private static void Stick(int r, int c) {
		for (int i = r; i < r + R; i++) {
			for (int j = c; j < c + C; j++) {
				if(sticker[i - r][j - c] == 1) board[i][j] = 1;
			}
		}
	}

	private static int[][] Rotate() {
		int[][] nSticker = new int[C][R];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				nSticker[j][R - 1 - i] = sticker[i][j];
			}
		}
		
		int temp = R;
		R = C;
		C = temp;
		
		return nSticker;
	}

}
