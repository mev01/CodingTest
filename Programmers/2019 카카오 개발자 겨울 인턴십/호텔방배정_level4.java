import java.util.*;

class Solution {
    static HashMap<Long, Long> map;
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        map = new HashMap<>();
        
        for(int i = 0; i < room_number.length; i++){
            long num = room_number[i];
            
            if(!map.containsKey(num)){
                map.put(num, num + 1);
                answer[i] = num ;
            }
            else{
                answer[i] = Search(num) - 1;
            }
        }
        
        return answer;
    }
    
    long Search(long num){
        if(!map.containsKey(num)){
            map.put(num, num + 1);
            
            return num + 1;
        }
        
        long n = Search(map.get(num));
        map.put(num, n);
        return n;
    }
}