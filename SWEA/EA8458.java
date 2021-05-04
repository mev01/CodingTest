/*
 * 모든 점들을 절댓값으로 바꾸었을 때
 * 1번째 움직임에서 갈 수 있는 거리는 1
 * 2번째 움직임에서 갈 수 있는 거리는 (1+2, |1-2|) = (1, 3)
 * 3번째 움직임에서 갈 수 있는 거리는 (1+3, |1-3|, 3-3, 3+3) = (0, 2, 4, 6)이 된다.
 * n번째 움직이세 갈 수 있는 최대 거리는 n!
 * 
 * 각 점의 x+y를 통해 0,0에서의 거리를 구하고 
 * 모든 거리 중 가장 큰 거리가 포함되어있는 움직임 중에서 최소 번째 움직임을 구한다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA8458 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int max = 0;
			boolean isPossible = true;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int sum = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
				
				if(i > 0) {
					if(max % 2 != sum % 2)	// 각 점에서의 거리 중 모두 홀수나 짝수가 아니라면
						isPossible = false;
				}
				max = Math.max(max, sum);
			}
			
			int fac = 0;
			int sum = 0;
			while(true) {
				if(max <= sum && (max - sum) % 2 == 0) break;
				
				sum += ++fac;
			}
			
			sb.append("#"+tc+" "+ (isPossible ? fac : -1) +'\n');
		}
		System.out.print(sb.toString());
	}
}
