import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2563 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[][] map = new int[100][100];
		int answer = 0;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int i = x-1; i < x+9; i++) {
				for (int j = y-1; j < y+9; j++) {
					if(map[i][j] == 0) answer++;
					map[i][j] = 1;
				}
			}
			
		}
		sb.append(answer);
		System.out.println(sb);
	}
}