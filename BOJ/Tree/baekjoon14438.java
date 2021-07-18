import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14438 {
	static int N, M;
	static int[] arr, tree;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		tree = new int[4 * N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		makeTree(1, N, 1);
		
		M = Integer.parseInt(br.readLine());
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int inst = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(inst == 1) change(1, N, 1, a, b);
			else sb.append(findMin(1, N, 1, a, b)).append('\n');
		}
		
		System.out.print(sb.toString());
	}

	private static int makeTree(int s, int e, int node) {
		if(s == e) return tree[node] = arr[s];
		int m = (s + e) / 2;
		return tree[node] = Math.min(makeTree(s, m, node * 2), makeTree(m + 1, e, node * 2 + 1));
	}

	private static int change(int s, int e, int node, int i, int v) {
		if(s == i && e == i) return tree[node] = v;
		if(e < i || i < s) return tree[node];
		int m = (s + e) / 2;
		return tree[node] = Math.min(change(s, m, node * 2, i, v), change(m + 1, e, node * 2 + 1, i, v));
	}

	private static int findMin(int s, int e, int node, int left, int right) {
		if(left <= s && e <= right) return tree[node];
		if(e < left || right < s) return Integer.MAX_VALUE;
		int m = (s + e) / 2;
		return Math.min(findMin(s, m, node * 2, left, right), findMin(m + 1, e, node * 2 + 1, left, right));
	}
}
