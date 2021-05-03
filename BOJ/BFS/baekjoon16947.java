import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon16947 {
	static int[] parent, ans;
	static boolean[] visited;
	static ArrayList<Integer>[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		ans = new int[N + 1];
		visited = new boolean[N + 1];
		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			arr[node1].add(node2);
			arr[node2].add(node1);
		}
		
		for (int i = 1; i <= N; i++) {
			// size가 1인 노드(지선의 마지막)을 찾아서
			// 자신의 부모 표시
			// 해당 노드가 연결 가능한 노드가 1이면 계속 부모를 표시하면서 탐색
			if(arr[i].size() == 1) {
				findLoopLine(i);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(parent[i] != 0 && ans[i] == 0) {
				ans[i] = findDis(i);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			sb.append(ans[i]).append(' ');
		}
		
		System.out.print(sb.toString());
	}

	private static int findDis(int i) {
		if(parent[i] == 0 || ans[i] != 0) return ans[i];
		ans[i] = findDis(parent[i]) + 1;
		
		return ans[i];
	}

	private static void findLoopLine(int idx) {
		// 연결 가능한 노드가 2개이상이면 탐색 중지
		int size = 0;
		for (int i = 0; i < arr[idx].size(); i++) {
			if(!visited[arr[idx].get(i)]) size++;
		}
		if(size >= 2) return;
		visited[idx] = true;
		
		// size가 3이상인 노드(지선과 순환선이 맞닿는 노드) 탐색
		for (int i = 0; i < arr[idx].size(); i++) {
			if(!visited[arr[idx].get(i)]) {
				parent[idx] = arr[idx].get(i);
				findLoopLine(arr[idx].get(i));
			}
		}
		
	}
}
