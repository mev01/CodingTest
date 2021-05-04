import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA3499 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] ans = new String[1001];
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			int len = Integer.parseInt(br.readLine());
			int half = len%2 == 0 ? len/2 : len/2+1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//처음 절반은 홀수 번호에 배치
			for (int j = 1; j <= half; j++) {
				ans[2*(j-1)+1] = st.nextToken();
			}
			//나중 절반은 짝수 번호에 배치
			for (int j = half+1; j <= len; j++) {
				ans[2*(j-half)] = st.nextToken();
			}
			
			sb.append("#"+i+" ");
			for (int j = 1; j <= len; j++) {
				sb.append(ans[j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}