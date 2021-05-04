import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA8104 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			sb.append("#"+tc+" ");
			if(N%2 ==0) {
				for (int i = 0; i < K; i++) {
					sb.append((1+N*K)*N/2+" ");	
				}
			}
			else {
				for (int i = 1; i <= K; i++) {
					sb.append((1+K*(N-1))*(N-1)/2+K*(N-1)+i+" ");	
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}