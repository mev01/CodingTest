import java.io.BufferedReader;
import java.io.InputStreamReader;

public class baekjoon11720 {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer=0;
		
		String Num = br.readLine();
		for (int j = 0; j < N; j++) {
			answer += (Num.charAt(j) - '0');
		}
		
		System.out.println(answer);
	}
}
