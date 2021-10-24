import java.util.*;

class Solution {
    static Bus[] busArr;
    
    static class Bus{
        Time time;
        ArrayList<Time> list;
        
        public Bus(Time time){
            this.time = time;
            list = new ArrayList<Time>();
        }
    }
    static class Time{
        int h, m;
        
        public Time(int h, int m){
            if(m >= 60){
                h += m / 60;
                m = m % 60;
            }
            this.h = h;
            this.m = m;
        }
    }
    public String solution(int n, int t, int m, String[] timetable) {
        busArr = new Bus[n];
        Time busTime = new Time(9, 0);
        for(int i = 0; i < n; i++){
            busArr[i] = new Bus(new Time(busTime.h, busTime.m + (t * i)));
        }
        
        int busIdx = 0, passIdx = 0;
        Arrays.sort(timetable);
        while(busIdx < n && passIdx < timetable.length){
            int H = Integer.parseInt(timetable[passIdx].substring(0, 2));
            int M = Integer.parseInt(timetable[passIdx].substring(3, 5));
            
            if((H < busArr[busIdx].time.h || (H == busArr[busIdx].time.h && M <= busArr[busIdx].time.m)) && busArr[busIdx].list.size() < m){
                busArr[busIdx].list.add(new Time(H, M));
                passIdx++;
            } 
            else
                busIdx++;
        }
        
        
        // System.out.println(busArr[n - 1].list.size() != 0 ? busArr[n - 1].list.get(busArr[n - 1].list.size() - 1).m : "ab");
        
        String answer = "";
        if(busArr[n - 1].list.size() != m){
            answer = String.format("%02d", busArr[n - 1].time.h) + ":" + String.format("%02d", busArr[n - 1].time.m);
        }
        else{
            Time time = busArr[n - 1].list.get(busArr[n - 1].list.size() - 1);
            time.m -= 1;
            if(time.m == -1){
                time.h -= 1;
                time.m = 59;
            } 
            
            answer = answer = String.format("%02d", time.h) + ":" + String.format("%02d", time.m);
        }
        
        
        return answer;
    }
}