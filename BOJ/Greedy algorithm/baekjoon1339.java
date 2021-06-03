import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon1339 {
	static int n;
	static node[] alpha = new node[26];
	static long ans = 0;
	
	static class node implements Comparable<node>{
		int idx;
		long num;
		
		public node(int idx, long num) {
			super();
			this.idx = idx;
			this.num = num;
		}

		@Override
		public int compareTo(node o) {
			return (int) (o.num - this.num);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 26; i++) {
			alpha[i] = new node(i, 0);
		}
		
		// 각 단어를 체크하면서 알파벳의 자릿수 체크
		// ABA -> A : 101 , B : 10
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			int len = str.length();
			
			for (int j = 0; j < len; j++) {
				alpha[str.charAt(j) - 'A'].num += (long)Math.pow(10, len - j - 1);
			}
		}
		
		// 알파벳 중에 더 큰 알파벳부터 숫자 부여
		Arrays.sort(alpha);
		
		int temp = 9;
		for (int i = 0; i < 26 && alpha[i].num != 0; i++) {
			ans += alpha[i].num * temp--;
		}
		
		System.out.print(ans);
	}
}
