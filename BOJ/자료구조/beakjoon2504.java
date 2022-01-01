import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class beakjoon2504 {
	static Stack<Integer> st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String parens = br.readLine();
		st = new Stack<>();
		int ans = 0;
		
		for(int i = 0; i < parens.length(); i++) {
			char ch = parens.charAt(i);
			
			if(ch == '(') st.add(-2);
			else if(ch == '[') st.add(-3);
			else if(ch == ')') {
				int part = calc(-2);
				
				if(part == -1) break;
			}
			else {
				int part = calc(-3);
				
				if(part == -1) break;
			}
		}
		
		if(st.peek() < 0 ) System.out.println(0);
		else {
			int num = 0;
			
			while(!st.isEmpty()) {
				num += st.pop();
			}
			
			System.out.println(num);
		}
	}

	private static int calc(int num) {
		int sum = 0;
		while(st.peek() > 0) {
			sum += st.pop();
		}
		
		if(st.peek() != num) return -1;
		
		if(sum == 0) sum = 1;
		st.add(sum * (-1 * st.pop()));
		return st.peek();
	}

}