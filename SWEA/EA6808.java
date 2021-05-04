import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EA6808 {
	static int[] gyuArr = new int[9], inArr = new int[9];
	static boolean[] check = new boolean[9];
	static int win = 0, fail = 0;
	static int MAX_SCORE = 19 * 9, All_SCORE = 9*8*7*6*5*4*3*2*1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			win = 0;
			fail = 0;
			for (int i = 0; i < 9; i++) {
				gyuArr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(gyuArr);
			
			int gyuIdx = 0, inIdx = 0;
			for (int i = 1; i <= 18; i++) {
				if(gyuIdx <= 8 && gyuArr[gyuIdx] == i)
					gyuIdx++;
				else {
					inArr[inIdx++] = i;
				}
			}
			
			func(0, 0, 0);
			
			sb.append("#"+tc+" "+(All_SCORE-fail)+" "+fail+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void func(int idx, int winScore, int failScore) {
		if(MAX_SCORE/2 < failScore) {
			int temp = 1;
			for (int i = idx; i < 9; i++) {
				temp *= (9-i);
			}
			fail += temp;
			return;
		}
		else if(MAX_SCORE/2 < winScore) {
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if(!check[i]) {
				check[i] = true;
				if(gyuArr[idx] > inArr[i])
					func(idx+1, winScore + (gyuArr[idx] + inArr[i]), failScore);
				else
					func(idx+1, winScore, failScore + (gyuArr[idx] + inArr[i]));
				check[i] = false;
			}
		}
	}

}
