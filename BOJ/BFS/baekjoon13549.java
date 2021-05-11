import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon13549 {
	static int N, K, ans = Integer.MAX_VALUE;
	static PriorityQueue<Dot> que;
	static boolean[] visit;
	
	static class Dot implements Comparable<Dot>{
		int num, sec;

		public Dot(int num, int sec) {
			super();
			this.num = num;
			this.sec = sec;
		}

		@Override
		public int compareTo(Dot o) {
			return this.sec - o.sec;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visit = new boolean[200000];
		que = new PriorityQueue<>();
		que.offer(new Dot(N, 0));
		
		while(!que.isEmpty()) {
			Dot dot = que.poll();
			
			if(visit[dot.num]) continue;
			visit[dot.num] = true;
			
			if(dot.num == K) {
				ans = dot.sec;
				break;
			}
			
			if(dot.num < K) {
				que.offer(new Dot(dot.num * 2, dot.sec));
				que.offer(new Dot(dot.num + 1, dot.sec + 1));
			}
			if(dot.num != 0) {
				que.offer(new Dot(dot.num - 1, dot.sec + 1));
			}
		}
		
		System.out.print(ans);
	}
}
