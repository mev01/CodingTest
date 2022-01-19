import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class beakjoon1620 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> map = new HashMap<>();
		String[] arr = new String[N + 1];
		
		for(int i = 1; i <= N; i++) {
			String name = br.readLine();
			
			map.put(name, i);
			arr[i] = name;
		}
		
		while(M-- > 0) {
			String q = br.readLine();
			
			if(Character.isDigit(q.charAt(0))) {
				int num = Integer.parseInt(q);
				
				sb.append(arr[num]).append('\n');
			}
			else {
				sb.append(map.get(q)).append('\n');
			}
		}
		
		System.out.println(sb.toString());
	}

}
