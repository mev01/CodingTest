import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon7568 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] weight = new int[51], height = new int[51], answer = new int[51];
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			
			weight[i] = x;
			height[i] = y;
		}
		
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {	//전체에서 자신보다 큰 덩치 확인
				if(height[i] < height[j] && weight[i] < weight[j]) {
					cnt++;
				}
			}
			answer[i] = cnt+1;
		}
		for (int i = 1; i <= N; i++) {
			sb.append(answer[i]+" ");
		}
		System.out.println(sb.toString());
	}

}
