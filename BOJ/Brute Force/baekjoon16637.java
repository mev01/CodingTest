import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon16637 {
	static int N;
	static long ans = Long.MIN_VALUE;
	static int[] num;
	static char[] oper;
	static boolean[] check;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		oper = new char[N];
		check = new boolean[N];
		String exp = br.readLine();
		for (int i = 0; i < N; i++) {
			if(i % 2 == 0) {
				num[i] = exp.charAt(i) - '0';
			}
			else {
				oper[i] = exp.charAt(i);
			}
		}
		
		func(1, false);
		System.out.println(ans);
	}
	
	private static void func(int idx, boolean chose) {
		if(idx >= N) {
			long result = num[0];
			//자신보다 +2 check 배열을 확인해서
			//괄호가 있다면 그 연산자를 먼저 계산 후 자신 계산
			for (int i = 1; i < N; i+=2) {
				if(i != N-2 && check[i+2]) {
					result = operation(i, result, operation(i+2, num[i+1], num[i+3]));
					i += 2;
				}
				else {
					result = operation(i, result, num[i+1]);
				}
			}
			ans = Math.max(result, ans);
			return;
		}
		if(!chose) {	//이전 연산자에 괄호가 없는 경우이므로 이번 연산자에 괄호
			check[idx] = true;
			func(idx+2, true);
		}
		check[idx] = false;
		func(idx+2, false);		
	}
	
	private static long operation(int operidx, long fir, long sec) {
		char temp = oper[operidx];
		if(temp == '+') {
			return fir + sec;
		}
		else if(temp == '-') {
			return fir - sec;
		}
		else {
			return fir * sec;
		}
	}
}
