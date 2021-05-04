import java.util.ArrayList;
import java.util.Scanner;

public class EA1215 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i <= 10; i++) {
			int length = sc.nextInt(), answer = 0;
			ArrayList<StringBuilder> list = new ArrayList<>();
			
			for (int j = 0; j < 8; j++) {
				StringBuilder word = new StringBuilder(sc.next());
				list.add(word);
			}
			for (int j = 0; j < 8; j++) {
				StringBuilder word = new StringBuilder();
				for (int k = 0; k < 8; k++) {
					word.append(list.get(k).charAt(j));	
				}
				list.add(word);
			}
			for (int j = 0; j < 16; j++) {
				for (int P = 0; P < 8-length+1; P++) {
					StringBuilder word = new StringBuilder();
					for (int W = P; W < P+length; W++) {
						word.append(list.get(j).charAt(W));
					}
					String preword = word.toString();
					word.reverse();
					if(preword.equals(word.toString())) {
						 answer++;
					}
				}
			}
			System.out.println("#"+i+" "+answer);
		}
	}

}
