import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2961 {
	static int T, answer = Integer.MAX_VALUE, cnt = 0;
	static int[] sour = new int[10], bitter = new int[10];
	
	static void func(int num, int flag) {
		if(num == T) { 
			if(flag == 0) return;
			int mulSour = 1, sumBitter = 0;
			
			for (int i = 0; i < T; i++) {
				if((1 << i & flag) != 0) {
					mulSour *= sour[i];
					sumBitter += bitter[i];
				}
			}
			if(answer > Math.abs(mulSour - sumBitter))
				answer = Math.abs(mulSour - sumBitter);
			return;
		}
		func(num+1, flag | 1 << num);
		func(num+1, flag);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}
		
		func(0, 0);
		System.out.println(answer);
	}

}
