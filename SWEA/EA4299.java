import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA4299 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int H, M, D;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()) - 11;
			H = Integer.parseInt(st.nextToken()) - 11;
			M = Integer.parseInt(st.nextToken()) - 11;
			
			if(M < 0) {
				M += 60;
				H -= 1;
			}
			if(H < 0){
				H += 24;
				D -= 1;
			}
			
			if(D<0 || H<0 || M<0) sb.append("#"+tc+" "+-1+"\n");
			else
				sb.append("#"+tc+" "+(D*60*24 + H*60 +M)+"\n");
		}
		System.out.println(sb.toString());
	}

}
