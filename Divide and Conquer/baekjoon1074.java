import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1074 {
	static int N, r, c, ans = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int max = (int) ((Math.pow(2, N)) - 1);
		divide(0, 0, max, max, (max+1)/2);
		System.out.print(ans);
	}
	private static void divide(int sx, int sy, int ex, int ey, int cnt) {
		if(sx == ex && sy == ey) {
			return;
		}
		if(r > (sx + ex)/2) {
			if(c > (sy + ey)/2) {	//4
				ans += (int)(Math.pow(cnt, 2)) * 3;
				divide((sx + ex)/2 + 1, (sy + ey)/2 + 1, ex, ey, cnt/2);
			}
			else {	//3
				ans += (int)(Math.pow(cnt, 2)) * 2;
				divide((sx + ex)/2 + 1, sy, ex, (sy + ey)/2, cnt/2);
			}
		}
		else {
			if(c > (sy + ey)/2) {	//2
				ans += (int)(Math.pow(cnt, 2)) * 1;
				divide(sx, (sy + ey)/2 + 1, (sx + ex)/2, ey, cnt/2);
			}
			else {	//1
				divide(sx, sy, (sx + ex)/2, (sy + ey)/2, cnt/2);
			}
		}
	}
}
