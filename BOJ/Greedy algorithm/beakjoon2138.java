import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class beakjoon2138 {
	static int N;
	static char[] state, nState;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		state = br.readLine().toCharArray();
		nState = br.readLine().toCharArray();
		
		int ans = 1000000;
		ans = Math.min(ans, getMinCount(state.clone()));
		
		Touch(state, 0);
		Touch(state, 1);
		ans = Math.min(ans, getMinCount(state) + 1);
		
		System.out.println(ans == 1000000 ? -1 : ans);
	}

	private static void Touch(char[] arr, int idx) {
		if(arr[idx] == '0') arr[idx] = '1';
		else arr[idx] = '0';
	}

	private static int getMinCount(char[] arr) {
		int cnt = 0;
		
		for(int i = 1; i < N - 1; i++) {
			if(arr[i - 1] != nState[i - 1]) {
				cnt++;
				Touch(arr, i);
				Touch(arr, i + 1);
			}
		}
		
		if(arr[N - 2] != nState[N - 2]) {
			cnt++;
			Touch(arr, N - 1);
		}
		
		if(arr[N - 1] != nState[N - 1]) return 1000000;
		return cnt;
	}
	
	

}
