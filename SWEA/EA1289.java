import java.util.Scanner;
import java.util.StringTokenizer;

public class EA1289 {
	static String a;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num= sc.nextInt();
		sc.nextLine();
		for (int i = 1; i <= num; i++) {
			a = sc.nextLine();	
			Calc(i);
		}
	}
	
	public static void Calc(int i) {
		int bol=1, cnt=0;
		for (int j = 0; j < a.length(); j++) {
			if( (a.charAt(j)-'0') == bol) {
				cnt++;
				bol=1-bol;
			}
		}
		System.out.println("#"+i+" "+cnt);
	}
}
