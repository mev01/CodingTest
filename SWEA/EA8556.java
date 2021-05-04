import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EA8556 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			String line = br.readLine();
			int top = 0, bot = 1, cnt = 0;
			
			for (int j = line.length()-4; j >= 0; j--) {
				if(line.charAt(j) == 'n') {
					if(cnt++ == 0) top = 0;
					else {
						top = top*2 - 90;
						bot *= 2;
					}
				}
				else if(line.charAt(j) == 'w') {
					if(cnt++ == 0) top = 90;
					else {
						top = top*2 + 90;
						bot *= 2;
					}
				}
			}
			
			while(top%2 == 0 && bot >= 2) {
				top /= 2;
				bot /= 2;
			}
			
			sb.append("#"+i+" "+top+(bot==1 ? "" : "/"+bot)+"\n");
		}
		System.out.println(sb.toString());
	}
}