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
		
		int []arr = new int[N+3];
		arr[3] = 1; arr[5] = 1;
		
		for (int i = 3; i <= N-3; i++) {
			if(arr[i] != 0) {
				if(arr[i+3] == 0) arr[i+3] = arr[i]+1;
				else arr[i+3] = (arr[i]+1 < arr[i+3]) ? arr[i]+1 : arr[i+3];
				if(arr[i+5] == 0) arr[i+5] = arr[i]+1;
				else arr[i+5] = (arr[i]+1 < arr[i+5]) ? arr[i]+1 : arr[i+5];
			}
		}
		bw.write((arr[N]==0 ? -1 : arr[N]) + "\n");
		bw.flush();
		bw.close();
	}

}
