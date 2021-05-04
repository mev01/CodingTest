import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA1861 {
	static int[][] map = new int[1000][1000];
	static int[] locX = new int[1000001], locY = new int[1000001], ans = new int[1000001];
	static int[] dirX = new int[]{-1, 1, 0, 0}, dirY = new int[]{0, 0, -1, 1};
	static int size = 0, ansRoom = 0, ansNum = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			ansRoom = 0; ansNum = 0;
			int tempRoom = 0;
			
			//입력
			size = Integer.parseInt(br.readLine());
			for (int x = 0; x < size; x++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int y = 0; y < size; y++) {
					map[x][y] = Integer.parseInt(st.nextToken());
					//방번호 순으로 위치를 저장
					locX[map[x][y]] = x;
					locY[map[x][y]] = y;
				}
			}
			
			//방번호가 1인 방부터 점검
			//방을 검사해서 지금 답보다 더 큰 수가 나올수 없는 경우는 제외
			ans[0]=1;
			for (int j = 1; j <= size*size; j++) {
				//방 번호가 j인 방의 위치를 가져옴
				int x = locX[j], y = locY[j];
				//전 방 번호에서 방 이동한 수를 가져와서 1이었다면 
				//새로운 루틴을 시작해야하므로 tempRoom에 j값 저장
				if(ans[j-1] == 1) tempRoom = j;
				ans[j] = 1;
				
				for (int k = 0; k < 4; k++) {
					if(x+dirX[k]>=0 && x+dirX[k]<size && y+dirY[k]>=0 && y+dirY[k]<size) {	//범위 체크
						if(map[x+dirX[k]][y+dirY[k]] == map[x][y]+1) {	//지금 수보다 1 큰지 체크
							ans[j] = ans[j-1] + 1;
						}
					}
				}
				//이동할 수 없는 방이 없다면 루틴을 종료
				//이전 루틴을 비교해서 답이 더 크다면 ansNum에 저장
				if(ans[j]==1) {
					if(ans[j-1]>ansNum) {
						ansNum = ans[j-1];
						ansRoom = tempRoom;
					}
				}
			}
			sb.append("#"+i+" "+ansRoom+" "+ansNum+"\n");
		}
		System.out.println(sb.toString());
	}
}
