import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Section{
	int pop;
	int election = 1;
	ArrayList<Integer> nearNode = new ArrayList<>();
	
	public Section(int popul) {
		this.pop = popul;
	}
}
public class baekjoon17471 {
	static int N, totalPop = 0, ans = Integer.MAX_VALUE;
	static boolean[] visit;
	static Section[] sectArr;
	static Queue<Integer> que;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		sectArr = new Section[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			sectArr[i] = new Section(Integer.parseInt(st.nextToken()));
			totalPop += sectArr[i].pop;
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			
			while(st.hasMoreTokens()) {
				sectArr[i].nearNode.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		subset();
		System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void subset() {
		//0-5 까지 부분집합 생성
		for (int i = 1; i < Math.pow(2, N)-1; i++) {
			for (int j = 0; j < N; j++) {
				if((i & 1<<j) != 0) {
					//부분 집합에 해당된 구역의 지역구 변경
					sectArr[j+1].election = 2;
				}
			}
			//각각의 지역구가 연결되어 있는지 점검
			visit = new boolean[N+1];
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if(!visit[j]) {
					cnt++;
					if(cnt <= 2) dfs(j);
				}
			}
			
			//dfs함수를 몇번 돌았는지 체크
			//2번 돌지 않았다면 불가능한 지역구
			if(cnt == 2) {
				int temp = 0;
				for (int j = 0; j < N; j++) {
					if((i & 1<<j) != 0) {
						temp += sectArr[j+1].pop;
					}
				}
				ans = Math.min(ans, (int)(Math.abs(temp - (totalPop - temp) ) ) );
			}
			
			//구역 다시 원상태로
			for (int j = 0; j < N; j++) {
				if((i & 1<<j) != 0) {
					//구역 변경
					sectArr[j+1].election = 1;
				}
			}
		}
	}

	private static void dfs(int preSecNum) {
		visit[preSecNum] = true;
		
		for (int i = 0; i < sectArr[preSecNum].nearNode.size(); i++) {
			int nextSecNum = sectArr[preSecNum].nearNode.get(i);
			//해당 구역을 방문하지 않았고
			//해당 구역이 지역구가 같을 때
			if(!visit[nextSecNum] && sectArr[preSecNum].election == sectArr[nextSecNum].election) {
				dfs(nextSecNum);
			}
		}
	}
}
