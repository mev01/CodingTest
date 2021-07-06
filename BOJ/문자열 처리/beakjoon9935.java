import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon9935 {
	static String str, bomb;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		str = br.readLine();
		bomb = br.readLine();
		arr = new int[str.length()];
		
		int idx = -1;
		
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == bomb.charAt(idx + 1)) {
				
			}
			else if(idx >= 0) {
				idx = bomb.indexOf(str.charAt(i));
			}
			
			arr[i] = idx;
			
			sb.append(str.charAt(i));
		}
	}
}
