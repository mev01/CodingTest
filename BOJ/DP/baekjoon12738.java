import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * LIS nlogn 알고리즘 사용
 */

public class baekjoon12738 {
	static int N, size = 1;
	static List<Long> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<Long>();
		
		st = new StringTokenizer(br.readLine());
		list.add(Long.parseLong(st.nextToken()));
		
		for (int i = 1; i < N; i++) {
			Long n = Long.parseLong(st.nextToken());
			
			if(n > list.get(size - 1)) {
				list.add(n);
				size++;
			}
			else {
				int idx = Collections.binarySearch(list, n);
				
				if(idx < 0) {
					list.set(Math.abs(idx + 1), n);
				}
			}
		}
		
		System.out.print(size);
	}
}
