import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class EA9280 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[] cost, wei, carNum;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			Queue<Integer> waitCar = new LinkedList<Integer>();
			PriorityQueue<Integer> waitNum = new PriorityQueue<>();
			
			cost = new int[n+1];
			wei = new int[m+1];
			carNum = new int[m+1];
			int ans = 0;
			
			for (int i = 1; i <= n; i++) {
				cost[i] = Integer.parseInt(br.readLine());
				waitNum.add(i);
			}
			for (int i = 1; i <= m; i++) {
				wei[i] = Integer.parseInt(br.readLine());
			}
			
			for (int i = 0; i < 2*m; i++) {
				int car = Integer.parseInt(br.readLine());
				
				if(car < 0) {
					waitNum.add(carNum[Math.abs(car)]);	//해당 번호를 돌려줌
					if(!waitCar.isEmpty())	//기다리고 있는 차가 있을때 힙에서 가장 작은 수를 넣음
						carNum[waitCar.poll()] = waitNum.poll();
				}
				else {
					if(waitNum.isEmpty()) {	//주차장 자리가 없으면 큐에 삽입
						waitCar.add(car);
					}
					else {	//주차장 자리가 있으면 해당 번호를 힙에서 빼서 넣음
						carNum[car] = waitNum.poll();
					}
				}
			}
			
			for (int i = 1; i <= m; i++) {
				ans += wei[i]*cost[carNum[i]];
			}
			sb.append("#"+tc+" "+ans+"\n");
		}
		System.out.print(sb.toString());
	}
}