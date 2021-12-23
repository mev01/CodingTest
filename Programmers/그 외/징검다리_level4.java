import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int s = 0, e = distance;
        while(s < e){
            int m = (s + e + 1) / 2;
            int removeCnt = 0, prev = 0;
            
            for(int i = 0; i < rocks.length; i++){
                if(rocks[i] - prev < m) removeCnt++;
                else prev = rocks[i];
            }
            
            if(distance - prev < m) removeCnt++;
            
            if(removeCnt > n) e = m - 1;
            else s = m;
        }
        
        return s;
    }
}