import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon17088 {
	static int N, diff;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N <= 2) {
			System.out.print(0);
		}
		else {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					diff = (arr[0] + i) - (arr[1] + j);
					Search(2, arr[1] + j, Math.abs(i) + Math.abs(j));
				}
			}
			
			System.out.print(-1);
		}
	}

	private static void Search(int idx, int pre, int cnt) {
		if(idx == N) {
			System.out.print(cnt);
			System.exit(0);
		}
		
		if(pre - arr[idx] == diff) Search(idx + 1, arr[idx], cnt);
		else if(pre - (arr[idx] + 1) == diff) Search(idx + 1, arr[idx] + 1, cnt + 1);
		else if(pre - (arr[idx] - 1) == diff) Search(idx + 1, arr[idx] - 1, cnt + 1);
	}
}
