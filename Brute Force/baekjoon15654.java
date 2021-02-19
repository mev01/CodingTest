import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon15654 {
	static int N, M;
	static StringBuilder sb;
	static int[] num, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		num = new int[N];
		ans = new int[M];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num);
		func(0, 0);
		System.out.print(sb.toString());
	}

	private static void func(int cnt, int flag) {
		if(cnt == M){
			for (int i = 0; i < M; i++) {
				sb.append(ans[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = 0; i < N; i++) {
			if((flag & 1<<i) == 0){
				ans[cnt] = num[i];
				func(cnt+1, flag | 1<<i);
			}
		}
	}
}
