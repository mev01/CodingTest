import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class baekjoon2309 {
	static int cnt = 0;
	static int[] height = new int[9];
	static boolean[] check = new boolean[9];
	static ArrayList<Integer> list = new ArrayList<>();
	
	static void func(int index, int cnt, int sum) {
		if(cnt==7 && sum==100) {
			for (int i = 0; i < 7; i++) {
				System.out.println(list.get(i));
			}
			System.exit(0);
		}
		if(index == 9) {
			return;
		}
		
		func(index+1, cnt, sum);
		list.add(height[index]);
		func(index+1, cnt+1, sum + height[index]);
		list.remove(list.size()-1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(height);
		func(0,0,0);
	}

}
