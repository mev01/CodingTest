import java.util.Scanner;

public class EA3431 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int i = 1; i <= T; i++) {
			int L = sc.nextInt();
			int U = sc.nextInt();
			int X = sc.nextInt();
			
			System.out.println("#"+i+" "+((X < L) ? L-X : (X > U) ? -1 : 0));
		}
	}

}
