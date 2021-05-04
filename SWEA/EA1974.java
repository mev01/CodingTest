import java.io.*;
import java.util.StringTokenizer;

public class EA1974 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			int[] vertical = new int[9]; //세로줄
			int[] hori = new int[9];	//가로줄
			int[] grid = new int[9];	//3*3격자
			int isPossible = 1;
			
			//입력하면서 배열에 더하기
			for (int x = 0; x < 9; x++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int y = 0; y < 9; y++) {
					int tmp = Integer.parseInt(st.nextToken());
					vertical[x] += tmp;
					hori[y] += tmp;
					grid[x/3*3+y/3] += tmp;
				}
				
			}
			
			//45(1-9까지 더한수)가 되는지 확인
			for (int j = 0; j < 9; j++) {
				if(vertical[j] != 45 || hori[j] != 45 || grid[j] != 45) {
					isPossible = 0;
					break;
				}
			}
			
			sb.append("#"+i+" "+isPossible+"\n");
		}
		System.out.println(sb.toString());
	}

}
