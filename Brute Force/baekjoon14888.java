import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14888 {
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] num, oper, operCnt = new int[5];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		oper = new int[N-1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		// 1: + 2: - 3: * 4: /
		for (int i = 1; i <= 4; i++) {
			operCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		//조합
		comb(1, 0, 0);
		
		sb.append(max).append('\n').append(min);
		System.out.print(sb.toString());
	}

	private static void comb(int operNum, int cnt, int idx) {
		//oper 배열이 다 채워졌을 때
		if(operNum == 5) {
			int temp = Calc();
			max = Math.max(max, temp);
			min = Math.min(min, temp);
			
			return;
		}
		//해당 연산자의 수만큼 oper 배열에 다 채웠을 때
		//다음 연산자로 넘어감
		if(operCnt[operNum] == cnt) {
			comb(operNum+1, 0, 0);
			return;
		}
		for (int i = idx; i < N-1; i++) {
			if(oper[i] == 0) {
				oper[i] = operNum;
				comb(operNum, cnt+1, i+1);
				oper[i] = 0;
			}
		}
	}

	private static int Calc() {
		int temp = num[0];
		for (int i = 1; i < N; i++) {
			switch (oper[i-1]) {
			case 1:
				temp += num[i];
				break;
			case 2:
				temp -= num[i];		
				break;
			case 3:
				temp *= num[i];	
				break;
			case 4:
				temp /= num[i];	
				break;
			}
		}
		return temp;
	}

}
