import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon2605 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		List<Integer> list = new ArrayList<Integer>();
		
		int T = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= T; i++) {
			list.add(i-1-Integer.parseInt(st.nextToken()), i);
		}
		
		for (int i = 0; i < T; i++) {
			sb.append(list.get(i)).append(' ');
		}
		System.out.print(sb.toString());
	}
}
