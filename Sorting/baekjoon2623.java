import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon2623 {
	static int N, M;
	static int[] cnt;
	static boolean[][] map;
	static Queue<Integer> que;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N + 1][N + 1];
		cnt = new int[N + 1];
		que = new LinkedList<Integer>();
		for (int i = 0; i < M; i++) {
			int a, b;
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			
			a = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				b = Integer.parseInt(st.nextToken());
				if(!map[a][b]) {
					map[a][b] = true;
					cnt[b]++;
				}
				a = b;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(cnt[i] == 0) que.offer(i);
		}
		
		while(!que.isEmpty()) {
			int pre = que.poll();
			sb.append(pre).append('\n');
			
			for (int i = 1; i <= N; i++) {
				if(map[pre][i]) {
					cnt[i]--;
					if(cnt[i] == 0) que.offer(i);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(cnt[i] != 0) {
				sb.setLength(0);
				sb.append(0);
			}
		}
		System.out.print(sb.toString());
	}
}
