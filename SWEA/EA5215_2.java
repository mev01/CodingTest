import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA5215_2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[] score = new int[20], cal = new int[20];
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int answer = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < 1<<N; i++) {
				int sumScore = 0, sumCal = 0;
				
				for (int j = 0; j < N; j++) {
					if((1<<j & i) != 0) {
						sumCal += cal[j];
						if(sumCal > L) break;
						sumScore += score[j];
					}
				}
				if(answer < sumScore) answer = sumScore;
			}
			
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb.toString());
	}
}