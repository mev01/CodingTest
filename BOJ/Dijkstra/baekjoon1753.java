import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Route{
	int ver, wei;

	public Route(int v, int w) {
		this.ver = v;
		this.wei = w;
	}
}
public class baekjoon1753 {
	static int V, E, start;
	static int[] D;
	static ArrayList<Route>[] routeArr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		routeArr = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			routeArr[i] = new ArrayList<Route>();
		}
		start = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			routeArr[u].add(new Route(v, w));
		}
		
		D = new int[V+1];
		Arrays.fill(D, Integer.MAX_VALUE - 10);
		visited = new boolean[V+1];
		
		D[start] = 0;
		for (int i = 1; i <= V; i++) {
			//가장 작은 거 찾기
			int idx = 1;
			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= V; j++) {
				if(!visited[j] && D[j] < min) {
					idx = j;
					min = D[j];
				}
			}
			visited[idx] = true;
			
			//갱신
			for (int j = 0; j < routeArr[idx].size(); j++) {
				if(!visited[routeArr[idx].get(j).ver]) {
					D[routeArr[idx].get(j).ver] = Math.min(D[routeArr[idx].get(j).ver], D[idx] + routeArr[idx].get(j).wei);
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			sb.append((D[i]==Integer.MAX_VALUE - 10) ? "INF": D[i]).append('\n');
		}
		System.out.println(sb.toString());
	}

}
