import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 0-1 knapsack
 * 연산을 뒤에서부터 계산해서 dp배열을 한줄로
 */

public class baekjoon12865 {
	static int N, K;
	static int[] dp;
	static Product[] pArr;
	
	static class Product{
		int w, v;

		public Product(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		pArr = new Product[N + 1];
		dp = new int[K + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			pArr[i] = new Product(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = K; j >= pArr[i].w; j--) {
				dp[j] = Math.max(dp[j], dp[j-pArr[i].w] + pArr[i].v);
			}
		}
		
		System.out.print(dp[K]);
	}
}
