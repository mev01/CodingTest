import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon14719 {
	static int H, W, ans = 0;
	static int[] hArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		hArr = new int[W];
		
		for(int i = 0; i < W; i++) {
			hArr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int h = 1; h <= H; h++) {
			int idx = -1;
			
			for(int w = 0; w < W; w++) {
				if(hArr[w] >= h) {
					if(idx >= 0) {
						ans += w - idx - 1;
					}
					idx = w;
				}
			}
		}
		
		System.out.println(ans);
	}

}