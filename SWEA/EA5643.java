import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class EA5643 {
	static int N, M, ans = 0;
	static Key[] keyArr;
	static boolean[] visited;
	static Queue<Integer> que;
	
	static class Key{
		int num, ancesNum, descNum;
		ArrayList<Integer> ancesList;
		ArrayList<Integer> descList;
		
		public Key(int num) {
			this.num = num;
			ancesList = new ArrayList<>();
			descList = new ArrayList<>();
		}
		
		public void addAnces(int i) {
			this.ancesList.add(i);
		}
		
		public void addDesc(int i) {
			this.descList.add(i);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			keyArr = new Key[N + 1];
			for (int i = 1; i <= N; i++) {
				keyArr[i] = new Key(i);
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				keyArr[a].addAnces(b);
				keyArr[b].addDesc(a);
			}
			
			for (int i = 1; i <= N; i++) {
				BFS_down(i);
				BFS_up(i);
			}
			
			ans = 0;
			for (int i = 1; i <= N; i++) {
				if(keyArr[i].ancesNum + keyArr[i].descNum == N - 1) ans++;
			}
			
			sb.append("#"+tc+" "+ans+'\n');
		}
		System.out.print(sb.toString());
	}

	private static void BFS_up(int idx) {
		visited = new boolean[N + 1];
		que = new LinkedList<Integer>();
		
		que.offer(idx);
		
		while(!que.isEmpty()) {
			int num = que.poll();
			
			for(int i : keyArr[num].descList) {
				if(!visited[i]) {
					que.offer(i);
					visited[i] = true;
					keyArr[i].descNum++;
				}
			}
		}
	}

	private static void BFS_down(int idx) {
		visited = new boolean[N + 1];
		que = new LinkedList<Integer>();
		
		que.offer(idx);
		
		while(!que.isEmpty()) {
			int num = que.poll();
			
			for(int i : keyArr[num].ancesList) {
				if(!visited[i]) {
					que.offer(i);
					visited[i] = true;
					keyArr[i].ancesNum++;
				}
			}
		}
	}
}
