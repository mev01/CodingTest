import java.util.Scanner;

public class EA4406 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int i = 1; i <= T; i++) {
			StringBuilder word = new StringBuilder(sc.next());
			for (int j = 0; j < word.length(); j++) {
				if(word.charAt(j)=='a' || word.charAt(j)=='e' || word.charAt(j)=='i' || word.charAt(j)=='o' || word.charAt(j)=='u' ) {
					word.deleteCharAt(j--);
				}			
			}
			System.out.println("#"+i+" "+word);
		}
	}

}
