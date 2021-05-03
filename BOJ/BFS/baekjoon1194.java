import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1194 {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] beforeKeyMap;
	static Person init;
	static char[][] map;
	static Queue<Person> que;
	
	static class Person{
		int r, c, key, moveCnt;

		public Person(int r, int c, int key, int moveCnt) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
			this.moveCnt = moveCnt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		beforeKeyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		init = findPerson();
		
		System.out.print(BFS());
	}

	private static Person findPerson() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == '0') {
					return new Person(i, j, 1, 0);
				}
			}
		}
		
		return null;
	}

	private static int BFS() {
		que = new LinkedList<Person>();
		que.offer(init);
		beforeKeyMap[init.r][init.c] = 1;
		
		while(!que.isEmpty()) {
			Person person = que.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = person.r + dr[dir];
				int nc = person.c + dc[dir];
				
				// map 밖이라면
				if(isOutOfMap(nr, nc)) continue;
				// 이동 불가능한 곳이라면
				if(map[nr][nc] == '#') continue;
				// 출구라면
				if(map[nr][nc] == '1') return person.moveCnt + 1;
				
//				// 그전에 같은 키를 가지고 방문한적이 있다면
//				if((person.key & beforeKeyMap[nr][nc]) == 0) continue;
//				// 그전보다 적은 키를 가지고 방문했다면
//				if((~person.key & beforeKeyMap[nr][nc]) == 0) continue;
				
				// 예전과는 다른키가 없다면
				// 110
				// 100
				// 101
				if((~beforeKeyMap[nr][nc] & person.key) == 0) continue;
				// 저장
				//beforeKeyMap[nr][nc] = beforeKeyMap[nr][nc] | person.key;
				beforeKeyMap[nr][nc] =  person.key;
				
				// 열쇠가 있는 구역이라면
				if(map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
					beforeKeyMap[nr][nc] = person.key | 1 << (map[nr][nc] - 96);
					que.offer(new Person(nr, nc, person.key | 1 << (map[nr][nc] - 96), person.moveCnt + 1));
				}
				// 문이 있는 구역이라면
				else if(map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
					if((person.key & 1 << (map[nr][nc] - 96)) != 0) {
						que.offer(new Person(nr, nc, person.key, person.moveCnt + 1));
					}
				}
				// 그냥 통과할 수 있는 구역
				else {
					que.offer(new Person(nr, nc, person.key, person.moveCnt + 1));
				}
			}
		}
		
		return -1;
	}

	private static boolean isOutOfMap(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return false;
		return true;
	}
}
