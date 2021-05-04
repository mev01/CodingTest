import java.util.Scanner;
import java.util.StringTokenizer;

public class EA5215 {
	static int answer=0, N, L;
	static int[] score = new int[21];
	static int[] cal = new int[21];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num= sc.nextInt();
		
		for (int i = 1; i <= num; i++) {
			answer=0;
			N=sc.nextInt();
			L=sc.nextInt();
			
			for (int j = 1; j <= N; j++) {
				score[j]=sc.nextInt();
				cal[j]=sc.nextInt();
			}
			
			for (int j = 1; j <= N; j++) {
				int calplus=0, scoplus=0;
				Calc(j, calplus, scoplus);
			}
			System.out.println("#"+i+" "+answer);
		}
	}
	
	public static void Calc(int i, int calplus, int scoplus) {
		if(calplus+cal[i]>L) {
			return;
		}
		else {
			calplus+=cal[i];
			scoplus+=score[i];
			if(answer<scoplus)
				answer=scoplus;
		}
		for (int j = i+1; j <= N; j++) {
			Calc(j, calplus, scoplus);
		}
	}
}
