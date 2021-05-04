import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EA1860 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			StringTokenizer test1 = new StringTokenizer(br.readLine());
			StringTokenizer test2 = new StringTokenizer(br.readLine());			
			int N = Integer.parseInt(test1.nextToken()), M = Integer.parseInt(test1.nextToken()), K = Integer.parseInt(test1.nextToken());
			int[] time = new int[N];
			for (int j = 0; j < N; j++) {
				time[j] = Integer.parseInt(test2.nextToken());
			}
			
			int guest = 0, isPossible = 1;
			Arrays.sort(time);
			for (int j = 0; j < N; j++) {
				if( (time[j]/M*K)-guest > 0) {
					guest++;
				}
				else {
					isPossible = 0;
					break;
				}	
			}
			bw.write("#"+i+" "+(isPossible==1 ? "Possible" : "Impossible")+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

}