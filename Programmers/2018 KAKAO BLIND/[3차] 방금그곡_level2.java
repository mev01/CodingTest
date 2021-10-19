import java.util.*;

class Solution {
    static class Music implements Comparable<Music>{
        int idx, time;
        String melody, subject;
        
        public Music(int idx, int time, String melody, String subject){
            this.idx = idx;
            this.time = time;
            this.melody = melody;
            this.subject = subject;
        }
        
        @Override
        public int compareTo(Music o){
            if(this.time == o.time) return this.idx - o.idx;
            return o.time - this.time;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        ArrayList<Music> list = new ArrayList<Music>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m.length(); i++){
            sb.append(m.charAt(i));
            if(m.charAt(i) >= 'A' && m.charAt(i) <= 'Z' && (i == m.length() - 1 || m.charAt(i + 1) != '#'))
                sb.append('*');
        }
        m = sb.toString();
        
        for(int i = 0; i < musicinfos.length; i++){
            StringTokenizer st = new StringTokenizer(musicinfos[i], ",");
            int time = changeTime(st.nextToken(), st.nextToken());
            String subject = st.nextToken();
            String melody = changeMelody(st.nextToken(), time);
            
            if(melody.contains(m)){
                list.add(new Music(i, time, melody, subject));
            }
            // System.out.println(time + " " + melody);
        }
        
        Collections.sort(list);
        
        return list.size() > 0 ? list.get(0).subject : "(None)";
    }
    
    int changeTime(String time1, String time2){
        int min = Integer.parseInt(time2.substring(0, 2)) - Integer.parseInt(time1.substring(0, 2));
        int sec = Integer.parseInt(time2.substring(3, 5)) - Integer.parseInt(time1.substring(3, 5));
        
        return min * 60 + sec;
    }
    
    String changeMelody(String preMelody, int time){
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while(time-- > 0){
            sb.append(preMelody.charAt(idx));
            idx = (idx + 1) % preMelody.length();
            if(preMelody.charAt(idx) == '#'){
                sb.append(preMelody.charAt(idx));
                idx = (idx + 1) % preMelody.length();
            }
            else{
                sb.append("*");
            }
        }
        
        return sb.toString();
    }
}
