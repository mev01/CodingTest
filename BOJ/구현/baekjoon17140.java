import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjoon17140 {
	static int R, C, K, ans = -1;
	static int lenR = 3, lenC = 3;
	static int[][] Arr = new int[4][4];
	
	static class Block implements Comparable<Block>{
		int num, cnt;
		
		public Block(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Block o) {
			if(this.cnt == o.cnt) return this.num - o.num;
			return this.cnt - o.cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				Arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int sec = 0;
		if(R <= lenR && C <= lenC && Arr[R][C] == K) {
			ans = sec;
			System.out.print(ans);
			return;
		}
		while(++sec <= 100) {
			if(lenR >= lenC) instR();
			else instC();
			
			if(R <= lenR && C <= lenC && Arr[R][C] == K) {
				ans = sec;
				break;
			}
		}
		
		System.out.print(ans);
	}

	private static void instR() {
		ArrayList<Block>[] newArray = new ArrayList[lenR];
		
		for (int i = 1; i <= lenR; i++) {
			HashMap<Integer, Integer> map = new HashMap<>();
			
			for (int j = 1; j <= lenC; j++) {
				if(Arr[i][j] == 0) continue;
				if(!map.containsKey(Arr[i][j])) map.put(Arr[i][j], 1);
				else map.put(Arr[i][j], map.get(Arr[i][j]) + 1);
			}
			
			ArrayList<Block> list = new ArrayList<>();
			for(Integer Int : map.keySet()) {
				list.add(new Block(Int, map.get(Int)));
			}
			
			Collections.sort(list);
			newArray[i - 1] = list;
		}
		
		int maxLen = 0;
		for(ArrayList<Block> list : newArray) {
			maxLen = Math.max(maxLen, list.size());
		}
		maxLen = Math.min(maxLen, 50);
		
		lenC = maxLen * 2;
		Arr = new int[lenR + 1][lenC + 1];
		
		for (int i = 1; i <= lenR; i++) {
			ArrayList<Block> list = newArray[i - 1];
			for (int j = 1; j <= lenC/2; j++) {
				int cnt = 0, num = 0;
				if(list.size() >= j) {
					cnt = list.get(j - 1).cnt;
					num = list.get(j - 1).num;
				}
				Arr[i][j * 2 - 1] = num;
				Arr[i][j * 2] = cnt;
			}
		}
	}

	private static void instC() {
		ArrayList<Block>[] newArray = new ArrayList[lenC];
		
		for (int j = 1; j <= lenC; j++) {
			HashMap<Integer, Integer> map = new HashMap<>();
			
			for (int i = 1; i <= lenR; i++) {
				if(Arr[i][j] == 0) continue;
				if(!map.containsKey(Arr[i][j])) map.put(Arr[i][j], 1);
				else map.put(Arr[i][j], map.get(Arr[i][j]) + 1);
			}
			
			ArrayList<Block> list = new ArrayList<>();
			for(Integer Int : map.keySet()) {
				list.add(new Block(Int, map.get(Int)));
			}
			
			Collections.sort(list);
			newArray[j - 1] = list;
		}
		
		int maxLen = 0;
		for(ArrayList<Block> list : newArray) {
			maxLen = Math.max(maxLen, list.size());
		}
		maxLen = Math.min(maxLen, 50);
		
		lenR = maxLen * 2;
		Arr = new int[lenR + 1][lenC + 1];
		
		for (int j = 1; j <= lenC; j++) {
			ArrayList<Block> list = newArray[j - 1];
			for (int i = 1; i <= lenR/2; i++) {
				int cnt = 0, num = 0;
				if(list.size() >= i) {
					cnt = list.get(i - 1).cnt;
					num = list.get(i - 1).num;
				}
				Arr[i * 2 - 1][j] = num;
				Arr[i * 2][j] = cnt;
			}
		}
	}
}
