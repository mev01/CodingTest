import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA5656 {
	static int N, W, H, min, total = 0, ans;
	static int[] shootNum;
	static int[] dirx = {-1, 0, 1, 0}, diry = {0, 1, 0, -1};
	static int[][] map, lastMap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			//입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			total = 0;
			
			map = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) total++;
				}
			}
			ans = total;
			//
			
			shootNum = new int[N];
			perm(0);
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.print(sb.toString());
	}

	/**
	 * 구슬을 쏠 위치의 순서를 순열로 정해서
	 * 벽돌 부수기 실행
	 */
	private static void perm(int cnt) {
		if(cnt == N) {
			int[][] tempMap = copyArr(map);
			min = 0;
			
			for (int i = 0; i < N; i++) {
				//첫번째 벽돌을 찾아서 해당 행값을 가져오고
				int firstRow = findFirstBrick(tempMap, shootNum[i]);
				if(firstRow != -1) {
					//map을 가지고 들어가서 부수고 map return
					tempMap = BreakBrick(tempMap, firstRow, shootNum[i]);
					//정리
					tempMap = arrange(tempMap);
				}
			}
			
			ans = Math.min(ans, total - min);
			return;
		}
		for (int i = 0; i < W; i++) {
			shootNum[cnt] = i;
			perm(cnt + 1);
		}
	}

	private static int[][] arrange(int[][] tempMap) {
		for (int col = 0; col < W; col++) {
			for (int row = H - 1; row >= 0; row--) {
				if (tempMap[row][col] == 0) {
					int index = row;
					
					for (int i = row; i >= 0; i--) {
						if(tempMap[i][col] != 0) {
							tempMap[index--][col] = tempMap[i][col];
							tempMap[i][col] = 0;
						}
					}
				}
			}
		}
		return tempMap;
	}

	/**
	 * 해당 인덱스의 벽돌을 깨서 폭발 일으키고 폭발에 반응하는 벽돌이 있으면 재귀 통해 들어가기
	 * @param tempMap 현재 상태 배열
	 * @param row 부술 벽돌의 행 값 
	 * @param col 부술 벽돌의 열 값 
	 * @return 
	 */
	private static int[][] BreakBrick(int[][] tempMap, int row, int col) {
		int bombRange = tempMap[row][col];
		tempMap[row][col] = 0;
		min++;
		
		for (int dir = 0; dir < 4; dir++) {
			for (int i = 1; i < bombRange; i++) {
				int nx = row + dirx[dir] * i;
				int ny = col + diry[dir] * i;
				
				if(nx >= 0 && nx < H && ny >= 0 && ny < W) {
					if(tempMap[nx][ny] >= 1) {
						tempMap = BreakBrick(tempMap, nx, ny);
					}
				}
				
			}
		}
		return tempMap;
	}

	/**
	 * 배열을 검사해서 col의 벽돌 중 제일 위에 있는 벽돌 찾기
	 * @param tempMap 현재 벽돌의 상태 2차원 배열
	 * @param col 구슬을 쏠 열 값
	 * @return 구슬이 맞는 벽돌의 행 값, 해당 열에 벽돌이 없으면 -1 리턴
	 */
	private static int findFirstBrick(int[][] tempMap, int col) {
		int row;
		for (row = 0; row < H; row++) {
			if(tempMap[row][col] >= 1) {
				return row;
			}
		}
		return -1;
	}

	/**
	 * 2차원 배열 복사
	 * @param map 복사할 대상
	 * @return 복사한 2차원 배열
	 */
	private static int[][] copyArr(int[][] map) {
		int[][] tempMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tempMap[i][j] = map[i][j];
			}
		}
		
		return tempMap;
	}
}
