import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class AP implements Comparable<AP>{
	int x, y, C, P;

	public AP(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		C = c;
		P = p;
	}

	@Override
	public int compareTo(AP o) {
		// TODO Auto-generated method stub
		return o.P - this.P;
	}
}

public class EA5644 {
	static int M, numAP, ans = 0;
	static int[] useAP;
	static int[] AmoveInfo, BmoveInfo;
	static int[] dirx = {0, 0, 1, 0, -1}, diry = {0, -1, 0, 1, 0};
	static AP[] APInfo;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			numAP = Integer.parseInt(st.nextToken());
			ans = 0;
			
			AmoveInfo = new int[M+1];
			BmoveInfo = new int[M+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				AmoveInfo[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				BmoveInfo[i] = Integer.parseInt(st.nextToken());
			}
			
			APInfo = new AP[numAP];
			for (int i = 0; i < APInfo.length; i++) {
				st = new StringTokenizer(br.readLine());
				APInfo[i] = new AP(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(APInfo);
			
			movePerson();
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.print(sb.toString());
	}

	/**
	 * 매 초마다 사람의 위치 변경
	 */
	private static void movePerson() {
		int Ax = 1, Ay = 1, Bx = 10, By = 10;
		
		for (int i = 0; i <= M; i++) {
			// 사람의 위치 설정
			Ax = Ax + dirx[AmoveInfo[i]];
			Ay = Ay + diry[AmoveInfo[i]];
			Bx = Bx + dirx[BmoveInfo[i]];
			By = By + diry[BmoveInfo[i]];
						
			useAP = new int[numAP];
			examAP(Ax, Ay, 1);
			examAP(Bx, By, 2);
			
//			useAP[0] = 2;
//			useAP[1] = 2;
//			useAP[2] = 2;
			
			// 충전한 양 계산
			calcPerf();
		}
		
	}

	/**
	 * useAP 배열을 확인 해서 쓸수 있는 충전 값중 가장 좋은 값들을 ans에 더하기
	 */
	private static void calcPerf() {
		int temp = 0, num = 0;
		for (int i = 0; i < numAP; i++) {
			if(useAP[i] != 0 && useAP[i] != temp && num < 2) {
				ans += APInfo[i].P;
				num++;
				if(useAP[i] != 3) temp = useAP[i];
			}
		}
	}

	/**
	 * 해당 좌표에서 사용가능 한 AP를 useAP 배열에 체크
	 * @param x
	 * @param y
	 * @return 사용가능한 AP 인덱스 리스트
	 */
	private static void examAP(int x, int y, int num) {
		for (int i = 0; i < numAP; i++) {
			int dist = Math.abs(x - APInfo[i].x) + Math.abs(y - APInfo[i].y);
			
			if(dist <= APInfo[i].C) {
				useAP[i] += num;
			}
		}
		
	}
}
