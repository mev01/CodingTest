import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon3687 {
	static String[] add = {"1", "7", "4", "2", "0", "8"};
	static long[] maxDP = new long[101];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		setMinDP();
		while(N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			
			sb.append(maxDP[num]).append(' ').append(getMax(num)).append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	private static void setMinDP() {
		Arrays.fill(maxDP, Long.MAX_VALUE);
		maxDP[2] = 1;
		maxDP[3] = 7;
		maxDP[4] = 4;
		maxDP[5] = 2;
		maxDP[6] = 6;
		maxDP[7] = 8;
		maxDP[8] = 10;
		
		for (int i = 9; i <= 100; i++) {
			for (int j = 2; j < 8; j++) {
				String addNum = maxDP[i - j] + add[j - 2];
				maxDP[i] = Math.min(Long.parseLong(addNum), maxDP[i]);
			}
		}
	}
	
	static public String getMax(int num) {
		StringBuilder sb = new StringBuilder();
		
		if(num % 2 == 1) {
			num -= 3;
			sb.append(add[1]);
		}
		
		int quo = num / 2;
		while(quo-- > 0) {
			sb.append(add[0]);
		}
		
		return sb.toString();
	}
}
