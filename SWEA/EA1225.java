import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class EA1225 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> que;
		
		for (int i = 1; i <= 10; i++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			que = new LinkedList<Integer>();
			int count = 1;
			
			while(st.hasMoreTokens()) {	//입력
				que.offer(Integer.parseInt(st.nextToken()));
			}
			
			
			while(true) {	//먼저 첫번 째 수 받아서 줄여서 다시 큐에 넣은 다음 탈출조건 체크
				int temp = que.poll()-count++;
				
				if(temp<0) temp = 0;
				if(count == 6) count = 1;
				que.offer(temp);
				
				if(temp==0) break;
			}
			
			sb.append("#"+i+" ");	//출력
			while(!que.isEmpty()) {
				sb.append(que.poll()+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
