import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class beakjoon1325 {
	static boolean[] visited;
	static Com[] comArr;
	
	static class Com{
		int num, gainTrustNum;
		ArrayList<Integer> trustList;
		
		public Com(int num) {
			this.num = num;
			trustList = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		comArr = new Com[N + 1];
		for(int i = 0; i <= N; i++) {
			comArr[i] = new Com(i);
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			comArr[from].trustList.add(to);
		}
		
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			
			Search(i);
		}
		
		int max = 0;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, comArr[i].gainTrustNum);
		}
		
		for(int i = 0; i <= N; i++) {
			if(max == comArr[i].gainTrustNum)
				sb.append(comArr[i].num).append(' ');
		}
		
		System.out.println(sb.toString());
	}

	private static void Search(int num) {
		visited[num] = true;
		comArr[num].gainTrustNum++;
		
		for(int nNum : comArr[num].trustList) {
			if(!visited[nNum]) Search(nNum);
		}
	}

}
