import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon1991 {
	static int[][] node = new int[26][2];
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String line = br.readLine();
			node[line.charAt(0)-'A'][0] = line.charAt(2)-'A';
			node[line.charAt(0)-'A'][1] = line.charAt(4)-'A';
		}
		
		pre(0);
		sb.append("\n");
		in(0);
		sb.append("\n");
		post(0);
		sb.append("\n");
		System.out.print(sb.toString());
	}
	
	static void pre(int index) {
		sb.append((char)(index + 'A'));
		if(node[index][0] >= 0) pre(node[index][0]);
		if(node[index][1] >= 0) pre(node[index][1]);
	}
	static void in(int index) {
		if(node[index][0] >= 0) in(node[index][0]);
		sb.append((char)(index + 'A'));
		if(node[index][1] >= 0) in(node[index][1]);
	}
	static void post(int index) {
		if(node[index][0] >= 0) post(node[index][0]);
		if(node[index][1] >= 0) post(node[index][1]);
		sb.append((char)(index + 'A'));
	}
}