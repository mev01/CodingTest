import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon16936 {
	static int N;
	static long[] arr, ans;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		ans = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			Search(arr[i], 0);
		}
	}

	private static void Search(long num, int idx) {
		ans[idx] = num;
		if(idx == N - 1) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				sb.append(ans[i]).append(' ');
			}
			
			System.out.print(sb.toString());
			System.exit(0);
		}
		
		int nIdx = Arrays.binarySearch(arr, num * 2);
		if(nIdx >= 0 && !visited[nIdx]) {
			visited[nIdx] = true;
			Search(num * 2, idx + 1);
		}
		
		if(num % 3 == 0) {
			nIdx = Arrays.binarySearch(arr, num / 3);
			if(nIdx >= 0 && !visited[nIdx]) {
				visited[nIdx] = true;
				Search(num / 3, idx + 1);
			}
		}
	}
}
