import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14499 {
	static int N, M, K;
	static int[] inst;
	static int[] disx = {0, 0, 0, -1, 1}, disy = {0, 1, -1, 0, 0};
	static int[] dice = {0, 0, 0, 0, 0, 0};
	static int[][] diceIdx = {{0, 1, 2, 3, 4, 5}, 
			{1, 5, 0, 3, 4, 2}, 
			{2, 0, 5, 3, 4, 1}, 
			{3, 1, 2, 5, 0, 4}, 
			{4, 1, 2, 0, 5, 3}, 
			{5, 1, 2, 4, 3, 0}};
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		String str;
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(2 * j) - '0';
			}
		}
		
		inst = new int[K];
		str = br.readLine();
		for (int i = 0; i < K; i++) {
			inst[i] = str.charAt(2 * i) - '0';
		}
		
		//주사위의 각 면 배열, 각면은 0
		for (int i = 0; i < K; i++) {
			//주사위가 바깥으로 구를 때 명령 무시
			if(isRangeOut(x + disx[inst[i]], y + disy[inst[i]])) continue;
			//굴리기
			x += disx[inst[i]];
			y += disy[inst[i]];
			changeDice(inst[i]);
			//해당 칸이 0이 아니면 주사위 바닥면 = 칸, 칸 = 0
			if(map[x][y] != 0){
				dice[0] = map[x][y];
				map[x][y] = 0;
			}
			//굴려서 해당 칸이 0이면 칸 = 주사위
			else{
				map[x][y] = dice[0];
			}
			
			//주사위가 이동했을 떄 상단에 쓰여있는 값 출력
			//sb.append(dice[diceIdx[diceX][diceY + 5]]).append('\n');
			System.out.println(dice[5]);
		}
		
		System.out.print(sb.toString());
	}

	private static void changeDice(int i) {
		int[] temp = new int[6];
		
		switch (i) {
		case 1:	// 오른쪽
			//3, 4는 그대로
			temp[3] = dice[3];
			temp[4] = dice[4];
			
			temp[2] = dice[0];
			temp[0] = dice[1];
			temp[1] = dice[5];
			temp[5] = dice[2];
			break;
		case 2:	// 왼쪽
			//3, 4는 그대로
			temp[3] = dice[3];
			temp[4] = dice[4];
			
			temp[1] = dice[0];
			temp[5] = dice[1];
			temp[2] = dice[5];
			temp[0] = dice[2];
			break;
		case 3:	// 위쪽
			//1, 2는 그대로
			temp[1] = dice[1];
			temp[2] = dice[2];
			
			temp[5] = dice[3];
			temp[3] = dice[0];
			temp[0] = dice[4];
			temp[4] = dice[5];
			break;
		case 4:	// 아래쪽
			//1, 2는 그대로
			temp[1] = dice[1];
			temp[2] = dice[2];
			
			temp[0] = dice[3];
			temp[4] = dice[0];
			temp[5] = dice[4];
			temp[3] = dice[5];
			break;
		}
		
		dice = temp;
	}

	private static boolean isRangeOut(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) return true;
		return false;
	}
}
