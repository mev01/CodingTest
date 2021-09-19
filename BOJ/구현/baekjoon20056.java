import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon20056 {
	static int N, M, K;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}, dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayList<FireBall> fireBallArr = new ArrayList<>();
	static ArrayList<FireBall>[][] Map;
	
	static class FireBall{
		int r, c, m, s, d;

		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			fireBallArr.add(new FireBall(r, c, m, s, d));
		}
		
		while(K-- > 0) {
			moveFireBall();
			markFireBall();
			splitFireBall();
		}
		
		int ans = 0;
		for(FireBall fireBall : fireBallArr) {
			ans += fireBall.m;
		}
		
		System.out.println(ans);
	}

	private static void moveFireBall() {
		for(FireBall fireBall : fireBallArr) {
			int nr = fireBall.r + fireBall.s * dr[fireBall.d];
			int nc = fireBall.c + fireBall.s * dc[fireBall.d];
			
			nr = replace(nr);
			nc = replace(nc);
			
			fireBall.r = nr;
			fireBall.c = nc;
		}
	}

	private static int replace(int num) {
		if(num <= 0) num = num % N + N - 1;
		else num = --num % N;
		
		return ++num;
	}
	
	private static void markFireBall() {
		Map = new ArrayList[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Map[i][j] = new ArrayList<>();
			}
		}
		
		for(FireBall fireBall : fireBallArr) {
			Map[fireBall.r][fireBall.c].add(fireBall);
		}
	}

	private static void splitFireBall() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(Map[i][j].size() > 1) {
					int m = 0, s = 0, mod = Map[i][j].get(0).d % 2;
					boolean same = true;
					
					for(FireBall fireBall : Map[i][j]) {
						m += fireBall.m;
						s += fireBall.s;
						if(same) same = (mod == fireBall.d % 2) ? true : false;
					}
					
					mod = same ? 0 : 1;
					for (int k = 0; k < 4; k++) {
						if(m / 5 != 0) fireBallArr.add(new FireBall(i, j, m / 5, s / Map[i][j].size(), mod + 2 * k));
					}
					
					for(FireBall fireBall : Map[i][j]) {
						fireBallArr.remove(fireBall);
					}
				}
			}
		}
	}
}
