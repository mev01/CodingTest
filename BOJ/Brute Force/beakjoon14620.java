import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon14620 {
	static int N, ans = Integer.MAX_VALUE;
	static int[] dr = {0, -1, 0, 1, 0, -1, -1, 1, 1, -2, 0, 0, 2}, dc = {0, 0, 1, 0, -1, -1, 1, 1, -1, 0, -2, 2, 0};
	static int[][] map, plantedSeed;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		plantedSeed = new int[N][N];
		Search(0, 0);
		
		System.out.println(ans);
	}

	private static void Search(int cnt, int price) {
		if(cnt == 3) {
			ans = Math.min(ans, price);
			return;
		}
		
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if(plantedSeed[i][j] == 0) {
					int nPrice = price;
					
					for(int dir = 0; dir < 5; dir++) {
						int nr = i + dr[dir], nc = j + dc[dir];
						
						nPrice += map[nr][nc];
					}
					
					if(nPrice >= ans) continue;
					
					Plant(i, j, cnt);
					Search(cnt + 1, nPrice);
					Dig(i, j, cnt);
				}
			}
		}
	}

	private static void Plant(int i, int j, int idx) {
		for(int dir = 0; dir < 13; dir++) {
			int nr = i + dr[dir], nc = j + dc[dir];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			plantedSeed[nr][nc] = plantedSeed[nr][nc] | (1 << idx);
		}
	}

	private static void Dig(int i, int j, int idx) {
		for(int dir = 0; dir < 13; dir++) {
			int nr = i + dr[dir], nc = j + dc[dir];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			plantedSeed[nr][nc] = plantedSeed[nr][nc] & ~(1 << idx);
		}
	}

}
