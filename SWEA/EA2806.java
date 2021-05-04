import java.util.Arrays;
import java.util.Scanner;

import javax.security.sasl.SaslClient;

public class EA2806 {
	static Boolean[] vertical;
	static Boolean[] leftDiag;
	static Boolean[] rightDiag;
	static int ans;
	
	static void Queen(int x, int y, int N) {
		System.out.println(x+"  "+y);
		if(y == N || x == N) return;
		int leftdiag, rightdiag;
		//leftdiag
		if(x >= y) {
			int tmp = x - y;
			leftdiag = N-1-tmp;
		}
		else {
			int tmp = y-x;
			leftdiag = N-1+tmp;
		}
		//rightdiag
		if(x >= y) {
			int tmp = N-1-y;
			leftdiag = x-tmp;
		}
		else {
			int tmp = x;
			leftdiag = N-1+tmp;
		}
		
		if(vertical[y] == false) {
			vertical[y] = true;
			if(x == N-1) ans++;
			Queen(x+1, 0, N);
			vertical[y] = false;
		}
		Queen(x, y+1, N);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int i = 1; i <= T; i++) {
			int N = sc.nextInt();
			ans = 0;
			vertical = new Boolean[N];
			leftDiag = new Boolean[N+N-1];
			rightDiag = new Boolean[N+N-1];
			Arrays.fill(vertical, false);
			Arrays.fill(leftDiag, false);
			Arrays.fill(rightDiag, false);
			
			for (int y = 0; y < N; y++) {
				Queen(0, y, N);
			}
			
			System.out.println("#"+i+" "+ans);
		}
	}

}
