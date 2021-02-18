import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class baekjoon2164 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> que = new LinkedList<Integer>();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			que.offer(i);
		}
		
		while(que.size() > 1) {
			que.poll();
			que.offer(que.poll());
		}
		
		sb.append(que.poll());	//출력
		System.out.println(sb.toString());
	}
}
