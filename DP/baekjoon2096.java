import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2096 {
	static int[][] maxArr = new int[2][3], minArr = new int[2][3];
	static StringTokenizer st;
	static BufferedReader br;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int idx = 0;
		input(idx);
		
		for (int i = 1; i < N; i++) {
			idx ^= 1;
			input(idx);
			maxCalc(idx);
			minCalc(idx);
		}
		
		sb.append( Math.max(maxArr[idx][0], Math.max(maxArr[idx][1], maxArr[idx][2])) ).append(' ');
		sb.append( Math.min(minArr[idx][0], Math.min(minArr[idx][1], minArr[idx][2])) );
		
		System.out.print(sb.toString());
	}

	private static void minCalc(int idx) {
		minArr[idx][0] += Math.min(minArr[idx^1][0], minArr[idx^1][1]);
		minArr[idx][1] += Math.min(minArr[idx^1][0], Math.min(minArr[idx^1][1], minArr[idx^1][2]));
		minArr[idx][2] += Math.min(minArr[idx^1][1], minArr[idx^1][2]);
	}

	private static void maxCalc(int idx) {
		maxArr[idx][0] += Math.max(maxArr[idx^1][0], maxArr[idx^1][1]);
		maxArr[idx][1] += Math.max(maxArr[idx^1][0], Math.max(maxArr[idx^1][1], maxArr[idx^1][2]));
		maxArr[idx][2] += Math.max(maxArr[idx^1][1], maxArr[idx^1][2]);
	}

	private static void input(int idx) throws IOException {
		String str = br.readLine();
		
		maxArr[idx][0] = str.charAt(0) - '0';
		maxArr[idx][1] = str.charAt(2) - '0';
		maxArr[idx][2] = str.charAt(4) - '0';
		
		minArr[idx][0] = str.charAt(0) - '0';
		minArr[idx][1] = str.charAt(2) - '0';
		minArr[idx][2] = str.charAt(4) - '0';
	}
}
