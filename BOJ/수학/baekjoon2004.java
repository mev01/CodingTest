import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2004 {
	static long n, m;
	static long[] num = new long[3];
	static long[] numFive = new long[3], numTwo = new long[3];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		m = Long.parseLong(st.nextToken());
		
		num[0] = n;
		num[1] = m;
		num[2] = n - m;
		
		for (int i = 0; i < 3; i++) {
			long mod = 2;
			while(true){
				long temp = num[i] / mod;
				if(temp > 0) numTwo[i] += temp;
				else break;
				
				mod *= 2;
			}
			
			mod = 5;
			while(true){
				long temp = num[i] / mod;
				if(temp > 0) numFive[i] += temp;
				else break;
				
				mod *= 5;
			}
		}
		
		System.out.println(Math.min(numFive[0] - numFive[1] - numFive[2], numTwo[0] - numTwo[1] - numTwo[2]));
	}
}
