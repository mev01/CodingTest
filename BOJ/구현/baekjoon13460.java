import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class bead{
	int x, y, cnt;
	
	public bead(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}
public class baekjoon13460 {
	static int N, M, holeX, holeY, ans = Integer.MAX_VALUE;
	static int lredx, lredy, lbluex, lbluey;
	static int[] dirx = {-1, 0, 1, 0}, diry = {0, 1, 0, -1};
	static char[][] map;
	static Queue<bead> redBead = new LinkedList<bead>();
	static Queue<bead> blueBead = new LinkedList<bead>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'B') {
					blueBead.offer(new bead(i, j, 0));
				}
				else if(map[i][j] == 'R') {
					redBead.offer(new bead(i, j, 0));
				}
				else if(map[i][j] == 'O') {
					holeX = i;
					holeY = j;
				}
			}
		}
		
		while (!redBead.isEmpty()) {
			bead preblue = blueBead.poll();
			int bluepx = preblue.x;
			int bluepy = preblue.y;
			
			bead prered = redBead.poll();
			int redpx = prered.x;
			int redpy = prered.y;
			int cnt = prered.cnt;
			
			if(ans <= cnt || 10 <= cnt) continue;
			
			for (int dir = 0; dir < 4; dir++) {
				lredx = redpx;
				lredy = redpy;
				lbluex = bluepx;
				lbluey = bluepy;

				map[bluepx][bluepy] = 'B';
				map[redpx][redpy] = 'R';
				map[holeX][holeY] = 'O';
				
				//
				map[lredx][lredy] = '.';
				RedMove(lredx, lredy, dir, cnt+1);
				
				map[lbluex][lbluey] = '.';
				BlueMove(lbluex, lbluey, dir, cnt+1);
				if(Movable(lredx + dirx[dir], lredy + diry[dir]) == 1 && (lredx != holeX || lredy != holeY)) {
					map[lredx][lredy] = '.';
					RedMove(lredx, lredy, dir, cnt+1);
				}
				//
				
				map[lredx][lredy] = '.';
				map[lbluex][lbluey] = '.';
				
				if(lbluex == holeX && lbluey == holeY) {
					
				}
				else if(lredx == holeX && lredy == holeY) {
					ans = Math.min(ans, cnt+1);
				}
				else if(lredx != redpx || lredy != redpy || lbluex != bluepx || lbluey != bluepy){
					blueBead.offer(new bead(lbluex, lbluey, cnt+1));
					redBead.offer(new bead(lredx, lredy, cnt+1));
				}
			}
		}
		
		System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void RedMove(int px, int py, int dir, int cnt) {
		if(Movable(px + dirx[dir], py + diry[dir]) == 1) {
			RedMove(px + dirx[dir], py + diry[dir], dir, cnt);
		}
		else if(Movable(px + dirx[dir], py + diry[dir]) == 0){
			lredx = px + dirx[dir];
			lredy = py + diry[dir];
		}
		else {
			lredx = px;
			lredy = py;
			map[px][py] = 'R';
		}
	}
	private static void BlueMove(int px, int py, int dir, int cnt) {
		if(px == holeX && py == holeY) {
			
		}
		if(Movable(px + dirx[dir], py + diry[dir]) == 1) {
			BlueMove(px + dirx[dir], py + diry[dir], dir, cnt);
		}
		else if(Movable(px + dirx[dir], py + diry[dir]) == 0){
			lbluex = px + dirx[dir];
			lbluey = py + diry[dir];
		}
		else {
			lbluex = px;
			lbluey = py;
			map[px][py] = 'B';
		}
	}
	private static int Movable(int x, int y) {
		if(map[x][y] == '.') {	//바로 움직일 수 있을 때
			return 1;
		}
		else if(map[x][y] == 'O') {	//목적지
			return 0;
		}
		else {	//움직일 수 없을 때
			return -1;
		}
	}
}
