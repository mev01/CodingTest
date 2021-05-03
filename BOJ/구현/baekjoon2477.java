import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2477 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int K = Integer.parseInt(br.readLine());
		
		int[] num = new int[8];
		int[] len = new int[8];
		for (int i = 1; i <= 6; i++) {
			st = new StringTokenizer(br.readLine());
			
			num[i] = Integer.parseInt(st.nextToken());
			len[i] = Integer.parseInt(st.nextToken());
		}
		
		int wid = 1;
		int minus = 1;
		for (int i = 1; i <= 6; i++) {
			int prev = i-1;
			int next = i+1;
			if(prev == 0) prev = 6;
			if(next == 7) next = 1;
			
			if(num[prev] == num[next]) { 
				minus *= len[i];
				if(i+3 > 6)
					wid *= len[(i+5)%8];
				else
					wid *= len[i+3];
				
			}
		}
		
		System.out.println(K * (wid - minus) );
	}
}