import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon7576 {
	static Queue<Integer> queX = new LinkedList<Integer>(), queY = new LinkedList<>();
	static int maxcnt;
	static int[] dirX = {-1, 0, 1, 0}, dirY = {0, 1, 0, -1};
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		maxcnt = M*N;
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					queX.offer(i);
					queY.offer(j);
					maxcnt--;
				}
				else if(map[i][j] == -1)
					maxcnt--;
			}
		}
		
		//queX가 빌 때까지 토마트 익히기
		int ans = 0;
		while(!queX.isEmpty()) {
			if(maxcnt == 0) break;
			int size = queX.size();
			ans++;
			
			while(size-- > 0) {
				int x = queX.poll();
				int y = queY.poll();
				
				for (int i = 0; i < 4; i++) {
					if(x + dirX[i] >= 0 && x + dirX[i] < N && y + dirY[i] >= 0 && y + dirY[i] < M && map[x + dirX[i]][y + dirY[i]] == 0) {
						map[x + dirX[i]][y + dirY[i]] = 1;
						queX.offer(x + dirX[i]);
						queY.offer(y + dirY[i]);
						maxcnt--;
					}
				}
			}
		}
		
		//maxcnt가 0이 아니라면 모든 토마토를 익힐 수 없으므로 -1
		System.out.println((maxcnt == 0) ? ans : -1);
	}
}