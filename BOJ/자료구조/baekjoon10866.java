import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon10866 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[] deque = new int[20000];
		int start = 10000, end = 10000;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			String inst = st.nextToken();
			
			if(inst.equals("push_front")) {
				int x = Integer.parseInt(st.nextToken());
				
				deque[--start] = x;
			}
			else if(inst.equals("push_back")) {
				int x = Integer.parseInt(st.nextToken());
				
				deque[end++] = x;
			}
			else if(inst.equals("pop_front")) {
				if(start == end)
					sb.append(-1+"\n");
				else
					sb.append(deque[start++]+"\n");
			}
			else if(inst.equals("pop_back")) {
				if(start == end)
					sb.append(-1+"\n");
				else
					sb.append(deque[--end]+"\n");
			}
			else if(inst.equals("size")) {
				sb.append(end-start+"\n");
			}
			else if(inst.equals("empty")) {
				if(start == end)
					sb.append(1+"\n");
				else
					sb.append(0+"\n");
			}
			else if(inst.equals("front")) {
				if(start == end)
					sb.append(-1+"\n");
				else
					sb.append(deque[start]+"\n");
			}
			else if(inst.equals("back")) {
				if(start == end)
					sb.append(-1+"\n");
				else
					sb.append(deque[end-1]+"\n");
			}
		}
		System.out.println(sb.toString());
	}

}
