import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1939 {
	static int N, M, S, E, ans;
	static boolean[] visited;
	static ArrayList<Bridge>[] list;
	static Queue<Integer> que;
	
	static class Bridge{
		int num, wei;

		public Bridge(int num, int wei) {
			super();
			this.num = num;
			this.wei = wei;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Bridge>();
		}
		
		int high = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Bridge(b, c));
			list[b].add(new Bridge(a, c));
			
			high = Math.max(high, c);
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		binarySearch(0, high);
		
		System.out.print(ans);
	}

	private static void binarySearch(int low, int high) {
		if(low == high) {
			ans = high;
			return;
		}
		int mid = (low + high) / 2 + 1;
		
		if(possibleWeight(mid)) binarySearch(mid, high);
		else binarySearch(low, mid - 1);
	}

	private static boolean possibleWeight(int mid) {
		que = new LinkedList<>();
		visited = new boolean[N + 1];
		
		que.offer(S);
		visited[S] = true;
		
		while(!que.isEmpty()) {
			int num = que.poll();
			if(num == E) return true;
			
			for (int i = 0; i < list[num].size(); i++) {
				Bridge bridge = list[num].get(i);
				
				if(visited[bridge.num]) continue;
				if(bridge.wei < mid) continue;
				
				que.offer(bridge.num);
				visited[bridge.num] = true;
			}
		}
		
		return false;
	}
}
