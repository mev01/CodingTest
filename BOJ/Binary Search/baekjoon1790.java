import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1790 {
	static int N, K, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		BSearch(1, N);
		
		System.out.print(ans);
	}

	private static void BSearch(int s, int e) {
		if(s > e) {
			ans = -1;
			return;
		}
		
		int m = (s + e) / 2;
		
		int num = getTH(m);
		
		if(num - (int) Math.log10(m) - 1 > K) BSearch(s, m - 1);
		else if(num < K) BSearch(m + 1, e);
		else {
			while(true) {
				if(num-- == K) {
					ans = m % 10;
					return;
				}
				else m /= 10;
			}
		}
	}

	private static int getTH(int m) {
		int len = (int) Math.log10(m) + 1;
		
		int th = 0;
		
		th += (m - Math.pow(10, len - 1) + 1) * len--;
		while(len > 0) {
			th += len * 9 * Math.pow(10, len-- - 1);
		}
		return th;
	}
}
