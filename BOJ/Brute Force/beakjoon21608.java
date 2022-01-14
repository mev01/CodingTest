import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon21608 {
	static int N, ans = 0;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[] order;
	static int[][] likeArr, place;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		order = new int[N * N];
		likeArr = new int[N * N + 1][5];
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			
			order[i] = Integer.parseInt(st.nextToken());
			likeArr[order[i]][1] = Integer.parseInt(st.nextToken());
			likeArr[order[i]][2] = Integer.parseInt(st.nextToken());
			likeArr[order[i]][3] = Integer.parseInt(st.nextToken());
			likeArr[order[i]][4] = Integer.parseInt(st.nextToken());
		}
		
		place = new int[N][N];
		for (int num = 0; num < N * N; num++) {
			int r = -1, c = -1, totalLikeNum = 0, totalEmpty = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(place[i][j] != 0) continue;
					
					int likeNum = 0, empty = 0;
					
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if(place[nr][nc] == 0) empty++;
						else {
							for (int k = 1; k < 5; k++) {
								if(place[nr][nc] == likeArr[order[num]][k]) likeNum++;
							}
						}
					}
					
					if(totalLikeNum < likeNum || (totalLikeNum == likeNum && totalEmpty < empty)) {
						r = i;
						c = j;
						totalLikeNum = likeNum;
						totalEmpty = empty;
					}
					if(r == -1 && c == -1) {
						r = i;
						c = j;
					}
				}
			}
			
			place[r][c] = order[num];
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int likeNum = 0;
				
				for (int dir = 0; dir < 4; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					for (int k = 1; k < 5; k++) {
						if(place[nr][nc] == likeArr[place[i][j]][k]) likeNum++;
					}
				}
				

				int score = (int)Math.pow(10, likeNum - 1);
				ans += score;
			}
		}
		
		System.out.println(ans);
	}

}
