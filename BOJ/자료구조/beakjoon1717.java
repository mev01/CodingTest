import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon1717 {
	static int N, M;
	static int[] arr, size;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		size = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			arr[i] = i;
			size[i] = 1;
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int inst = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); 
			
			switch (inst) {
			case 0:
				unionGroup(a, b);
				break;
			case 1:
				if(findRoot(a) == findRoot(b)) sb.append("YES").append('\n');
				else sb.append("NO").append('\n');
				break;
			}
		}
		
		System.out.print(sb.toString());
	}

	private static void unionGroup(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		
		if(size[aRoot] >= size[bRoot]) {
			arr[bRoot] = aRoot;
		}
		else {
			arr[aRoot] = bRoot;
		}
	}

	private static int findRoot(int a) {
		if(arr[a] == a) return a;
//		return arr[a] = findRoot(arr[a]);
		return findRoot(arr[a]);
	}
}
