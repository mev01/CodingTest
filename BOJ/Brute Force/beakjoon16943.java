import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon16943 {
	static int ans = 0, B;
	static char[] arr, permArr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String A = st.nextToken();
		B = Integer.parseInt(st.nextToken());
		
		arr = A.toCharArray();
		permArr = new char[arr.length];
		visited = new boolean[arr.length];
		
		Perm(0);
		
		System.out.println(ans == 0 ? -1 : ans);
	}
	
	static void Perm(int idx) {
		if(idx == arr.length) {
			int C = Integer.parseInt(new String(permArr));
			if(C < B) ans = Math.max(ans, C);
			
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(!visited[i]) {
				if(!(idx == 0 && arr[i] == '0')){
					visited[i] = true;
					permArr[idx] = arr[i];
					
					Perm(idx + 1);
					
					visited[i] = false;
				}
			}
		}
	}

}
