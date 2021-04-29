import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon15997 {
	static String[] countryName = new String[4];
	static Pair[] teamRank;
	static float[] wholeResult = new float[5], ans = new float[4];
	static float[][] gameInfoArr = new float[6][5], gameResultArr = new float[6][5];
	
	static class Pair implements Comparable<Pair>{
		int a, b;
		
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Pair o) {
			return o.b - this.b;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			countryName[i] = st.nextToken();
		}
		
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			
			gameInfoArr[i][0] = findCountry(st.nextToken());
			gameInfoArr[i][1] = findCountry(st.nextToken());
			gameInfoArr[i][2] = Float.parseFloat(st.nextToken());
			gameInfoArr[i][3] = Float.parseFloat(st.nextToken());
			gameInfoArr[i][4] = Float.parseFloat(st.nextToken());
		}
		
		playGame(0);
		
		for (int i = 0; i < 4; i++) {
			sb.append(ans[i]).append('\n');
		}
		System.out.print(sb.toString());
	}

	private static float findCountry(String nextToken) {
		for (int i = 0; i < 4; i++) {
			if(countryName[i].equals(nextToken))
				return i;
		}
		return 0;
	}

	private static void playGame(int idx) {
		if(idx == 6) {
			calcResult();
			calcTeamRank();
			return;
		}
		
		for (int i = 2; i <= 4; i++) {
			if(gameInfoArr[idx][i] != 0) {
				chooseWinCount(idx, i);
				playGame(idx + 1);
			}
		}
	}

	private static void calcTeamRank() {
		teamRank = new Pair[4];
		for (int i = 0; i < 4; i++) {
			teamRank[i] = new Pair(i, (int) wholeResult[i]);
		}
		
		Arrays.sort(teamRank);
		
		// 1등 구하기
		int cnt = 0;
		for (int j = 1; j < 4; j++) {
			if(teamRank[0].b == teamRank[j].b) cnt++;
		}
		
		if(cnt == 0) {
			ans[teamRank[0].a] += wholeResult[4];
		}
		else {
			for (int i = 0; i <= cnt; i++) {
				ans[teamRank[i].a] += wholeResult[4] / (cnt + 1) * 2;
			}
			return;
		}
		
		// 2등 구하기
		cnt  = 0;
		for (int j = 2; j < 4; j++) {
			if(teamRank[1].b == teamRank[j].b) cnt++;
		}
		if(cnt == 0) {
			ans[teamRank[1].a] += wholeResult[4];
		}
		else {
			for (int i = 1; i <= cnt + 1; i++) {
				ans[teamRank[i].a] += wholeResult[4] / (cnt + 1);
			}
			return;
		}
	}

	private static void calcResult() {
		float temp;
		
		for (int j = 0; j < 4; j++) {
			temp = 0;
			for (int i = 0; i < 6; i++) {
				temp += gameResultArr[i][j];
			}
			wholeResult[j] = temp;
		}
		
		temp = 1;
		for (int i = 0; i < 6; i++) {
			temp *= gameResultArr[i][4];
		}
		wholeResult[4] = temp;
	}

	private static void chooseWinCount(int gameNum, int countNum) {
		switch (countNum) {
		case 2:
			gameResultArr[gameNum][(int) gameInfoArr[gameNum][0]] = 3;
			gameResultArr[gameNum][(int) gameInfoArr[gameNum][1]] = 0;
			gameResultArr[gameNum][4] = gameInfoArr[gameNum][countNum];
			break;
		case 3:
			gameResultArr[gameNum][(int) gameInfoArr[gameNum][0]] = 1;
			gameResultArr[gameNum][(int) gameInfoArr[gameNum][1]] = 1;
			gameResultArr[gameNum][4] = gameInfoArr[gameNum][countNum];
			break;
		case 4:
			gameResultArr[gameNum][(int) gameInfoArr[gameNum][0]] = 0;
			gameResultArr[gameNum][(int) gameInfoArr[gameNum][1]] = 3;
			gameResultArr[gameNum][4] = gameInfoArr[gameNum][countNum];
			break;
		}
	}
}
