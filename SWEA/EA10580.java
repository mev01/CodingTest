import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EA10580 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			//입력
			int N = Integer.parseInt(br.readLine());
			int[] wire = new int[N];
			int[] wireLocation = new int[10001];
			int answer = 0;
			for (int j = 0; j < N; j++) {
				StringTokenizer test = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(test.nextToken()), B = Integer.parseInt(test.nextToken());
				
				wire[j] = A;
				wireLocation[A] = B;
			}
			
			Arrays.sort(wire);
			for (int j = 1; j < N; j++) {
				for (int k = 0; k < j; k++) {
					if(wireLocation[wire[k]] > wireLocation[wire[j]]) answer++;
				}
			}
			
			bw.write("#"+i+" "+answer+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
}
