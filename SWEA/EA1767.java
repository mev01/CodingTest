import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Core{
	int x;
	int y;
	public Core(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class EA1767 {
	static int N, numCore, sumWire;
	static int[][] map;
	static List<Core> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			numCore = 0;
			sumWire = 0;
			list = new ArrayList<Core>();
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						Core core = new Core(i, j);
						list.add(core);
					}
				}
			}
			
			func(0, 0, 0);
			sb.append("#"+tc+" "+sumWire).append('\n');
		}
		System.out.print(sb.toString());
	}
	
	static void func(int cnt, int num, int sum) {
		if(cnt == list.size()) {
			if(numCore < num) {
				numCore = num;
				sumWire = sum;
			}
			else if(numCore == num && sumWire > sum) {
				sumWire = sum;
			}
			return;
		}
		int x = list.get(cnt).x;
		int y = list.get(cnt).y;
		if(x == 0 || x == N-1 || y == 0 || y == N-1) {
			func(cnt+1, num+1, sum);
			return;
		}
		
		for (int i = 0; i < x; i++) {  //상
			if(map[i][y] == 1)
				break;
			if(i == x-1) {
				for (int k = 0; k < x; k++) {
					map[k][y] = 1;
				}
				func(cnt+1, num+1, sum+x);
				for (int k = 0; k < x; k++) {
					map[k][y] = 0;
				}
			}
		}
		for (int i = x+1; i < N; i++) {	//하
			if(map[i][y] == 1)
				break;
			if(i == N-1) {
				for (int k = x+1; k < N; k++) {
					map[k][y] = 1;
				}
				func(cnt+1, num+1, sum+N-x-1);
				for (int k = x+1; k < N; k++) {
					map[k][y] = 0;
				}
			}
		}
		for (int j = 0; j < y; j++) {	//좌
			if(map[x][j] == 1)
				break;
			if(j == y-1) {
				for (int k = 0; k < y; k++) {
					map[x][k] = 1;
				}
				func(cnt+1, num+1, sum+y);
				for (int k = 0; k < y; k++) {
					map[x][k] = 0;
				}
			}
		}
		for (int j = y+1; j < N; j++) {	//우
			if(map[x][j] == 1)
				break;
			if(j == N-1) {
				for (int k = y+1; k < N; k++) {
					map[x][k] = 1;
				}
				func(cnt+1, num+1, sum+N-y-1);
				for (int k = y+1; k < N; k++) {
					map[x][k] = 0;
				}
			}
		}
		func(cnt+1, num, sum); //연결안하고 넘어감
	}
}
