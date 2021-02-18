import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon4963 {
	static int[] disX = {0,-1,-1,-1,0,1,1,1}, disY = {-1,-1,0,1,1,1,0,-1};
	static int[][] map = new int[50][50];
	static int ans = 0, w, h;

	static void DFS(int x, int y) {
		map[x][y] = 0;
		for (int k = 0; k <= 7; k++) {
			if(x+disX[k]>=0 && x+disX[k]<h && y+disY[k]>=0 && y+disY[k]<w) {
				if(map[x+disX[k]][y+disY[k]] == 1)
					DFS(x+disX[k], y+disY[k]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		while( (w = Integer.parseInt(st.nextToken()))!=0 && (h = Integer.parseInt(st.nextToken()))!=0) {
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(map[i][j] == 1) {
						ans++;
						DFS(i, j);
					}
				}
			}
			
			sb.append(ans+"\n");
			st = new StringTokenizer(br.readLine());
		}
		System.out.print(sb.toString());
	}
}