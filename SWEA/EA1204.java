import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA1204 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] score = new int[101];
			int answer=0, maxScore = 0;
			
			while(st.hasMoreTokens()) {
				score[Integer.parseInt(st.nextToken())]++;
			}
			
			sb.append("#"+i+" ");
			for (int j = 100; j >= 0; j--) {
				if(score[j]>maxScore) {
					maxScore = score[j];
					answer = j;
				}
			}
			sb.append(answer+"\n");
		}
		System.out.println(sb.toString());
	}

}
