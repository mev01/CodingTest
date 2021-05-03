import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon1041 {
	static long N;
	static int[] minarr = new int[4], numarr = new int[7];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		if(N == 1) {
			int sum = 0, max = 0;
			for (int i = 0; i < 6; i++) {
				numarr[i] = Integer.parseInt(st.nextToken());
				sum += numarr[i];
				max = Math.max(max, numarr[i]);
			}
			System.out.print(sum-max);
			return;
		}
		for (int i = 0; i < 6; i++) {
			numarr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(minarr, Integer.MAX_VALUE);
		for (int i = 1; i <= 3; i++) {
			findMin(0, i, 0, 0);
		}
		
		long side1 = minarr[1] * ( (N-2)*(N-2) + 4*(N-2)*(N-1) );
		long side2 = minarr[2] * ( 4*(N-2) + 4*(N-1) );
		long side3 = minarr[3] * 4;
		System.out.print(side1 + side2 + side3);
		
	}

	private static void findMin(int cnt, int num, int flag, int sum) {
		if(cnt == num) {
			minarr[num] = Math.min(sum, minarr[num]);
			return;
		}
		if(minarr[num] <= sum) {
			return;
		}
		for (int i = 0; i < 6; i++) {
			if((flag & 1<<i) == 0){
				findMin(cnt+1, num, flag | 1<<i | 1<<(6-i-1), sum + numarr[i]);
			}
		}
	}
}
