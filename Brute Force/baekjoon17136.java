import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17136 {
	static int answer = Integer.MAX_VALUE;
	static int[][] map = new int[11][11];
	static int[] paper = {0,5,5,5,5,5};
	
	public static void main(String[] args) throws IOException {
		makeMap();
		pastePaper(0, 0, 0);
		System.out.println((answer==Integer.MAX_VALUE) ? -1 : answer);
	}
	
	private static void pastePaper(int x, int y, int cnt) {
		if(x == 10) {
			answer = Math.min(answer, cnt);
			return;
		}
		if(answer < cnt) return;
		
		if(map[x][y] == 1){
			for (int size = findMaxSize(x, y); size >= 1; size--) {
				if( paper[size] == 0 )
	                continue;
				paper[size]--;
				fillNumber(x, y, size, 0);
				if(y+size > 9)
					pastePaper(x+1, 0, cnt+1);
				else
					pastePaper(x, y+size, cnt+1);
				paper[size]++;
				fillNumber(x, y, size, 1);
			}
		}
		else{
			if(y >= 9)
				pastePaper(x+1, 0, cnt);
			else
				pastePaper(x, y+1, cnt);
		}
	}
	
	private static void fillNumber(int x, int y, int size, int num) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[x+i][y+j] = num;
			}
		}
	}
	
	private static int findMaxSize(int x, int y) {
		int temp = 5, cnt = 0, maxsize = 0, precnt = 5;
		
		for (int i = 0; i < precnt; i++) {
			for (int j = 0; j < precnt; j++) {
				if(x+i < 10 && y+j < 10) {
					if(map[x+i][y+j] == 1) {
						cnt++;
					}
					else {
						break;
					}
				}
			}
			precnt = cnt;
			temp = Math.min(i+1, cnt);
			maxsize = Math.max(maxsize, temp);
			cnt = 0;
		}
		
		return maxsize;
	}
	
	private static void makeMap() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
	}
}
