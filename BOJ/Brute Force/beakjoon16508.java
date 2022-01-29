import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon16508 {
	static int N, ans = Integer.MAX_VALUE;
	static String T;
	static int[] price;
	static int[][] numOfAlpha;
	static int[] selectedBook;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = br.readLine();
		N = Integer.parseInt(br.readLine());
		
		price = new int[N];
		numOfAlpha = new int[N][26];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			price[i] = Integer.parseInt(st.nextToken());
			char[] name = st.nextToken().toCharArray();
			
			for(char ch : name) {
				numOfAlpha[i][ch - 'A']++;
			}
		}
		
		selectedBook = new int[N];
		Search(0, 0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void Search(int idx, int pPrice) {
		if(idx == T.length()) {
			ans = Math.min(ans, pPrice);
			return;
		}
		
		char ch = T.charAt(idx);
		for(int i = 0; i < N; i++) {
			if(numOfAlpha[i][ch - 'A'] > 0) {
				int nPrice = pPrice;
				if(selectedBook[i] == 0) nPrice += price[i];
				if(nPrice >= ans) continue;
				
				numOfAlpha[i][ch - 'A']--;
				selectedBook[i]++;
				
				Search(idx + 1, nPrice);
				
				numOfAlpha[i][ch - 'A']++;
				selectedBook[i]--;
			}
		}
	}

}
