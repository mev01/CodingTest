import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon14501 {
	static int N, ans = 0;
	static int[] T, P;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		T = new int[N + 1];
		P = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		Search(1, 0);
		
		System.out.println(ans);
	}

	private static void Search(int idx, int price) {
		if(idx == N + 1) {
			ans = Math.max(ans, price);
			return;
		}
		
		if(idx + T[idx] <= N + 1) Search(idx + T[idx], price + P[idx]); 
		Search(idx + 1, price);
	}
}
