import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon1068 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[T];
		boolean[] check = new boolean[T];
		
		for (int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int del = Integer.parseInt(br.readLine());
		arr[del] = -2;	//삭제할 노드의 부모가 리프노드가 되는것을 대비
		
		//자신의 부모를  true로 변경 -> leaf 노드만 false
		for (int i = 0; i < T; i++) {
			if(arr[i] < 0) continue;
			check[arr[i]] = true;
		}
		//리프 노드만 조상들을 탐색하여 del이 있는지 확인
		//없으면 ans+1
		int ans = 0;
		for (int i = 0; i < T; i++) {
			if(!check[i]) {
				int tmp = i, isDel = 0;
				while(tmp >= 0) {
					if(tmp == del) {
						isDel = 1;
						break;
					}
					tmp = arr[tmp];
				}
				if(isDel == 0) ans++;
			}
		}
		
		System.out.println(ans);
	}
}