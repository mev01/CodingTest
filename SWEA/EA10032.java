import java.util.Scanner;

public class EA10032 {

	public static void main(String[] args) {
		int num, N, K;
		Scanner sc=new Scanner(System.in);
		
		num=sc.nextInt();
		for (int i = 1; i <= num; i++) {
			N=sc.nextInt();
			K=sc.nextInt();
			System.out.println("#"+i+" "+((N%K==0)?0:1));
		}
	}

}
