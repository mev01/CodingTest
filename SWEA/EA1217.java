import java.util.Scanner;

public class EA1217 {
	public static int involution(int N, int M) {
		if(M == 1)
			return N;
		return N * involution(N, M-1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			int num = sc.nextInt();
			int N = sc.nextInt(), M = sc.nextInt();
			
			System.out.println("#"+num+" "+involution(N, M));
		}
	}

}
