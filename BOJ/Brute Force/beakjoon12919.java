import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class beakjoon12919 {
	static String S, T;
	static boolean[] isBArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		S = br.readLine();
		T = br.readLine();
		
		Oper(sb.append(T));
		
		System.out.println(0);
	}

	private static void Oper(StringBuilder word) {
		if(word.length() == S.length()) {
			if(word.toString().equals(S)) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		
		if(word.charAt(word.length() - 1) == 'A') {
			StringBuilder nWord = new StringBuilder(word.toString());
			
			Oper(nWord.deleteCharAt(nWord.length() - 1));
		}
		if(word.charAt(0) == 'B') {
			StringBuilder nWord = new StringBuilder(word.toString());
			
			Oper(nWord.deleteCharAt(0).reverse());
		}
		
	}

}
