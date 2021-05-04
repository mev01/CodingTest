import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EA1228 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 1; i <= 10; i++) {	
			ArrayList<Integer> codeList = new ArrayList<>();
			
			int codeNum = Integer.parseInt(br.readLine());	//첫 번째 줄
			
			StringTokenizer st1  = new StringTokenizer(br.readLine()," ");	//두 번째 줄
			for (int j = 0; j < codeNum; j++) {
				codeList.add(Integer.parseInt(st1.nextToken()));
			}
			
			int instNum = Integer.parseInt(br.readLine());	//세 번째 줄
			
			StringTokenizer st2  = new StringTokenizer(br.readLine()," ");	//네 번째 줄
			for (int j = 0; j < instNum; j++) {
				String inst = st2.nextToken();
				if(inst.equals("I")){
					int x = Integer.parseInt(st2.nextToken());
					int y = Integer.parseInt(st2.nextToken());
					for (int k = 0; k < y; k++) {
						int s = Integer.parseInt(st2.nextToken());
						codeList.add(x++, s);
					}
				}
			}
			
			bw.write("#"+i+" ");
			for (int j = 0; j < 10; j++) {
				bw.write(codeList.get(j)+" ");
			}
			bw.write("\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
