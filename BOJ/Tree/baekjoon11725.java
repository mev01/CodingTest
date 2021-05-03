import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class baekjoon11725 {
	static int[] ans;
	static ArrayList<Integer>[] list;
	
	static void findParent(int num) {
		for (int i = 0; i < list[num].size(); i++) {
			if(ans[list[num].get(i)] == 0) ans[list[num].get(i)] = num;
			else list[num].remove(i--);
		}
		for (int i = 0; i < list[num].size(); i++) {
			findParent(list[num].get(i));
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		ans = new int[N+1];
		ans[1] = -1;
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		findParent(1);
		
		for (int i = 2; i <= N; i++) {
			sb.append(ans[i]+"\n");
		}
		System.out.println(sb.toString());
	}
}