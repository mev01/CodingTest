import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon3109 {
	static int R, C, ans = 0;
	static char[][] map;
	
	static boolean DFS(int x, int y) {
		if(y==C-1) {
			ans++;
			return true;
		}
		map[x][y] = 'x';
		
		if(x-1>=0 && map[x-1][y+1] == '.')
			if(DFS(x-1, y+1))
				return true;
		if(map[x][y+1] == '.')
			if(DFS(x, y+1))
				return true;
		if(x+1<R && map[x+1][y+1] == '.')
			if(DFS(x+1, y+1))
				return true;
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int x = 0; x < R; x++) {
			String line = br.readLine();
			for (int y = 0; y < C; y++) {
				map[x][y] = line.charAt(y);
			}
		}
		
		for (int i = 0; i < R; i++) {
			DFS(i, 0);
		}
		
		System.out.println(ans);
	}

}
