import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10868 {
	static int N, M;
	static int[] arr, tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		tree = new int[N * 4];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		setTree(0, N - 1, 1);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(findMin(0, N - 1, 1, a - 1, b - 1)).append('\n');
		}
		
		System.out.print(sb.toString());
	}
	
	private static int setTree(int s, int e, int node) {
		if(s == e) {
			return tree[node] = arr[s];
		}
		int m = (s + e) / 2;
		return tree[node] = Math.min(setTree(s, m, node * 2), setTree(m + 1, e, node * 2 + 1));
	}
	
	private static int findMin(int s, int e, int node, int left, int right) {
		if(left <= s && e <= right) return tree[node];
		if(left > e || s > right) return Integer.MAX_VALUE;
		int m = (s + e) / 2;
		return Math.min(findMin(s, m, node * 2, left, right), findMin(m + 1, e, node * 2 + 1, left, right));
	}
}
