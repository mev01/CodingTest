import java.util.Scanner;

public class baekjoon2581 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(), N = sc.nextInt();
		int[] num = new int[10001];
		int cnt = 0, min = 0;
		
		num[1]=1;
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if(num[i] == 0) {
				int j = 1;
				while(++j*i <= N) {
					num[j*i] = 1;
				}
			}
		}
		
		for (int i = M; i <= N; i++) {
			if(num[i] == 0) {
				cnt += i;
				if(min == 0) min = i;
			}
		}
		
		System.out.print(cnt==0 ? -1 : (cnt+"\n"+min+"\n"));
	}

}
