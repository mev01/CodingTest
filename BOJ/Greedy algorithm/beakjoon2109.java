import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class beakjoon2109 {
	static int N, ans = 0;
	static Lecture[] arr;
	static PriorityQueue<Integer> que;
	
	static class Lecture implements Comparable<Lecture>{
		int pay, day;

		public Lecture(int pay, int day) {
			super();
			this.pay = pay;
			this.day = day;
		}

		@Override
		public int compareTo(Lecture o) {
			return o.day - this.day;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new Lecture[N + 1];
		que = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			
			arr[i] = new Lecture(pay, day);
		}
		arr[N] = new Lecture(0, 0);
		
		Arrays.sort(arr);
		
		int day = arr[0].day;
		int idx = 0;
		
		while(day >= 1) {
			while(idx < N && day <= arr[idx].day) que.offer(arr[idx++].pay);
			
			int nday = arr[idx].day;
			while(!que.isEmpty() && nday < day) {
				ans += que.poll();
				--day;
			}
			day = nday;
		}
		
		System.out.print(ans);
	}
}
