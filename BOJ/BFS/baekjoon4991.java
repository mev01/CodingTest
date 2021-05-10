import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon4991 {
	static int W, H, numDirty, ans;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static boolean[][][] visit;
	static char[][] map;
	static Queue<Robot> que;
	
	static int[][] check;
	
	static class Robot{
		int r, c, numWash, cnt;

		public Robot(int r, int c, int numWash, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.numWash = numWash;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true){
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if(W == 0) break;
			
			ans = Integer.MAX_VALUE;
			numDirty = 0;
			map = new char[H][W];
			que = new LinkedList<Robot>();
			
			check = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					if(map[i][j] == 'o'){
						// 청소기 시작 위치 que에 넣기
						que.offer(new Robot(i, j, 0, 0));
						map[i][j] = '.';
					}
					else if(map[i][j] == '*'){
						// 더러운칸 map에 구분해서 표시
						// 더러운칸 카운트
						map[i][j] = (char) (++numDirty + '0');
					}
				}
			}
			
			visit = new boolean[H][W][1 << numDirty];
			visit[que.peek().r][que.peek().c][0] = true;
			
			loop:
			while(!que.isEmpty()){
				Robot robot = que.poll();
				
				for (int i = 0; i < 4; i++) {
					int nr = robot.r + dr[i];
					int nc = robot.c + dc[i];
					
					if(isOutOfMap(nr, nc)) continue;
					
					if(map[nr][nc] == '.'){
						if(!visit[nr][nc][robot.numWash]){
							// 해당칸이 같은 방을 청소하고 온적이 없다면 que에 넣기
							que.offer(new Robot(nr, nc, robot.numWash, robot.cnt + 1));
							visit[nr][nc][robot.numWash] = true;
							
							check[nr][nc] = robot.numWash;
						}
					}
					else if(map[nr][nc] >= '1' && map[nr][nc] <= '9'){
						if(!visit[nr][nc][robot.numWash]){
							if((robot.numWash | (1 << (map[nr][nc] - '1'))) == (int)Math.pow(2, numDirty) - 1){
								ans = Math.min(ans, robot.cnt + 1);
								break loop;
							}
							// 탐색하다가 더러운칸 만나면 numwash에 +하고
							que.offer(new Robot(nr, nc, robot.numWash | (1 << (map[nr][nc] - '1')), robot.cnt + 1));
							visit[nr][nc][robot.numWash | (1 << (map[nr][nc] - '1'))] = true;
							
							check[nr][nc] = robot.numWash | (1 << (map[nr][nc] - '1'));
						}
					}
				}
				
				
			}
			
			sb.append(ans == Integer.MAX_VALUE ? -1 : ans).append('\n');
		}
		System.out.print(sb.toString());
	}

	private static boolean isOutOfMap(int nr, int nc) {
		if(nr < 0 || nr >= H || nc < 0 || nc >= W) return true;
		return false;
	}
}
