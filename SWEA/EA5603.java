import java.util.Scanner;

public class EA5603 {

	public static void main(String[] args) {
		int num, N;
		
		Scanner sc=new Scanner(System.in);
		num=sc.nextInt();
		for (int i = 1; i <= num; i++) {
			N=sc.nextInt();
			int sum=0, answer=0;
			int [] arr=new int[N];
			
			for (int j = 0; j < N; j++) {
				arr[j]=sc.nextInt();
				sum+=arr[j];			
			}
			sum=sum/N;
			for (int j = 0; j < N; j++) {
				if(arr[j]>sum) answer+=(arr[j]-sum);			
			}
			
			System.out.println("#"+i+" "+answer);
		}
	}

}
