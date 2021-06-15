import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon1202 {
	static int N, K;
	static long ans = 0;
	static int[] backpackList;
	static jewelry[] jewelryList;
	static PriorityQueue<Integer> pq;
	
	static class jewelry implements Comparable<jewelry>{
		int M, V;

		public jewelry(int m, int v) {
			super();
			M = m;
			V = v;
		}

		@Override
		public int compareTo(jewelry o) {
			return this.M - o.M;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewelryList = new jewelry[N];
		backpackList = new int[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewelryList[i] = new jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < K; i++) {
			backpackList[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(jewelryList);
		Arrays.sort(backpackList);
		
		int idx = 0;
		pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < K; i++) {
			int c = backpackList[i];
			
			while(idx < N && jewelryList[idx].M <= c)
				pq.offer(jewelryList[idx++].V);
			
			if(!pq.isEmpty())
				ans += pq.poll();
		}
		
		System.out.print(ans);
	}
}
