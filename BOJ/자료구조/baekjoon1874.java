import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon1874 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int index = 1, isPossible = 1;
		boolean[] visit = new boolean[100001];
		
		loop:
		while(N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			if(num >= index) {
				for (int i = index; i <= num; i++) {
					if(!visit[i])
						sb.append("+\n");
				}
				sb.append("-\n");
				index = num;
				visit[index] = true;
			}
			else if(num < index) {
				for (int i = index-1; i >= num; i--) {
					if(!visit[i] && i != num) {
						isPossible = 0;
						break loop;
					}
				}
				sb.append("-\n");
				index = num;
				visit[index] = true;
			}
		}
		System.out.println((isPossible==1 ? sb.toString() : "NO"));
	}

}
