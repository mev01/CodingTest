import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1806 {
	static int N, S, len = Integer.MAX_VALUE;
	static int fir = 0, sec = 0, sum =0;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		while(sec < N) {
			// 해당 인덱스의 수 구하기
			sum += arr[sec];
						
			// sec - fir < len 일때까지 sec++
			if(sec - fir >= len - 1) {
				sum -= arr[fir++];
				sum -= arr[sec];
				continue;
			}
			
			// s보다 크거나 같으면 sec고정 시키고 fir--
			if(sum >= S) {
				len = sec - fir + 1;
				
				// fir++ 하면서 sum 빼고
				// sum이 그래도 가능하다면 len 업데이트
				// sum이 안된아면 fir = sec, sum = 0
				while(true) {
					sum -= arr[fir++];
					
					if(sum >= S) len--;
					else {
						sum -= arr[sec];
						break;
					}
				}
			}
			// 작으면 sec++
			else
				sec++;
		}
		
		
		
		System.out.print(len == Integer.MAX_VALUE ? 0 : len);
	}
}
