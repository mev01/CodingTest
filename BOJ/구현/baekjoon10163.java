import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10163 {
	static int[][] map = new int[101][101];
	static int[] cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		cnt = new int[T+1];
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int wid = Integer.parseInt(st.nextToken());
			int hei = Integer.parseInt(st.nextToken());
			
			for (int i = x; i < x + hei; i++) {
				for (int j = y; j < y + wid; j++) {
					map[i][j] = tc;
				}
			}
		}
		
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				cnt[map[i][j]]++;
			}
		}
		
		for (int i = 1; i <= T; i++) {
			sb.append(cnt[i]).append('\n');
		}
		System.out.print(sb.toString());
	}
}
