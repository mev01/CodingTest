import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * LIS nlogn 알고리즘 사용
 */

public class baekjoon14003 {
	static int N, size = 1;
	static int[] idxArr, ans;
	static long[] num, list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		num = new long[N];
		list = new long[N];
		idxArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Long.parseLong(st.nextToken());
		}
		
		list[0] = num[0];
		for (int i = 1; i < N; i++) {
			long n = num[i];
			
			if(n > list[size - 1]) {
				idxArr[i] = size;
				list[size++] = n;
			}
			else {
				int idx = Arrays.binarySearch(list, 0, size - 1, n);
				
				if(idx < 0) {
					idxArr[i] = Math.abs(idx + 1);
					list[Math.abs(idx + 1)] = n;
				}
				else {
					idxArr[i] = idx;
				}
			}
		}
		
		int temp = size - 1;
		ans = new int[size];
		for (int i = N - 1; i >= 0 && temp >= 0; i--) {
			if(idxArr[i] == temp) {
				ans[temp--] = i;
			}
		}
		
		sb.append(size).append('\n');
		for (int i = 0; i < size; i++) {
			sb.append(num[ans[i]]).append(' ');
		}
		
		System.out.print(sb.toString());
	}
}
