import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1946 {
	static int ans = 0;
	static int[] volarr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			ans = 0;
			volarr = new int[N+1];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				volarr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
			}
			
			int temp = volarr[1];
			for (int i = 2; i <= N; i++) {
				if(temp > volarr[i]) {
					temp = volarr[i];
				}
				else {
					ans++;
				}
			}
			
			sb.append(N-ans).append('\n');
		}
		System.out.print(sb.toString());
	}
}