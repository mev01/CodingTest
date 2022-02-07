import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon15724 {
	static int N, M, K;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) - map[i - 1][j - 1] + map[i - 1][j] + map[i][j - 1];
			}
		}
		
		K = Integer.parseInt(br.readLine());
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = map[x2][y2]; // (1, 1) - (x2, y2) 까지 합
			sum -= map[x1 - 1][y2] + map[x2][y1 - 1]; // (1, 1) - (x1 - 1, y2), (1, 1) - (x2, y1 - 1) 빼주기
			sum += map[x1 - 1][y1 - 1]; // (1, 1) - (x1 - 1, y1 - 1) 위에서 빼주면서 2번 뺀 부분 채워주기
			
			sb.append(sum).append('\n');
		}
		
		System.out.println(sb.toString());
	}

}
