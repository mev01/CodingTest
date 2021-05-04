import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA7732 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testcase = Integer.parseInt(br.readLine());
		for (int i = 1; i <= testcase; i++) {
			StringTokenizer currentTime = new StringTokenizer(br.readLine(), ":");
			StringTokenizer meetingTime = new StringTokenizer(br.readLine(), ":");
			
			int currentHour = Integer.parseInt(currentTime.nextToken());
			int currentMin = Integer.parseInt(currentTime.nextToken());
			int currentSec = Integer.parseInt(currentTime.nextToken());
			int meetingHour = Integer.parseInt(meetingTime.nextToken());
			int meetingtMin = Integer.parseInt(meetingTime.nextToken());
			int meetingSec = Integer.parseInt(meetingTime.nextToken());
			
			int ansHour = meetingHour - currentHour;
			int ansMin = meetingtMin - currentMin;
			int ansSec = meetingSec - currentSec;
			
//			if(ansHour<0 || ansMin<0 || ansSec<0) {
//				ansHour += 23;
//				ansMin += 59;
//				ansSec += 60;
//			}
			
			if(ansSec >= 60) {
				ansSec -= 60;
				ansMin += 1;
			}
			else if(ansSec < 0) {
				ansSec += 60;
				ansMin -= 1;
			}
			if(ansMin >= 60) {
				ansMin -= 60;
				ansHour += 1;
			}
			else if(ansMin < 0) {
				ansMin += 60;
				ansHour -= 1;
			}
			if(ansHour < 0) {
				ansHour += 24;
			}
			
			sb.append("#"+i+" "+(ansHour/10==0 ? "0"+ansHour : ansHour)+":"+(ansMin/10==0 ? "0"+ansMin : ansMin)+":"+(ansSec/10==0 ? "0"+ansSec : ansSec)+"\n");
		}
		System.out.println(sb.toString());
	}

}
