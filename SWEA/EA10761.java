import java.io.*;
import java.util.StringTokenizer;

public class EA10761 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		for (int i = 1; i <= TC; i++) {
			String test = br.readLine();
			StringTokenizer st = new StringTokenizer(test);
			int numInst = Integer.parseInt(st.nextToken());
			int preO = 1, preB = 1, ans = 0, lastO = 0, lastB = 0;
			String nowRobot = "a";
			
			for (int j = 0; j < numInst; j++) {
				nowRobot = st.nextToken();
				int dis = Integer.parseInt(st.nextToken());
				
				// 다음의 2가지 경우로 나누어서 생각
				// 1. 로봇이 다른 로봇이 명령을 처리할 때까지 기다리는 경우
				// 2. 로봇이 기다리지 않고 움직이다가 바로 버튼을 누르는 경우
				// 1의 경우에는 기다렸다가 바로 처리하면 되므로 그전 명령어를 처리한 시간+1
				// 2의 경우에는 다른 로봇이 명령어를 처리한 시간을 무시할 수 있으므로 예전 자리에서 움직여서 누른 시간을 계산 
				if(nowRobot.equals("O")){
					int time = ( Math.abs(dis - preO) <= (ans - lastO)) ? 
							ans + 1 : (Math.abs(dis - preO) + 1 + lastO);
					ans = time;
					preO = dis;
					lastO = ans;
				}
				else if(nowRobot.equals("B")){
					int time = ( Math.abs(dis - preB) <= (ans - lastB)) ?
							ans + 1 : (Math.abs(dis - preB) + 1 + lastB);
					ans = time;
					preB = dis;
					lastB = ans;
				}
			}
			
			bw.write("#"+i+" "+ans+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
