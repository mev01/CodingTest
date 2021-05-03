import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17070_bruteforce {
	static int N, ans = 0;
	static int[] leftloc = {0,0}, rightloc = {0,1};
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		makeMap();
		movePipe(0,0,0,1);
		System.out.println(ans);
	}

	private static void makeMap() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
	}
	

	private static void movePipe(int leftX, int leftY, int rightX, int rightY) {
		if(rightX == N-1 && rightY == N-1) {
			ans++;
			return;
		}
		
		if(isMovableDiag(leftX, leftY, rightX, rightY)) {
			movePipe(rightX, rightY, rightX+1, rightY+1);
		}
		if(isMovableRight(leftX, leftY, rightX, rightY)) {
			movePipe(rightX, rightY, rightX, rightY+1);
		}
		if(isMovableDown(leftX, leftY, rightX, rightY)) {
			movePipe(rightX, rightY, rightX+1, rightY);
		}
	}

	private static boolean isMovableDown(int leftX, int leftY, int rightX, int rightY) {
		if(leftX != rightX && rightX+1 < N) {
			if(map[rightX+1][rightY]!=1) {
				return true;
			}
		}
		return false;
	}

	private static boolean isMovableRight(int leftX, int leftY, int rightX, int rightY) {
		if(leftY != rightY && rightY+1 < N) {
			if(map[rightX][rightY+1]!=1) {
				return true;
			}
		}
		return false;
	}

	private static boolean isMovableDiag(int leftX, int leftY, int rightX, int rightY) {
		if(rightX < N && rightY < N) {
			if(map[rightX+1][rightY+1]!=1 && map[rightX][rightY+1]!=1 && map[rightX+1][rightY]!=1) {
				return true;
			}
		}
		return false;
	}
}
