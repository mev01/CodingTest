import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon15651 {
	static int N, M;
	static StringBuilder sb;
	static int[] ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = new int[M];
		func(0);
		System.out.print(sb.toString());
	}

	private static void func(int cnt) {
		if(cnt == M){
			for (int i = 0; i < M; i++) {
				sb.append(ans[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = 1; i <= N; i++) {
			ans[cnt] = i;
			func(cnt+1);
		}
	}
}
