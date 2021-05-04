import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dis{
	int x, y;
	public Dis(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class EA1953 {
	static int N, M, ans;
	static int[] disx = {-1, 0, 1, 0}, disy = {0, 1, 0, -1};
	static int[][] map;
	static Queue<Dis> que;
	static boolean[][] visited;
	static boolean[][] pipe = {
			{false, false, false, false},
			{true, true, true, true},
			{true, false, true, false},
			{false, true, false, true},
			{true, true, false, false},
			{false, true, true, false},
			{false, false, true, true},
			{true, false, false, true},
	};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			que = new LinkedList<Dis>();
			ans = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited[R][C] = true;
			que.offer(new Dis(R, C));
			while(L-- > 0) {
				int num = que.size();
				while(num-- > 0) {
					Dis dis = que.poll();
					ans++;
					
					for (int dir = 0; dir < 4; dir++) {
						if(pipe[map[dis.x][dis.y]][dir]) {	//파이프가 해당 방향으로 연결되어 있을 때
							if(dis.x + disx[dir] >= 0 && dis.x + disx[dir] < N && dis.y + disy[dir] >= 0 && dis.y + disy[dir] < M && !visited[dis.x + disx[dir]][dis.y + disy[dir]]) {	//방향 검사
								if(pipe[map[dis.x + disx[dir]][dis.y + disy[dir]]][(dir + 2) % 4]) {	//해당 방향의 위치와 파이프가 연결되어 있는지
									que.offer(new Dis(dis.x + disx[dir], dis.y + disy[dir]));
									visited[dis.x + disx[dir]][dis.y + disy[dir]] = true;
								}
							}
						}
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		
		System.out.print(sb.toString());
	}
}
