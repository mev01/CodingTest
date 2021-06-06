import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon16933 {
	static int N, M, K, ans = -1;
	static int[] dr = {-1, 0, 1, 0, 0}, dc = {0, 1, 0, -1, 0};
	static char[][] map;
	static Room[][] markMap;
	static Queue<User> que;

	static class Room {
		int crash, dis;

		public Room(int crash, int dis) {
			super();
			this.crash = crash;
			this.dis = dis;
		}
	}

	static class User {
		int r, c, crash, dis;
		boolean isDay, isNight;

		public User(int r, int c, int crash, int dis, boolean isDay, boolean isNight) {
			super();
			this.r = r;
			this.c = c;
			this.crash = crash;
			this.dis = dis;
			this.isDay = isDay;
			this.isNight = isNight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		markMap = new Room[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		que = new LinkedList<User>();
		que.offer(new User(0, 0, 0, 1, true, false));
		markMap[0][0] = new Room(0, 1);
		
		while(!que.isEmpty()) {
			User user = que.poll();
			
			if(user.r == N - 1 && user.c == M - 1) {
				ans = user.dis;
				break;
			}
			
			for (int dir = 0; dir < 5; dir++) {
				int nr = user.r + dr[dir];
				int nc = user.c + dc[dir];
				
				if(isOutOfMap(nr, nc)) continue;
				if(dir == 4) {
					if(user.isNight) que.offer(new User(user.r, user.c, user.crash, user.dis + 1, !user.isDay, !user.isNight));
					continue;
				}
				
				if(user.isDay) {
					// 벽 이동
					if(map[nr][nc] == '1') {
						if(user.crash < K) {
							if(markMap[nr][nc] == null || markMap[nr][nc].crash > user.crash + 1 || markMap[nr][nc].dis > user.dis + 1) {
								markMap[nr][nc] = new Room(user.crash + 1, user.dis + 1);
								que.offer(new User(nr, nc, user.crash + 1, user.dis + 1, !user.isDay, !user.isNight));
							}
						}
					}
				}
				if(map[nr][nc] == '0') {
					if(markMap[nr][nc] == null || markMap[nr][nc].crash > user.crash || markMap[nr][nc].dis > user.dis + 1) {
						markMap[nr][nc] = new Room(user.crash, user.dis + 1);
						que.offer(new User(nr, nc, user.crash, user.dis + 1, !user.isDay, !user.isNight));
					}
				}
			}
		}
		
		System.out.print(ans);
	}

	private static boolean isOutOfMap(int nr, int nc) {
		if(nr < 0 || nr >= N || nc < 0 || nc >= M) return true;
		return false;
	}
}
