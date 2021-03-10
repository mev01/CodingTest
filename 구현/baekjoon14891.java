import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14891 {
	static int ans = 0;
	static int[][] wheel = new int[4][8];	//톱니바퀴의 극을 저장
	static int[][] contactIdx = { {6 , 2}, {6 , 2}, {6 , 2}, {6 , 2} };	//톱니바퀴의 왼쪽 맞닿는 인덱스, 오른쪽 맞닿는 인덱스
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = str.charAt(j) - '0';
			}
		}
		
		int rotNum = Integer.parseInt(br.readLine());
		for (int i = 0; i < rotNum; i++) {
			st = new StringTokenizer(br.readLine());
			int numWheel = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			Rotation(numWheel-1, dir, -1);
		}
		
		for (int i = 0; i < 4; i++) {
			if(wheel[i][(contactIdx[i][0] + 2) % 8] == 1)
				ans = ans | 1<<i;
		}
		
		System.out.print(ans);
	}
	private static void Rotation(int numWheel, int dir, int preNum) {
		if(numWheel == 0) {	//1번째 톱니바퀴를 돌릴 때
			if(wheel[numWheel][contactIdx[numWheel][1]] != wheel[numWheel+1][contactIdx[numWheel+1][0]] && numWheel+1 != preNum) {
				Rotation(numWheel + 1, 0 - dir, numWheel);
			}
		}
		else if(numWheel == 3) {	//4번째 톱니바퀴를 돌릴 때
			if(wheel[numWheel][contactIdx[numWheel][0]] != wheel[numWheel-1][contactIdx[numWheel-1][1]] && numWheel-1 != preNum) {
				Rotation(numWheel - 1, 0 - dir, numWheel);
			}
		}
		else {	//2, 3번째 톱니바퀴를 돌릴 때
			//현재 톱니바퀴의 맞닿는 왼쪽 부분과 왼쪽 톱니바퀴의 맞닿는 오른쪽 부분이 같지 않고
			//그전의 톱니바퀴가 왼쪽 톱니바퀴가 아닐 때
			if(wheel[numWheel][contactIdx[numWheel][0]] != wheel[numWheel-1][contactIdx[numWheel-1][1]] && numWheel-1 != preNum) {
				Rotation(numWheel - 1, 0 - dir, numWheel);
			}
			//현재 톱니바퀴의 맞닿는 오른쪽 부분과 오른쪽 톱니바퀴 맞닿는 왼쪽 부분이 같지 않고
			//그전의 톱니바퀴가 오른쪽 톱니바퀴가 아닐 때
			if(wheel[numWheel][contactIdx[numWheel][1]] != wheel[numWheel+1][contactIdx[numWheel+1][0]] && numWheel+1 != preNum) {
				Rotation(numWheel + 1, 0 - dir, numWheel);
			}
		}
		
		contactIdx[numWheel][0] -= dir;
		contactIdx[numWheel][1] -= dir;
		
		if(contactIdx[numWheel][1] == -1) contactIdx[numWheel][1] = 7;
		else if(contactIdx[numWheel][1] == 8) contactIdx[numWheel][1] = 0;
		if(contactIdx[numWheel][0] == -1) contactIdx[numWheel][0] = 7;
		else if(contactIdx[numWheel][0] == 8) contactIdx[numWheel][0] = 0;
	}
}
