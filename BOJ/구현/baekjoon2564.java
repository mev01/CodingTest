import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2564 {
	static int W, H, mainPos, circum, ans = 0;
	static int[] storePos;
	static int[] storeToInt = new int[5];
	
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		circum = (W + H) * 2; 					// 둘레
		storeToInt[1] = 0;
		storeToInt[2] = circum - H;
		storeToInt[3] = circum;
		storeToInt[4] = W;
		
		int N = Integer.parseInt(br.readLine());
		storePos = new int[N];
		
		for (int i = 0; i < N; i++) {
			storePos[i] = getPosition();
		}
		mainPos = getPosition();
		
		for (int i = 0; i < N; i++) {
			ans += getShortestDis(i);
		}
		
		System.out.print(ans);
	}

	private static int getShortestDis(int idx) {
		if(storePos[idx] > mainPos) {
			return Math.min(storePos[idx] - mainPos, circum - storePos[idx] + mainPos);
		}
		else {
			return Math.min(mainPos - storePos[idx], circum - mainPos + storePos[idx] );
		}
	}

	private static int getPosition() throws IOException {
		st = new StringTokenizer(br.readLine());
		int dir = Integer.parseInt(st.nextToken());
		int dis = Integer.parseInt(st.nextToken());
		
		if(dir == 2 || dir == 3) {
			return storeToInt[dir] - dis;
		}
		else {
			return storeToInt[dir] + dis;
		}
	}
}
