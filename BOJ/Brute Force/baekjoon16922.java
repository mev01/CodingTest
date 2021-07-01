import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon16922 {
	static int N, cnt = 0;
	static int[] arr;
	static boolean[] visited = new boolean[1001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		for (int i = 0; i < 4; i++) {
			BF(0, i);
		}
		
		System.out.print(cnt);
	}
	
	private static void BF(int idx, int num) {
		arr[idx] = num;
		
		if(idx == N - 1) {
			int realNum = numbering();
			
			if(!visited[realNum]) {
				++cnt;
				visited[realNum] = true;
			}
			return;
		}
		
		for (int i = num; i < 4; i++) {
			BF(idx + 1, i);
		}
	}

	private static int numbering() {
		int num = 0;
		for (int i = 0; i < N; i++) {
			switch (arr[i]) {
			case 0:
				num += 50;
				break;
			case 1:
				num += 10;
				break;
			case 2:
				num += 5;
				break;
			case 3:
				num += 1;
				break;
			}
		}
		return num;
	}
}
