import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon1174 {
	static int N, cnt = 10;
	static int[] arr = new int[13];
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		if(N <= 10) {
			System.out.print(N-1);
			return;
		}
		else if(N > 1023) {
			System.out.print(-1);
			return;
		}
		for (int i = 2; i <= 10; i++) {
			func(i, i);
		}
	}
	private static void func(int idx, int all) {
		if(idx == 0) {
			if(++cnt == N) {
				for (int i = all; i >= 1; i--) {
					sb.append(arr[i]);
				}
				System.out.print(sb);
				System.exit(0);
			}
			return;
		}
		if(idx != all) {
			for (int i = 0; i <= 9; i++) {
				if(arr[idx + 1] > i) {
					arr[idx] = i;
					func(idx-1, all);
				}
			}
		}
		else {
			for (int i = 1; i <= 9; i++) {
				arr[idx] = i;
				func(idx-1, all);
			}
		}
		
	}
}
