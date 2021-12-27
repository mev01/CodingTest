import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Character.Subset;
import java.util.StringTokenizer;

public class beakjoon16938 {
	static int N, L, R, X, ans = 0;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Subset(0);
		
		System.out.println(ans);
	}
	
	static void Subset(int idx) {
		if(idx == N) {
			if(Check()) ans++;
			
			return;
		}
		
		visited[idx] = false;
		Subset(idx + 1);
		visited[idx] = true;
		Subset(idx + 1);
	}

	private static boolean Check() {
		int sum = 0, min = Integer.MAX_VALUE, max = 0;
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) {
				sum += arr[i];
				min = Math.min(min, arr[i]);
				max = Math.max(max, arr[i]);
			}
		}
		
		if(L <= sum && sum <= R && max - min >= X) return true;
		
		return false;
	}

}
