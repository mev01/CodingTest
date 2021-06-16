import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon1561 {
	static long N;
	static int M, ans = 0;
	static int[] time;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		time = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		long min = findTime(0, 60000000000L);
		
		long num = calc(min - 1);
		if(num < M) num = 0;
		
		for (int i = 0; i < M; i++) {
			if(min % time[i] == 0) ++num;
			if(num == N) {
				ans = i + 1;;
				break;
			}
		}
		
		System.out.print(ans);
	}

	private static long findTime(long s, long e) {
		if(s == e) return s;
		long min = calc((s + e) / 2);
		
		if(N <= min) return findTime(s, (s + e) / 2);
		else return findTime((s + e) / 2 + 1, e);
	}

	private static long calc(long min) {
		long tmp = M;
		
		for (int i = 0; i < M; i++) {
			tmp += min / time[i];
		}
		
		return tmp;
	}
}
