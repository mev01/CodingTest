import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon1759 {
	static int L, C;
	static char[] arr, ans;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		ans = new char[L];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		
		func(0, 0);
		System.out.print(sb.toString());
	}

	private static void func(int cnt, int idx) {
		if(cnt == L){
			//°Ë»ç
			int numVow = 0, numCon = 0;
			for (int i = 0; i < L; i++) {
				if(ans[i] == 'a' || ans[i] == 'e' || ans[i] == 'i' || ans[i] == 'o' || ans[i] == 'u'){
					numVow++;
				}
				else{
					numCon++;
				}
			}
			if(numVow >= 1 && numCon >= 2){
				for (int i = 0; i < L; i++) {
					sb.append(ans[i]);
				}
				sb.append('\n');
			}
			return;
		}
		for (int i = idx; i < C; i++) {
			ans[cnt] = arr[i];
			func(cnt+1, i+1);
		}
	}
}
