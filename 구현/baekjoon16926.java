import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon16926 {
	static int N, M, R;
	static int[][] map, ans;
	
	static int nextStepX(int x, int y, int num, int step) {	//step 번째 이후의 x계산
		for (int j = 0; j < step; j++) {
			if(y == num && x != N-1-num) ++x;
			else if(y == M-1-num && x != num) --x;
			else if(x == num && y != num) --y;
			else if(x == N-1-num && y != M-1-num) ++y;
		}
		
		return x;
	}
	static int nextStepY(int x, int y, int num, int step) {	//step 번째 이후의 y계산
		for (int j = 0; j < step; j++) {
			if(y == num && x != N-1-num) ++x;
			else if(y == M-1-num && x != num) --x;
			else if(x == num && y != num) --y;
			else if(x == N-1-num && y != M-1-num) ++y;
		}
		
		return y;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ans = new int[N][M];
		//입력
		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < M; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		//각 사이클마다 계산
		for (int i = 0; i < Math.min(N, M)/2; i++) {
			int numDot = 2*((N-2*i)+(M-2*i))-4;	//사이클의 원소 개수
			int numRot = (R-1) % numDot; //사이클이 몇번 회전해야하는지
			int x = i, y = i, cnt = 1;
			int nextX = nextStepX(x, y, i, numRot), nextY =  nextStepY(x, y, i, numRot);	//(i,i)가 마지막에 위치하는 좌표
			
			while(cnt++ <= numDot) {
				int tempX, tempY;
				
				//x, y가 R번 회전했을 경우 x, y위치를 nextX, nextY에 저장
				tempX = nextX;
				tempY = nextY;
				nextX = nextStepX(tempX, tempY, i, 1);
				nextY =  nextStepY(tempX, tempY, i, 1);
				ans[nextX][nextY] = map[x][y];
				
				//다음 계산할 x, y 구하기
				tempX = x;
				tempY = y;
				x = nextStepX(tempX, tempY, i, 1);
				y =  nextStepY(tempX, tempY, i, 1);
			}
		}
		//출력
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				sb.append(ans[x][y]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}