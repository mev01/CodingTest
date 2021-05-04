import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class EA1224 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> numStack;
		Stack<Character> operStack;
		
		for (int T = 1; T <= 10; T++) {
			int len = Integer.parseInt(br.readLine());
			String calc = br.readLine();
			numStack = new Stack<>();
			operStack = new Stack<>();
			
			for (int i = 0; i < len; i++) {
				Character ch = calc.charAt(i);
				
				if(ch>='0' && ch<='9') {
					numStack.push((int)ch-'0');
				}
				else if(ch == '+') {
					operStack.push(ch);
				} 
				else if(ch == '*') {	
					char next = calc.charAt(i+1);
					// 다음 글자가 숫자이면 다음 글자를 가져와서 *연산
					// 다음 글자가 괄호인경우 일단 *를 operStack에 넣기
					if(next>='0' && next<='9') {
						numStack.push(numStack.pop() * (int)(next-'0'));
						i++;
					}
					else
						operStack.push(ch);
				}
				else if(ch == '(') {
					operStack.push(ch);
				}
				else if(ch == ')') {
					while(true) {
						// operStack에서 (가 나올 때까지 반복문을 돌면서 +연산을 수행
						// *연산은 미리 해주었으므로 +연산만 하면 됨
						Character oper = operStack.pop();
						if(oper == '(') break;
						numStack.push(numStack.pop() + numStack.pop());
					}
					while(!operStack.empty()) {
						// (가 끝나고 그전에 *연산자가 있었던 경우
						// *연산자가 안나올 때까지 *연산 실행
						// ex) 7*8*(1+2)인 경우 7*8*를 연산해준다.
						Character oper = operStack.peek();
						if(oper != '*') break;
						operStack.pop();
						numStack.push(numStack.pop() * numStack.pop());
					}
				}
				
			}
			sb.append("#"+T+" "+numStack.pop()+"\n");	//다 연산한 이후에는 numStack에는 숫자 하나만 남는다.
		}
		System.out.println(sb.toString());
	}

}
