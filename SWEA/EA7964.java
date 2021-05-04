import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA7964 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()), dis = Integer.parseInt(st.nextToken());
			
			int temp = 0, ans = 0;
			while(num-- > 0) {
				if(br.read() == 48)
					temp++;
				else
					temp = 0;
				if(temp == dis) {
					ans++;
					temp = 0;
				}
				br.read();
			}
			br.read();
			sb.append("#"+i+" "+ans+"\n");
		}
		System.out.println(sb.toString());
	}
}