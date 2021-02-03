import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjoon2839 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		int []arr = new int[N+6];
		Arrays.fill(arr, Integer.MAX_VALUE);
		arr[3] = 1; arr[5] = 1;
		
		for (int i = 3; i <= N; i++) {
			if(arr[i] != Integer.MAX_VALUE) {
				arr[i+3]=arr[i]+1 < arr[i+3] ? arr[i]+1 : arr[i+3];
				arr[i+5]=arr[i]+1 < arr[i+5] ? arr[i]+1 : arr[i+5];
			}
		}
		bw.write(((int)arr[N]==Integer.MAX_VALUE ? -1 : arr[N]) + "\n");
		bw.flush();
		bw.close();
	}

}
