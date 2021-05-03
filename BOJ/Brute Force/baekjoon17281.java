import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17281 {
	static int N, ans = 0;
	static int[] seq = new int[10];
	static int[][] result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		//각 이닝별 선수의 결과
		result = new int[N+1][10];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		seq[4] = 1;
		//선수들의 순서를 순열로
		makeSeq(1, 0);
		
		System.out.print(ans);
	}

	private static void makeSeq(int cnt, int flag) {
		if(cnt == 10){
			ans = Math.max(ans, playGame());
			return;
		}
		if(cnt == 4) cnt++;
		for (int i = 2; i <= 9; i++) {
			if((flag & 1<<i) == 0){
				seq[cnt] = i;
				makeSeq(cnt+1, flag | 1<<i);
			}
		}
	}

	private static int playGame() {
		int score = 0, sequence = 1;
		//각 이닝별 계산
		for (int en = 1; en <= N; en++) {
			int out = 0;
			int[] ground = new int[4];
			while(out < 3){
				int res = result[en][seq[sequence]];
				
				if(res == 0) out++;
				// res가 아웃이 아닌경우
				else score += movePlayer(res, ground);
				
				if(++sequence == 10) sequence = 1;
			}
		}
		return score;
	}

	private static int movePlayer(int res, int[] ground) {
		int score = 0;
		ground[0] = 1;
		//각 선수들을 옮기면서 점수 계산
		for (int i = 3; i >=0; i--) {
			if(ground[i] == 1){
				if(i + res >= 4){
					score++;
					ground[i] = 0;
				}
				else{
					ground[i] = 0;
					ground[i + res] = 1;
				}
			}
		}
		
		return score;
	}
}
