import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA11387 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			sb.append("#"+i+" "+ ( D*N + D/100*( N * (N-1))/2*L ) + "\n");
		}
		System.out.println(sb.toString());
	}
}