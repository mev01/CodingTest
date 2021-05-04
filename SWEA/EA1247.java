import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA1247 {
	static int comX, comY, homeX, homeY, numClient, ans = Integer.MAX_VALUE, tempAns = 0, allcnt =0;;
	static int[] clientX = new int[10], clientY = new int[10];
	static boolean[] check = new boolean[10];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			numClient = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			comX = Integer.parseInt(st.nextToken());
			comY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < numClient; i++) {
				clientX[i] = Integer.parseInt(st.nextToken());
				clientY[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < numClient; i++) {
				check[i] = true;
				tempAns += (Math.abs(clientX[i]-comX) + Math.abs(clientY[i]-comY));
				goHome(1,i);
				check[i] =false;
				tempAns -= (Math.abs(clientX[i]-comX) + Math.abs(clientY[i]-comY));
			}
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void goHome(int cnt, int lastIdx) {
		if(cnt == numClient) {
			tempAns += (Math.abs(clientX[lastIdx]-homeX) + Math.abs(clientY[lastIdx]-homeY));
			if(ans > tempAns) ans = tempAns;
			tempAns -= (Math.abs(clientX[lastIdx]-homeX) + Math.abs(clientY[lastIdx]-homeY));
			return;
		}
		if(tempAns >= ans) return;
		
		for (int i = 0; i < numClient; i++) {
			if(!check[i]) {
				tempAns += (Math.abs(clientX[i]-clientX[lastIdx]) + Math.abs(clientY[i]-clientY[lastIdx]));
				check[i] = true;
				goHome(cnt+1, i);
				tempAns -= (Math.abs(clientX[i]-clientX[lastIdx]) + Math.abs(clientY[i]-clientY[lastIdx]));
				check[i] = false;
			}
		}
	}

}
