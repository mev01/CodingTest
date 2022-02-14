import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baejoon15868 {
	static int N, M, ans = Integer.MAX_VALUE;
	static ArrayList<Pos> houseArr, chickenArr;
	static int[] chickenDisArr, combArr;
	static int[][] map;
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		houseArr = new ArrayList<>();
		chickenArr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int info = Integer.parseInt(st.nextToken());
				
				if(info == 1) houseArr.add(new Pos(i, j));
				else if(info == 2) chickenArr.add(new Pos(i, j));
			}
		}
		
		combArr = new int[M];
		Comb(0, 0);
		
		System.out.println(ans);
	}
	private static void Comb(int idx, int cnt) {
		if(cnt == M) {
			chickenDisArr = new int[houseArr.size()];
			Arrays.fill(chickenDisArr, Integer.MAX_VALUE);
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < houseArr.size(); j++) {
					Pos chicken = chickenArr.get(combArr[i]);
					Pos house = houseArr.get(j);
					
					int dis = (int)Math.abs(house.r - chicken.r) + (int)Math.abs(house.c - chicken.c);
					chickenDisArr[j] = Math.min(chickenDisArr[j], dis);
				}
			}
			
			int sum = 0;
			for(int dis : chickenDisArr) {
				sum += dis;
			}
			ans = Math.min(ans, sum);
			
			return;
		}
		
		for (int i = idx; i < chickenArr.size(); i++) {
			combArr[cnt] = i;
			Comb(i + 1, cnt + 1);
		}
	}
}
