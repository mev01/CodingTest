import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class EA5948 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[] num = new int[7];
		int[] sum;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			sum = new int[35];
			int size = 0, cnt = 1;
			
			for (int i = 0; i < 7; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int x = 0; x < 7; x++)
				for (int y = x+1; y < 7; y++)
					for (int z = y+1; z < 7; z++)
						sum[size++] = num[x]+num[y]+num[z];
			
			Arrays.sort(sum);
			int i;
			for (i = 33; i >= 0; i--) {
				if(sum[i] == sum[i+1]) continue;
				if(++cnt == 5) break;
			}
			
			sb.append("#"+tc+" "+sum[i]+"\n");
		}
		System.out.println(sb.toString());
	}
}