import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EA2112 {
	static int D, W, K, ans;
	static int[] film, drug;
	static int[][] map, copyMap;
	static boolean[] passEachTest;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			copyMap = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = copyMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			// 성능검사 실행
			if(isPassTest(map)) ans = 0;
			// 실패하면 약물 투입
			else injectDrug(0, 0);
			
			sb.append("#"+tc+" "+ans+'\n');
		}
		System.out.print(sb.toString());
	}

	private static void injectDrug(int idx, int cnt) {
		if(cnt >= ans) return;
		if(idx == D) {
			if(isPassTest(copyMap)) {
				ans = cnt;
			}
			
			return;
		}
		
		// 이 막에 약물 투입 안했을 때
		injectDrug(idx + 1, cnt);
		
		// 이 막에 0 약물 투입했을 때
		Arrays.fill(copyMap[idx], 0);
		injectDrug(idx + 1, cnt + 1);
		
		// 이 막에 1 약물 투입했을 때
		Arrays.fill(copyMap[idx], 1);
		injectDrug(idx + 1, cnt + 1);
		
		for (int i = 0; i < W; i++) {
			copyMap[idx][i] = map[idx][i];
		}
	}

	private static boolean isPassTest(int[][] map) {
		passEachTest = new boolean[W];
		
		for (int j = 0; j < W; j++) {
			int cnt = 0;
			for (int i = 0; i < D-1; i++) {
				if(map[i][j] == map[i+1][j]) cnt++;
				else cnt = 0;
				
				if(cnt == K - 1) {
					passEachTest[j] = true;
					break;
				}
			}
			
			if(!passEachTest[j]) return false;
		}
		
		return true;
	}
}
