import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA8457 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int ans = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int clock = Integer.parseInt(st.nextToken());
				int time = B/clock*clock;
				
				if(time >= B-E) ans++;
				else if(time+clock <= B+E) ans++;
			}
			sb.append("#"+tc+" "+ans+"\n");			
		}
		System.out.print(sb.toString());
	}
}