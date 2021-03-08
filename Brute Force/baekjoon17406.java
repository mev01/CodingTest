import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//회전 연산에 관한 정보를 저장
class rotInfo{
	int r, c, s;

	public rotInfo(int r, int c, int s) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
	}
}
public class baekjoon17406 {
	static int N, M, K, ans = Integer.MAX_VALUE;
	static int[] seq, disX = {0, 1, 0, -1}, disY = {1, 0, -1, 0};
	static int[][] arr, resArr;
	static rotInfo[] rotArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		seq = new int[K];
		rotArr = new rotInfo[K];
		//입력 배열
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//회전 연산 저장
		int r, c, s;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			rotArr[i] = new rotInfo(r, c, s);
		}
		//순열
		permutation(0, 0);
		System.out.print(ans);
	}

	private static void permutation(int cnt, int flag) {
		if(cnt == K) {
			Rotate();
			ans = Math.min(ans, getAns());
			return;
		}
		for (int i = 0; i < K; i++) {
			if((flag & 1<<i) == 0) {
				seq[cnt] = i;
				permutation(cnt+1, flag | 1<<i);
			}
		}
	}
	//배열 돌리기
	private static void Rotate() {
		int r, c, s;
		
		resArr = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				resArr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < K; i++) {
			r = rotArr[seq[i]].r;
			c = rotArr[seq[i]].c;
			s = rotArr[seq[i]].s;
			
			//안쪽 회전부터 연산
			for (int rn = 1; rn <= s; rn++) {
				int x = r - rn;
				int y = c - rn;
				int ncon = 0, pcon = 0;
				
				//상, 우, 하, 좌 순으로 시계방향으로 돌리기
				for (int dis = 0; dis < 4; dis++) {
					int temp = 0;
					while(temp++ < 2 * rn) {
						x += disX[dis];
						y += disY[dis];
						
						if(dis == 0 && temp == 1) ncon = resArr[x - disX[dis]][y - disY[dis]];
						pcon = ncon;
						//다음 옮길 값을 미리 저장
						ncon = resArr[x][y];
						//이번에 옮길 값을 저장
						resArr[x][y] = pcon;
					}
				}
			}
		}
	}

	private static int getAns() {
		int ans = Integer.MAX_VALUE;
		
		for (int i = 1; i <= N; i++) {
			int temp = 0;
			for (int j = 1; j <= M; j++) {
				temp += resArr[i][j];
			}
			ans = Math.min(ans, temp);
		}
		return ans;
	}
}
