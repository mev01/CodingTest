import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon16235 {
	static int N, M, K;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int[][] preNourishArr, addNourishArr;
	static Deque<Integer>[][] treeArr;
	static Queue<Tree> liveTree;
	
	static class Tree implements Comparable<Tree>{
		int r, c, age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		preNourishArr = new int[N + 1][N + 1];
		addNourishArr = new int[N + 1][N + 1];
		treeArr = new ArrayDeque[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				addNourishArr[i][j] = Integer.parseInt(st.nextToken());
				preNourishArr[i][j] = 5;
				treeArr[i][j] = new ArrayDeque<Integer>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			treeArr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < K; i++) {
			passFourSeasons();
		}
		
		System.out.println(countTree());
	}

	private static int countTree() {
		int cnt = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cnt += treeArr[i][j].size();
			}
		}
		
		return cnt;
	}

	private static void passFourSeasons() {
		passSpringDSummer();
		passAutumn();
		passWinter();
	}

	private static void passSpringDSummer() {
		liveTree = new LinkedList<Tree>();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(treeArr[i][j].isEmpty()) continue;
				
				int len = treeArr[i][j].size();
				int sum = 0;					// 죽은 나무들의 나이의 합
				for (int k = 0; k < len; k++) {
					int age = treeArr[i][j].pollFirst();
					
					// 해당 칸의 양분이 충분하면 liveTree로 아니면 deadTree로
					if(preNourishArr[i][j] >= age) {
						// 나무가 자기 나이만큼 영양분 흡수
						preNourishArr[i][j] -= age;
						
						treeArr[i][j].offerLast(age + 1);
						
						if((age + 1) % 5 == 0) liveTree.offer(new Tree(i, j, age));
					}
					else {
						sum += age/2;
					}
				}
				
				preNourishArr[i][j] += sum;
			}
		}
	}

	private static void passAutumn() {
		// 인접 칸에 나이가 1인 나무 생성
		while(!liveTree.isEmpty()) {
			Tree tree = liveTree.poll();
			
			for (int i = 0; i < 8; i++) {
				int nr = tree.r + dr[i];
				int nc = tree.c + dc[i];
				
				if(isOutOfMap(nr, nc)) continue;
				treeArr[nr][nc].offerFirst(1);
			}
		}
	}

	private static boolean isOutOfMap(int nr, int nc) {
		if(nr >= 1 && nr <= N && nc >= 1 && nc <= N) return false;
		return true;
	}

	private static void passWinter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				preNourishArr[i][j] += addNourishArr[i][j];
			}
		}
	}
}
