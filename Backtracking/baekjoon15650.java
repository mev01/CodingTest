import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon15650 {
	static int N, M;
	static StringBuilder sb;
	static int[] arr = new int[9];
	
	static void func(int cnt) {
		if(cnt == M+1) { 
			for (int i = 1; i <= M; i++) {
				sb.append(arr[i]+" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = arr[cnt-1]+1; i <= N; i++) {
			arr[cnt] = i;
			func(cnt+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		func(1);
		System.out.println(sb.toString());
	}
}