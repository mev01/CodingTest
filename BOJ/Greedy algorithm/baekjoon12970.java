import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon12970 {
	static int N, K;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list = new ArrayList<>();
		
		int sum = N - 1;
		if(N <= K) {
			list.add(N - 1);
			
			while(sum < K && sum > 0) {
				sum -= list.size();
				
				if(K - sum < list.get(list.size() - 1)) {
					list.add(K - sum);
					sum += K - sum;
				}
				else {
					list.add(list.get(list.size() - 1) - 1);
					sum += list.get(list.size() - 1);
				}
			}
		}
		else {
			list.add(K);
		}
		
		if(sum <= 0) {
			sb.append(-1);
		}
		else {
			for (int i = 0; i < N; i++) {
				sb.append('B');
			}
			for (int i = 0; i < list.size(); i++) {
				sb.replace(list.get(i), list.get(i) + 1, "A");
			}
			sb.reverse();
		}
		
		System.out.println(sb.toString());
	}
}
