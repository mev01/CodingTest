import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class beakjoon1764 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> set = new HashSet<>();
		
		while(N-- > 0) {
			String name = br.readLine();
			
			set.add(name);
		}
		
		ArrayList<String> ansList = new ArrayList<>();
		
		while(M-- > 0) {
			String name = br.readLine();
			
			if(set.contains(name)) ansList.add(name);
		}
		
		Collections.sort(ansList);
		
		sb.append(ansList.size()).append('\n');
		for(String name : ansList) {
			sb.append(name).append('\n');
		}
		
		System.out.println(sb.toString());
	}

}