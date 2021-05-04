import java.io.*;

public class EA1223 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= 10; i++) {
			int sum = 0, temp = 0;
			int len = Integer.parseInt(br.readLine());
			String str = br.readLine();
			
			for (int j = 0; j < len; j++) {
				Character ch = str.charAt(j);
				
				if(ch == '*'){
					temp = (temp * (str.charAt(++j)-'0'));
				}
				else if(ch == '+'){
					sum += temp;
				}
				else{
					temp = str.charAt(j)-'0';
				}
			}
			sum += temp;
			
			sb.append("#"+i+" "+sum+"\n");
		}
		System.out.println(sb.toString());
	}
}