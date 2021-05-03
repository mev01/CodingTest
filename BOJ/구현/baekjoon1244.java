import java.io.*;
import java.util.Scanner;

public class baekjoon1244 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] swit = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			swit[i] = sc.nextInt();
		}
		int numPer = sc.nextInt();
		for (int i = 0; i < numPer; i++) {
			int gender = sc.nextInt();
			int numSwit = sc.nextInt();
			
			if(gender == 1){
				for (int j = 1; j <= N/numSwit; j++) {
					swit[j*numSwit] = 1 - swit[j*numSwit];
				}
			}
			else if(gender == 2){
				swit[numSwit] = 1 - swit[numSwit];
				for (int j = 1; numSwit-j > 0 && numSwit+j <= N; j++) {
					if(swit[numSwit-j] == swit[numSwit+j]){
						swit[numSwit-j] = 1 - swit[numSwit-j];
						swit[numSwit+j] = 1 - swit[numSwit+j];
					}
					else
						break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(swit[i]+" ");
			if(i%20 == 0) sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}

}
