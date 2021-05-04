import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA5431 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] stu = new int[101];
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= K; j++) {
				stu[Integer.parseInt(st.nextToken())] = 1;
			}
			
			sb.append("#"+i+" ");
			for (int j = 1; j <= N; j++) {
				if(stu[j]==0) sb.append(j+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
