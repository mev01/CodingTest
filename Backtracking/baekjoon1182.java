import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon1182 {
	static int N, S, ans = 0;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		func(0, false, 0);
		
		System.out.print(ans);
	}
	private static void func(int idx, boolean isChose, int sum) {
		if(sum == S){
			if(isChose){
				ans++;
				if(arr[idx-1] > 0) return;
			}
		}
		if(sum > S){
			if(idx != 0 && arr[idx-1] > 0) return;
		}
		if(idx == N){
			return;
		}
		func(idx + 1, true, sum + arr[idx]);
		func(idx + 1, false, sum);
	}
}