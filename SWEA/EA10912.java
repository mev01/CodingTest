import java.io.*;

public class EA10912{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			bw.write("#"+i+" ");
			String test = br.readLine();
			int notLonely = 1;
			int[] count = new int[26];
			
			//가져온 문자들을 숫자로 치환 a->0, b->1, ...
			//치환한 숫자들을 배열의 인덱스로 생각하고 배열의 값들을 ++
			for (int j = 0; j < test.length(); j++) {
				count[(int)(test.charAt(j)-'a')]++;
			}
			
			//배열의 값이 짝수가 아니라면 인덱스에 해당하는 문자를 한번 출력
			for (int j = 0; j < 26; j++) {
				if(count[j]%2 != 0){
					bw.write(j+'a');
					notLonely = 0;
				}
			}
			//출력된 수가 없으면 Good을 한번 출력
			if(notLonely == 1) bw.write("Good");
			bw.write("\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
}
