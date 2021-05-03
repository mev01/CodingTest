import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2529 {
	static int N;
	static char[] inequal;
	static int[] ans;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		inequal = new char[N];
		ans = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inequal[i] = st.nextToken().charAt(0);
		}
		
		minfunc(0, 0);
		maxfunc(0, 0);
		System.out.print(sb.toString());
	}
	private static boolean minfunc(int cnt, int flag) {
		if(cnt == N+1){
			for (int i = 0; i < N+1; i++) {
				sb.append(ans[i]);
			}
			sb.append('\n');
			return true;
		}
		for (int i = 9; i >= 0; i--) {
			if((flag & 1<<i) == 0){
				ans[cnt] = i;
				if(cnt > 0){	//부등호 관계가 맞는지
					if(inequal[cnt - 1] == '<'){
						if(ans[cnt-1] > ans[cnt]) continue;
					}
					else{
						if(ans[cnt-1] < ans[cnt]) continue;
					}
				}
				
				if(minfunc(cnt+1, flag | 1<<i)) return true;
			}
		}
		return false;
	}
	private static boolean maxfunc(int cnt, int flag) {
		if(cnt == N+1){
			for (int i = 0; i < N+1; i++) {
				sb.append(ans[i]);
			}
			sb.append('\n');
			return true;
		}
		for (int i = 0; i < 10; i++) {
			if((flag & 1<<i) == 0){
				ans[cnt] = i;
				if(cnt > 0){	//부등호 관계가 맞는지
					if(inequal[cnt - 1] == '<'){
						if(ans[cnt-1] > ans[cnt]) continue;
					}
					else{
						if(ans[cnt-1] < ans[cnt]) continue;
					}
				}
				
				if(maxfunc(cnt+1, flag | 1<<i)) return true;
			}
		}
		return false;	
	}
}
