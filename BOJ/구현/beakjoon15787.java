import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class beakjoon15787 {
	static int N, M;
	static int[] trainArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trainArr = new int[N + 1];
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int x = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
			
			switch (order) {
			case 1:
				trainArr[i] = trainArr[i] | (1 << (x - 1));
				break;
			case 2:
				trainArr[i] = trainArr[i] & ~(1 << (x - 1));
				break;
			case 3:
				trainArr[i] = trainArr[i] << 1;
				trainArr[i] = trainArr[i] & ~(1 << 20);
				break;
			case 4:
				trainArr[i] = trainArr[i] >> 1;
				break;
			}
		}
		
		int ans = 0;
		Set<Integer> set = new HashSet<>();
		
		for (int i = 1; i <= N; i++) {
			if(!set.contains(trainArr[i])) {
				ans++;
				set.add(trainArr[i]);
			}
		}
		
		System.out.println(ans);
	}

}
