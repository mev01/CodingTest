import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon1248 {
	static int N;
	static int[] A;
	static char[][] S;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		S = new char[N][N];
		
		//S배열에 input 값 넣기
		String inputStr = br.readLine();
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				S[i][j] = inputStr.charAt(index++);
			}
		}
		
		Backtrack(0);

		for (int i = 0; i < N; i++) {
			sb.append(A[i]).append(' ');
		}
		System.out.print(sb.toString());
	}
	private static boolean Backtrack(int cnt) {
		//가지치기
		//지금 A배열에 넣은 수까지만 맞는지 검사
		if(cnt >= 2) {
			for (int j = 0; j < cnt - 1; j++) {
				int sum = 0;
				for (int idx = j; idx <= cnt-1; idx++) {
					sum += A[idx];
				}
				if(sum < 0 && S[j][cnt - 1] != '-') {
					return false;
				}
				else if(sum > 0 && S[j][cnt - 1] != '+') {
					return false;
				}
				else if(sum == 0 && S[j][cnt - 1] != '0') {
					return false;
				}
			}
		}
		if(cnt == N) {
			return true;
		}
		//A배열에 숫자 넣기
		//0,0 1,1 ... 자리를 검사해서 +면 1~10까지만 숫자 넣기 
		if(S[cnt][cnt] == '+') {
			for (int i = 1; i <= 10; i++) {
				A[cnt] = i;
				if(Backtrack(cnt+1)) return true;
			}
		}
		else if(S[cnt][cnt] == '-') {
			for (int i = -10; i <= -1; i++) {
				A[cnt] = i;
				if(Backtrack(cnt+1)) return true;
			}
		}
		else {
			A[cnt] = 0;
			if(Backtrack(cnt+1)) return true;
		}
		return false;
	}
	
}
