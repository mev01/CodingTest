import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0, e = 1000000000;
		while(s < e) {
			int m = (s + e + 1) / 2;
			
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += (arr[i] > m) ? arr[i] - m : 0;
			}
			
			if(sum < M) {
				e = m - 1;
			}
			else {
				s = m;
			}
		}
		
		System.out.println(s);
	}
}
