import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int s = 0, e = people.length - 1, ans = 0;
        
        Arrays.sort(people);
        while(s < e){
            if(people[s] + people[e] <= limit) ++s;
            --e;
            ++ans;
        }
        
        return s == e ? ans + 1 : ans;
    }
}