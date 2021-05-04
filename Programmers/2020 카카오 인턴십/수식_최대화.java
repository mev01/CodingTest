import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class 수식_최대화 {
	static Deque<String> deque, exDeque;
	static String[] operator = new String[6];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println(solution("50*6-3*2"));
	}
	
	public static long solution(String expression) {
        long answer = 0;
        
        deque = new LinkedList<String>();
        parsing(expression);
        setPriority();
        for (int i = 0; i < 6; i++) {
        	exDeque = new LinkedList<>();
        	for(String str : deque) {
        		exDeque.offer(str);
        	}
        	
        	for (int j = 0; j < 3; j++) {
				compute(i, j);
			}
        	answer = Math.max(answer, Math.abs(Long.parseLong(exDeque.poll())));
		}
        
        return answer;
    }

	private static void compute(int num, int operNum) {
		int len = exDeque.size() / 2;
		
		String num1 = exDeque.poll();
		String num2;
		while(len-- > 0) {
			String oper = exDeque.poll();
			num2 = exDeque.poll();
			
			if(oper.equals(Character.toString(operator[num].charAt(operNum)))) {
				long temp = 0;
				
				switch (operator[num].charAt(operNum)) {
					case '+':
						temp = Long.parseLong(num1) + Long.parseLong(num2);
						break;
					case '-':
						temp = Long.parseLong(num1) - Long.parseLong(num2);
						break;
					case '*':
						temp = Long.parseLong(num1) * Long.parseLong(num2);
						break;
				}
				
				num1 = Long.toString(temp);
			}
			else {
				exDeque.offer(num1);
				exDeque.offer(oper);
				num1 = num2;
			}
		}
		
		exDeque.offer(num1);
	}

	private static void setPriority() {
		operator[0] = "+-*";
		operator[1] = "+*-";
		operator[2] = "-+*";
		operator[3] = "-*+";
		operator[4] = "*+-";
		operator[5] = "*-+";
	}

	private static void parsing(String expression) {
		String num = "";
		
		for (int i = 0; i < expression.length(); i++) {
			if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
				num += expression.charAt(i);
			}
			else {
				deque.offer(num);
				num = "";
				deque.offer(Character.toString(expression.charAt(i)));
			}
		}
		deque.offer(num);
	}
}

