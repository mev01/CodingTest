import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1285 {
	static int N, ans = Integer.MAX_VALUE;
	static Queue<char[][]> que;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		char[][] coin = new char[N][N];
		for (int i = 0; i < N; i++) {
			coin[i] = br.readLine().toCharArray();
		}
		
		que = new LinkedList<char[][]>();
		que.offer(coin);
		
		DFS(coin);
		
//		while(!que.isEmpty()) {
//			char[][] arr = que.poll();
//			
//			// arr이 뒤집을 필요가 있는지
//			if(!nessFlip(arr)) {
//				int cnt = 0;
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						if(arr[i][j] == 'T') ++cnt;
//					}
//				}
//				ans = Math.min(ans, cnt);
//				continue;
//			}
//			
//			for (int i = 0; i < N; i++) {
//				int cnt = 0;
//				for (int j = 0; j < N; j++) {
//					if(arr[i][j] == 'T') ++cnt;
//				}
//				if(cnt >= (float)N / 2) que.offer(Flip(i, arr));
//			}
//			for (int j = 0; j < N; j++) {
//				int cnt = 0;
//				for (int i = 0; i < N; i++) {
//					if(arr[i][j] == 'T') ++cnt;
//				}
//				if(cnt >= (float)N / 2) que.offer(Flip(N + j, arr));
//			}
//		}
		
		System.out.print(ans);
	}

	private static void DFS(char[][] arr) {
		// arr이 뒤집을 필요가 있는지
		if(!nessFlip(arr)) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j] == 'T') ++cnt;
				}
			}
			ans = Math.min(ans, cnt);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if(arr[i][j] == 'T') ++cnt;
			}
			if(cnt >= (float)N / 2) DFS(Flip(i, arr));
		}
		for (int j = 0; j < N; j++) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if(arr[i][j] == 'T') ++cnt;
			}
			if(cnt >= (float)N / 2) DFS(Flip(N + j, arr));
		}
	}

	private static boolean nessFlip(char[][] arr) {
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if(arr[i][j] == 'T') {
					++cnt;
				}
			}
			if(cnt >= (float)N / 2) return true;
		}
		return false;
	}

	private static char[][] Flip(int rc, char[][] arr) {
		char[][] coin = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				coin[i][j] = arr[i][j];
			}
		}
		
		if(rc < N) {
			for (int i = 0; i < N; i++) {
				if(coin[rc][i] == 'H') coin[rc][i] = 'T';
				else coin[rc][i] = 'H';
			}
		}
		else {
			for (int i = 0; i < N; i++) {
				if(coin[i][rc - N] == 'H') coin[i][rc - N] = 'T';
				else coin[i][rc - N] = 'H';
			}
		}
		return coin;
	}
}
