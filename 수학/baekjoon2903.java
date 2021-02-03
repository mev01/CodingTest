import java.util.Scanner;

public class baekjoon2903 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int numDot = 2;
		for (int i = 0; i < N; i++) {
			numDot += numDot-1;
		}
		System.out.println(numDot*numDot);
	}

}
