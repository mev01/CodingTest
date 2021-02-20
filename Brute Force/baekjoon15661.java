import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon15661 {
	static int N, ans = Integer.MAX_VALUE;
	static int[] start, link;
	static int[][] stat;


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		stat = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				stat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 2; i <= N/2; i++) {
			start = new int[i];
			link = new int[N-i];
			makeTeam(i, 0, 0, 0);
		}
		System.out.println(ans);
	}

	private static void makeTeam(int total, int cnt, int index, int flag) {
		if(cnt == total){
			int startidx = 0, linkidx = 0;
			for (int i = 0; i < N; i++) {
				if((flag & 1<<i) != 0) start[startidx++] = i;
				else link[linkidx++] = i;
			}
			//start team
			int sumStart = 0;
			for (int i = 0; i < start.length; i++) {
				for (int j = i+1; j < start.length; j++) {
					sumStart += stat[start[i]][start[j]];
					sumStart += stat[start[j]][start[i]];
				}
			}
			//link team
			int sumLink = 0;
			for (int i = 0; i < link.length; i++) {
				for (int j = i+1; j < link.length; j++) {
					sumLink += stat[link[i]][link[j]];
					sumLink += stat[link[j]][link[i]];
				}
			}
			
			if(Math.abs(sumLink - sumStart) < ans) 
				ans = Math.abs(sumLink - sumStart);
			return;
		}
		for (int i = index; i < N; i++) {
			makeTeam(total, cnt+1, i+1, flag | 1<<i);
		}
	}

}
