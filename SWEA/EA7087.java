import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EA7087 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());
			boolean[] isTitle = new boolean[26];
			
			for (int j = 0; j < N; j++) {
				String title = br.readLine();
				isTitle[title.charAt(0)-'A'] = true;
			}
			
			int j;
			for (j = 0; j < 26; j++) {
				if(!isTitle[j]) break;
			}
			
			sb.append("#"+i+" "+(j-0)+"\n");
		}
		System.out.println(sb.toString());
	}

}
