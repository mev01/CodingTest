import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA1952 {
	static int ans = Integer.MAX_VALUE;
	static int[] availMonth = {0, 1, 3, 12};
	static int[] cost = new int[4];
	static int[][] plan = new int[2][13];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[0][i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= 12; i++) {	//1달 이용권
				plan[0][i] = Math.min(plan[0][i-1], plan[1][i-1]) + Math.min(plan[0][i] * cost[0], cost[1]);
				plan[1][i] = (i >= 3) ?  Math.min(plan[0][i-3], plan[1][i-3]) + cost[2] : cost[2];
			}
			
			ans = Math.min(plan[0][12], plan[1][12]);
			
			ans = Math.min(ans, cost[3]);	//1년 이용권
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.print(sb.toString());
	}
}
