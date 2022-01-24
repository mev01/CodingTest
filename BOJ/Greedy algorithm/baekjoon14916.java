import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14916 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int coin = Integer.parseInt(br.readLine());
		
		int min = Integer.MAX_VALUE;
		for (int i = coin / 5; i >= 0; i--) {
			if((coin - i * 5) % 2 == 0) {
				min = Math.min(min, i + (coin - i * 5) / 2);
				break;
			}
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

}
