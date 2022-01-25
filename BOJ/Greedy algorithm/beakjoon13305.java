import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon13305 {
	static int cityCnt;
	static int[] lenArr, priceArr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		cityCnt = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		lenArr = new int[cityCnt];
		for(int i = 0; i < cityCnt - 1; i++) {
			lenArr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		priceArr = new int[cityCnt + 1];
		for(int i = 0; i < cityCnt; i++) {
			priceArr[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		long minPrice = priceArr[0], ans = 0;
		while(idx < cityCnt - 1) {
			if(priceArr[idx] < minPrice) minPrice = priceArr[idx];
			
			ans += minPrice * lenArr[idx];
			idx++;
		}
		
		System.out.println(ans);
	}

}
