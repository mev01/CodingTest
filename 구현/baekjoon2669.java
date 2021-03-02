import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2669 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map = new int[101][101];
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			int leftx = Integer.parseInt(st.nextToken());
			int lefty = Integer.parseInt(st.nextToken());
			int rightx = Integer.parseInt(st.nextToken());
			int righty = Integer.parseInt(st.nextToken());
			
			for (int y = lefty+1; y <= righty; y++) {
				for (int x = leftx+1; x <= rightx; x++) {
					if(map[y][x] == 0) {
						map[y][x] = 1;
						ans++;
					}
				}
			}
		}
		
		System.out.print(ans);
	}
}
