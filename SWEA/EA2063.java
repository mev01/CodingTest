import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA2063 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[200];
		int cnt = 0;
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			arr[Integer.parseInt(st.nextToken())]++;
		}
		for (int i = 0; i < 200; i++) {
			cnt += arr[i];
			if(cnt >= (N/2+1)) { 
				System.out.println(i);
				break;
			}
		}
	}

}
