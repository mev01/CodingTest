import java.io.IOException;

public class 카카오인턴십_2021_1 {
	static String[] number = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println(solution("one4seveneight"));
	}
	
	public static int solution(String s) {
        int answer = 0;
        
        for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) <= '9' && s.charAt(i) >= '0') {
				answer = answer * 10 + (s.charAt(i) - '0');
			}
			else {
				String str = s.substring(i, i+2);
				
				for (int j = 0; j < 10; j++) {
					if(number[j].substring(0, 2).equals(str)) {
						answer = answer * 10 + j;
						i += number[j].length() - 1;
						break;
					}
				}
			}
		}
        
        return answer;
	}
}