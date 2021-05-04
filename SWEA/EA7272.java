import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA7272 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			String str1 = st.nextToken(), str2 = st.nextToken();
			boolean isPossible = true;
			
			if(str1.length() == str2.length()) {
				for (int i = 0; i < str1.length(); i++) {
					if( isOneCircle(str1.charAt(i)) ^ isOneCircle(str2.charAt(i)) ) {
						isPossible = false;
						break;
					}
					else if(str1.charAt(i) == 'B' ^ str2.charAt(i) == 'B') {
						isPossible = false;
						break;
					}
				}
			}
			else
				isPossible = false;
			
			
			sb.append("#"+tc+" "+ (isPossible ? "SAME" : "DIFF") + "\n");
		}
		System.out.print(sb.toString());
	}

	private static boolean isOneCircle(char ch) {
		if(ch == 'A' || ch == 'D' || (ch>='O' && ch<='R'))
			return true;
		return false;
	}

}