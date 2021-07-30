import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        LinkedList<String> list = new LinkedList<String>();
        int seq = -1, len = cities.length, answer = 0;
        
        while(++seq < len){
            String city = cities[seq].toUpperCase();
            int idx = list.indexOf(city);
            
            if(idx == -1){
                answer += 5;
                list.addLast(city);
            }
            else{
                answer += 1;
                list.remove(idx);
                list.addLast(city);
            }
            
            if(list.size() > cacheSize) list.poll();
        }
        
        return answer;
    }
}