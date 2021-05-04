import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EA9778 {
	static int[] card;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			card = new int[] {0,0,4,4,4,4,4,4,4,4,16,4};
			int N = Integer.parseInt(br.readLine());
			int sum = 0, win = 0, lose = 0;
			
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(br.readLine());
				sum += a;
				card[a]--;
			}
			
			for (int i = 2; i <= 11; i++) {
				if(i > 21-sum) lose += card[i];
				else win += card[i];
			}
			
			sb.append("#"+tc+" ").append( (win<=lose) ? "STOP" : "GAZUA").append("\n");
		}
		System.out.print(sb.toString());
	}
}