import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon17822 {
	static boolean isDelete;
	static int N, M, T, sum, cnt, total;
	static int[] plateIdx;
	static int[][] plate;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		plateIdx = new int[N + 2];
		plate = new int[N + 1][M];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				plate[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(T-- > 0) {
			sum = 0;
			total = 0;
			isDelete = false;
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			for (int i = x; i <= N; i += x) {
				if(d == 0) plateIdx[i] = (plateIdx[i] + (M - k)) % M;
				else plateIdx[i] = (plateIdx[i] + k) % M;
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					cnt = 0;
					int num = plate[i][j];
					if(plate[i][j] != 0) dfs(i, j, plate[i][j]);
					
					if(cnt <= 1) plate[i][j] = num;
					else isDelete = true;
					
					sum += plate[i][j];
					if(plate[i][j] != 0) ++total;
				}
			}
			
			if(!isDelete) {
				float num = (float)sum / total;
				
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if(plate[i][j] == 0) continue;
						if(plate[i][j] > num) plate[i][j] -= 1;
						else if(plate[i][j] < num) plate[i][j] += 1;
					}
				}
			}
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				ans += plate[i][j];
			}
		}
		
		System.out.print(ans);
	}

	private static void dfs(int r, int c, int num) {
		++cnt;
		plate[r][c] = 0;
		
		int nr, nc;
		
		nr = r;
		nc = (c + 1) % M;
		if(plate[nr][nc] == num) dfs(nr, nc, num);
		
		nr = r;
		nc = (c + (M -1)) % M;
		if(plate[nr][nc] == num) dfs(nr, nc, num);
		
		int dis = 0;
		if(c > plateIdx[r]) dis = c - plateIdx[r];
		else if(c < plateIdx[r]) dis = M - (plateIdx[r] - c);
		
		nr = r - 1;
		
		nc = (plateIdx[nr] + dis)  % M;
		if(nr >= 1 && plate[nr][nc] == num) dfs(nr, nc, num);
		
		nr = r + 1;
		nc = (plateIdx[nr] + dis)  % M;
		if(nr <= N && plate[nr][nc] == num) dfs(nr, nc, num);
	}
}
