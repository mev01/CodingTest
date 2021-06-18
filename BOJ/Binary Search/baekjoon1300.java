import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1300 {
	static int N, k, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		BSearch(1, k);
		
		System.out.print(ans);
	}

	private static void BSearch(int s, int e) {
		int m = (s + e) / 2;
		int sum = 0;
		
		for (int i = 1; i <= N; i++) {
			sum += Math.min(m / i, N);
		}
		
		if(s == e) {
			ans = s;
			return;
		}
		if(sum >= k) BSearch(s, (s + e) / 2);
		else BSearch((s + e) / 2 + 1, e);
	}
}
