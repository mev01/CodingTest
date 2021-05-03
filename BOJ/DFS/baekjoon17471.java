import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon17471 {
	static int N, districtNum, ans = Integer.MAX_VALUE;
	static int[] population;
	static ArrayList<Integer>[] connectedSection;
	static ArrayList<Integer> district;
	static boolean[] selected, visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		connectedSection = new ArrayList[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			connectedSection[i] = new ArrayList<>();
			for (int j = 0; j < num; j++) {
				connectedSection[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 조합으로 한 선거구가 1, ..., N/2 의 구역을 가질 때를 각각 계산
		for (int i = 1; i <= N/2; i++) {
			selected = new boolean[N + 1];
			districtNum = i;
			
			Combination(1, 0);
		}
		
		System.out.print(ans == Integer.MAX_VALUE ? -1 : ans );
	}

	private static void Combination(int start, int cnt) {
		if(districtNum == cnt) {
			int popDiffer;
			
			district = new ArrayList<>();
			// 고른 구역을 선거구에 넣기
			for (int i = 1; i <= N; i++) {
				if(selected[i]) {
					district.add(i);
				}
			}
			visited = new boolean[N + 1];
			// 선거구가 모두 연결되어 있는지
			if(!IsConnected()) return;
			popDiffer = calcPopulation();
			
			district = new ArrayList<>();
			// 고르지 않은 구역을 선거구에 넣기
			for (int i = 1; i <= N; i++) {
				if(!selected[i]) {
					district.add(i);
				}
			}
			visited = new boolean[N + 1];
			// 선거구가 모두 연결되어 있는지
			if(!IsConnected()) return;
			popDiffer = Math.abs(popDiffer - calcPopulation());
			
			// 둘다 연결되어 있다면 인구수의 차이 계산
			ans = Math.min(ans, popDiffer);
			
			return;
		}
		for (int i = start; i <= N; i++) {
			if(!selected[i]) {
				selected[i] = true;
				Combination(i + 1, cnt + 1);
				selected[i] = false;
			}
		}
	}

	private static int calcPopulation() {
		int num = 0;
		for (int i = 0; i < district.size(); i++) {
			num += population[district.get(i)];
		}
		return num;
	}

	private static boolean IsConnected() {
		// district의 첫 번째 원소를 dfs방식으로 탐색
		DFS(district.get(0));
		
		// district 중  방문하지 않았다면 false
		for (int i = 0; i < district.size(); i++) {
			if(!visited[district.get(i)]) {
				return false;
			}
		}
		
		return true;
	}

	private static void DFS(int num) {
		visited[num] = true;
		
		for (int i = 0; i < connectedSection[num].size(); i++) {
			// DFS로 방문하지 않고 selected[num]과 같은 구역
			if(!visited[connectedSection[num].get(i)] && selected[num] == selected[connectedSection[num].get(i)]) {
				DFS(connectedSection[num].get(i));
			}
		}
	}
}
