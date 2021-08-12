import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int[][] logTimes = transToTimes(lines);
        
         for(int i = 0; i < logTimes.length; i++){
            int cnt = 0;
            int endTime = logTimes[i][1];
            
            for(int j = i; j < logTimes.length; j++){
                if(logTimes[j][0] <= endTime + 999) ++cnt;
            }
            
            answer = Math.max(cnt, answer);
        }
        
        return answer;
    }
    
    int[][] transToTimes(String[] lines){
        StringTokenizer st;
        int[][] logTimes = new int[lines.length][2];
        
        for(int i = lines.length - 1; i >= 0; i--){
            st = new StringTokenizer(lines[i]);
            st.nextToken();
            String timeLine = st.nextToken();
            String lenLine = st.nextToken();
            
            st = new StringTokenizer(timeLine, ":");
            int h = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            float f = Float.parseFloat(st.nextToken());
            int s = (int) Math.ceil(f * 1000);
            int time = h * 60 * 60 * 1000 + m * 60 * 1000 + s;
            
            lenLine = lenLine.substring(0, lenLine.length() - 1);
            int len = (int) (Math.ceil(Float.parseFloat(lenLine) * 1000));
            
            logTimes[i][0] = time - len + 1;
            logTimes[i][1] = time;
        }
        
        return logTimes;
    }
}