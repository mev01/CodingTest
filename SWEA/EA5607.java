import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA5607 {
	static long[] fac = new long[1000001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		fac[0] = 1;
		for (int i = 1; i <= 1000000; i++) {
			fac[i] = fac[i-1] * i % 1234567891;
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			long tmp = fac[N - R] * fac[R] % 1234567891;
			
			tmp = power(tmp, 1234567891 - 2, 1234567891);
			
			long ans = (fac[N] * tmp) % 1234567891;
			
			sb.append("#"+tc+" "+ans+'\n');
		}
		System.out.print(sb.toString());
	}
	private static long power(long x, int y, int p) {
		long res = 1L;
		
		while(y > 0) {
			if(y % 2 == 1)
				res = (res * x) % p;
			y = y >> 1;
			x = (x * x) % p;
		}
		return res;
	}
}
