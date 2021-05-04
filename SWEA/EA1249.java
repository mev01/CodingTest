import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class EA1249 {
	static int N;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] map, minCost;
	static boolean[][] checked;
	static Heap heap;
	
	static class Section implements Comparable<Section>{
		int r, c, cost;

		public Section(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Section o) {
			return this.cost - o.cost;
		}
	}
	
	static class Heap {
		private ArrayList<Section> list;	// heap 배열
		private int[][] listIdxArr;			// 각 section에 대한 list 에서의 인덱스
		
		public Heap() {
			super();
			
			list = new ArrayList<>();
			listIdxArr = new int[N][N];
			
			list.add(new Section(0, 0, 0));	// dummy 값
		}

		public void offer(Section s) {
			// s가 들어갈 인덱스를 key 배열에 저장
			listIdxArr[s.r][s.c] = list.size();
			// T 객체를 list 끝에 저장
			list.add(s);
			// up
			up(listIdxArr[s.r][s.c]);
		}
		
		public Section poll() {
			// 첫번째 객체 미리 저장
			Section s = list.get(1);
			// 마지막 객체를 첫번째 자리에
			list.set(1, list.get(list.size() - 1));
			list.remove(list.size() - 1);
			
			// down
			if(list.size() > 1) down();
			
			return s;
		}
		
		public void change(Section s) {
			list.get(listIdxArr[s.r][s.c]).cost = s.cost;
			
			up(listIdxArr[s.r][s.c]);
		}
		
		public void up(int idx) {
			
			while(idx > 1) {
				// 자신의 부모보다 작을 때
				if(list.get(idx).compareTo(list.get(idx >> 1)) < 0) {
					// 스왑
					Section tmp = list.get(idx);
					list.set(idx, list.get(idx >> 1));
					list.set(idx >> 1, tmp);
					
					listIdxArr[list.get(idx).r][list.get(idx).c] = idx;
					
					idx >>= 1;
				}
				else
					break;
			}
			
			listIdxArr[list.get(idx).r][list.get(idx).c] = idx;
		}
		
		public void down() {
			int idx = 1;
			
			while((idx * 2) < list.size()) {
				int max;
				
				// 왼쪽 자식이 오른쪽 자식보다 작은 경우
				if((idx * 2) == list.size() - 1 || list.get(idx * 2).compareTo(list.get(idx * 2 + 1)) < 0) {
					max = idx << 1;
				}
				else
					max = idx * 2 + 1;
				if(list.get(idx).compareTo(list.get(max)) < 0)
					break;
				
				Section tmp = list.get(idx);
				list.set(idx, list.get(max));
				list.set(max, tmp);
				
				listIdxArr[list.get(idx).r][list.get(idx).c] = idx;
				
				idx = max;
			}
			
			listIdxArr[list.get(idx).r][list.get(idx).c] = idx;
		}
		
		public boolean hasSection(int r, int c) {
			if(listIdxArr[r][c] == 0) return false;
			return true;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			minCost = new int[N][N];
			checked = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					minCost[i][j] = Integer.MAX_VALUE - 1000000;
				}
			}
			
			
			heap = new Heap();
			heap.offer(new Section(0, 0, 0));
			minCost[0][0] = 0;
			
			while(true) {
				// PQ에서  poll
				Section section = heap.poll();
				
				// 해당 노드 선택하고 check
				checked[section.r][section.c] = true;
				
				// 해당 노드가 목표노드이면 break
				if(checked[N-1][N-1]) break;
				
				// 값들 갱신해서 pq에 넣기
				for (int dir = 0; dir < 4; dir++) {
					int nr = section.r + dr[dir];
					int nc = section.c + dc[dir];
					
					if(isNotBoundOut(nr, nc)) {
						if(minCost[nr][nc] > minCost[section.r][section.c] + map[nr][nc]) {
							minCost[nr][nc] = minCost[section.r][section.c] + map[nr][nc];
							if(heap.hasSection(nr, nc)) {
								heap.change(new Section(nr, nc, minCost[nr][nc]));
							}
							else {
								heap.offer(new Section(nr, nc, minCost[section.r][section.c] + map[nr][nc]));
							}
						}
					}
				}
				
			}
			
			sb.append("#"+tc+" " + minCost[N-1][N-1] + '\n');
		}
		System.out.print(sb.toString());
	}

	private static boolean isNotBoundOut(int r, int c) {
		if(r >= 0 && r < N && c >= 0 && c < N) return true;
		return false;
	}
}
