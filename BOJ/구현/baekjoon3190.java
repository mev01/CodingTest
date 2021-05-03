import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon3190 {
	static int N, sec, dir;
	static int[] dirSecInfo, dirInfo;
	static int[] dirx = {-1, 0, 1, 0}, diry = {0, 1, 0, -1};
	static int[][] map;
	static Deque<Pos> que;
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N + 1][N + 1];
		int K = Integer.parseInt(br.readLine());
		//사과 기록
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -1;
		}
		int L = Integer.parseInt(br.readLine());
		dirSecInfo = new int[L+1];
		dirInfo = new int[L+1];
		//방향 변환 정보
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			dirSecInfo[i] = Integer.parseInt(st.nextToken());
			
			if(st.nextToken().equals("L")) dirInfo[i] = 3;
			else dirInfo[i] = 1;
		}
		
		sec = 1;
		dir = 1;
		int dirIdx = 0;
		que = new LinkedList<Pos>();
		que.offerLast(new Pos(1, 1));
		int x = 1;
		int y = 1;
		map[x][y] = 1;
		// 뱀 진행
		while(true) {
			// 다음 위치 계산
			x += dirx[dir];
			y += diry[dir];
			// 뱀 머리가 밖으로 나가거나 자기 자신과 부딪히면 게임 종료
			if(x < 1 || x > N || y < 1 || y > N || map[x][y] == 1) {
				break;
			}
			// 사과를 먹지 않으면 que를 줄이고 map을 0으로
			if(map[x][y] != -1) {
				Pos pos = que.pollFirst();
				map[pos.x][pos.y] = 0;
			}
			// 다음 위치를 큐에 넣고 맵에 체크
			que.offerLast(new Pos(x, y));
			map[x][y] = 1;

			//방향 전환
			if(sec == dirSecInfo[dirIdx]) {
				dir = (dir + dirInfo[dirIdx++]) % 4;
			}
			sec++;
		}
		
		System.out.print(sec);
	}
}
