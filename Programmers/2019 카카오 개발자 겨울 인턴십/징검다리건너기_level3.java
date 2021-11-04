import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int s = 0, e = k - 1;
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < k; i++){
            list.add(stones[i]);
        }
        
        Collections.sort(list);
        
        int max = list.get(list.size() - 1);
        int answer = max;
        for(e = e + 1; e < stones.length; e++, s++){
            int sIdx = Collections.binarySearch(list, stones[s]);
            list.remove(sIdx);
            if(list.size() == 0) max = 0;
            else if(stones[s] == max) max = list.get(list.size() - 1);
            
            int eIdx = Collections.binarySearch(list, stones[e]);
            list.add((eIdx < 0) ? (eIdx + 1) * -1 : eIdx, stones[e]);
            if(max < stones[e]) max = stones[e];
            
            if(max < answer) answer = max;
        }
        
        return answer;
    }
}