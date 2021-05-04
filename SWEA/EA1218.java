import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class EA1218 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack;
		
		for (int i = 1; i < 11; i++) {
			stack = new Stack<>();
			int len = Integer.parseInt(br.readLine());
			String[] line = (br.readLine()).split("");
			int j;
			for (j = 0; j < len; j++) {
				if(line[j].equals("(")) stack.push(0);
				else if(line[j].equals("[")) stack.push(1);
				else if(line[j].equals("{")) stack.push(2);
				else if(line[j].equals("<")) stack.push(3);
				else if(line[j].equals(")")) {
					if(stack.pop() != 0) break;
				}
				else if(line[j].equals("]")) {
					if(stack.pop() != 1) break;
				}
				else if(line[j].equals("}")) {
					if(stack.pop() != 2) break;
				}
				else if(line[j].equals(">")) {
					if(stack.pop() != 3) break;
				}
			}
			
			sb.append("#"+i+" "+(j==len && stack.empty() ? 1 : 0)+"\n");
		}
		System.out.println(sb.toString());
	}

}
