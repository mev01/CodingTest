import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1644 {
	static int N, ans = 0;
	static boolean[] Prime;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Prime = new boolean[N + 1];
		Prime[1] = true;
		
		for (int i = 2; i <= N; i++) {
			if(!Prime[i]) {
				int cnt = 2;
				
				while(i * cnt <= N) {
					Prime[i * cnt++] = true;
				}
			}
		}
		
		int s = 1, e = 1, sum = 0;
		
		while(true) {
			if(e > N) break;
			
			while(e <= N && sum < N) {
				if(!Prime[e]) sum += e;
				e += 1;
			}
			
			while(s <= N && sum > N) {
				if(!Prime[s]) sum -= s;
				s += 1;
			}
			
			if(sum == N) {
				++ans;
				while(s <= e && sum >= N) {
					if(!Prime[s]) sum -= s;
					s += 1;
				}
			}
		}
		
		System.out.print(ans);
	}
}
