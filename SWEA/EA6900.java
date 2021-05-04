import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class EA6900 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String[] numArr = new String[100];
		int[] moneyArr = new int[100];
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int sumMoney = 0;
			
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				numArr[j] = st.nextToken();
				moneyArr[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < M; j++) {
				String id = br.readLine();
				loop:
				for (int k = 0; k < N; k++) {
					for (int idx = 0; idx < numArr[k].length(); idx++) {
						if(id.charAt(idx) != numArr[k].charAt(idx) && numArr[k].charAt(idx) !='*') {
							break;
						}
						if(idx == numArr[k].length()-1) { 
							sumMoney += moneyArr[k];
							break loop;
						}
					}
				}
			}
			sb.append("#").append(i).append(" ").append(sumMoney).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

}
