import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class land{
	ArrayList<Pair> list = new ArrayList<>();
}
class Pair implements Comparable<Pair>{
	Integer first;
	Integer second;
	public Pair(Integer connectedNumLand, Integer lenBridge) {
		this.first = connectedNumLand;
		this.second = lenBridge;
	}
	public Integer first() {
		return first;
	}
	public Integer second() {
		return second;
	}
	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return this.second - o.second;
	}
}
public class baekjoon17472 {
	static int N, M, totalLand = 0, ans = 0;
	static int[] disx = {-1, 0, 1, 0}, disy = {0, 1, 0, -1};
	static int[][] map;
	static boolean[] visited = new boolean[7];
	static land[] landArr = new land[7];
	static PriorityQueue<Pair> heap = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][M+2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				//바다: -2, 땅: -1
				map[i][j] = Integer.parseInt(st.nextToken()) - 2;
			}
		}
		//번호를 매길 수 있는 땅 찾기
		numberingLand();
		//다리를 짓기 위해 섬의 땅 조사
		findLand();
		//다리 길이가 짧은 순으로 정렬
		for (int i = 1; i <= totalLand; i++) {
			Collections.sort(landArr[i].list);
		}
		//prim 알고리즘으로 최소 길이 만들기
		Prim(1);
		
		boolean possible = true;
		for (int i = 1; i <= totalLand; i++) {
			if(!visited[i]) {
				possible = false;
			}
		}
		
		System.out.print(possible ? ans : -1);
	}
	private static void Prim(int num) {
		visited[num] = true;
		
		for (int i = 0; i < landArr[num].list.size(); i++) {
			heap.offer(landArr[num].list.get(i));
		}
		
		while(!heap.isEmpty()) {
			Pair pair = heap.poll();
			if(!visited[pair.first]) {
				ans += pair.second;
				Prim(pair.first);
			}
		}
	}
	private static void findLand() {
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= M; y++) {
				if(map[x][y] >= 1) {	//섬인지
					for (int dir = 0; dir < 4; dir++) {
						if(map[x + disx[dir]][y + disy[dir]] == -2) {	//근처에 다리를 지을 바다가 있는지
							buildBridge(x + disx[dir], y + disy[dir], dir, 1, map[x][y]);
						}
					}
				}
			}
		}
	}
	private static void buildBridge(int x, int y, int dir, int bridgelen, int landNum) {
		if(x >= 1 && x <= N && y >= 1 && y <= M && map[x + disx[dir]][y + disy[dir]] == -2 ) {
			buildBridge(x + disx[dir], y + disy[dir], dir, bridgelen+1, landNum);
		}
		else {
			if(bridgelen >= 2 && map[x + disx[dir]][y + disy[dir]] >= 1) {	//다리 길이가 2이상이고 다음 땅이 섬일 때
				landArr[landNum].list.add(new Pair(map[x + disx[dir]][y + disy[dir]], bridgelen));
			}
		}
	}
	private static void numberingLand() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j] == -1) {
					totalLand++;
					landArr[totalLand] = new land();
					//근처에 있는 땅들을 묶어 섬으로 만들기
					makeIsland(i, j);
				}
			}
		}
	}
	private static void makeIsland(int x, int y) {
		map[x][y] = totalLand;
		for (int i = 0; i < 4; i++) {
			if(map[x + disx[i]][y + disy[i]] == -1) {
				makeIsland(x + disx[i], y + disy[i]);
			}
		}
	}
}
