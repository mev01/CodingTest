import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EA9229 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> list;
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			list = new ArrayList<>();
			int wei = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			loop:
			for (int f = N-1; f >= 1; f--) {
				for (int s = f-1; s >= 0; s--) {
					if(list.get(f) + list.get(s) <= M && list.get(f) + list.get(s) > wei) {
						wei = list.get(f) + list.get(s);
						if(wei == M) break loop;
					}
				}
			}
			
			sb.append("#"+i+" "+ (wei==0 ? -1 : wei)+"\n");
		}
		System.out.println(sb.toString());
	}

}
