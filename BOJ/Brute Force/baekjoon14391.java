import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14391 {
	static int N, M, ans = 0;
	static int[] dirx = {0, 1}, diry = {1, 0};
	static int[][] map;
	static boolean[][] checked;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		checked = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		//한 칸씩 탐색
		findUnchecked(0, 0, 0);
		
		System.out.println(ans);
	}

	private static void findUnchecked(int x, int y, int sum) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!checked[i][j]) {
					//해당 칸에서 밑, 오른쪽으로 나가면서 숫자 더하기(해당 방향으로 할 수 있는 모든 숫자하기)
					slice(i, j, sum);
					return;
				}
			}
		}
		
		//다 오면 ans와 비교해서 저장
		ans = Math.max(ans, sum);
	}

	private static void slice(int x, int y, int sum) {
		//밑, 오른쪽으로 자르는게 가능한지 
		//오른쪽
		int temp = 0, idx = M - y;
		for (int i = 0; i < M - y; i++) {
			if(!checked[x][y + i]) {
				checked[x][y + i] = true;
				temp = temp * 10 + map[x][y + i];
				findUnchecked(x, y, sum + temp);
			}
			else {
				idx = i;
				break;
			}
		}
		for (int i = 0; i < idx; i++) {
			checked[x][y + i] = false;
		}
		
		//밑
		temp = 0;
		idx = N - x;
		for (int i = 0; i < N - x; i++) {
			if(!checked[x + i][y]) {
				checked[x + i][y] = true;
				temp = temp * 10 + map[x + i][y];
				findUnchecked(x, y, sum + temp);
			}
			else {
				idx = i;
				break;
			}
		}
		for (int i = 0; i < idx; i++) {
			checked[x + i][y] = false;
		}
	}
}
