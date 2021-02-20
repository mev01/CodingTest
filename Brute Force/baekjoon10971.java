import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10971 {
	static int N, ans = Integer.MAX_VALUE;
	static int[] route;
	static int[][] travelCost;


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		route = new int[N];
		route[0] = 0;
		travelCost = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				travelCost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		traveling(1, 0);
		System.out.println(ans);
	}

	private static void traveling(int cnt, int flag) {
		if(cnt == N){
			int temp = 0;
			for (int i = 0; i < N; i++) {
				int cost = travelCost[route[i]][route[(i+1)%N]];
				if(cost == 0) return;
				temp += cost;
			}
			if(temp < ans) ans = temp;
			return;
		}
		for (int i = 1; i < N; i++) {
			if((flag & 1<<i) == 0){
				route[cnt] = i;
				traveling(cnt+1, flag | 1<<i);
			}
		}
	}

}
