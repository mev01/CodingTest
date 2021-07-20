import java.util.*;

class Solution {
    static PriorityQueue<Integer> que;
    
    public int solution(int[] scoville, int K) {
        int answer = 0, len = scoville.length;
        que = new PriorityQueue<Integer>();
        
        for(int i = 0; i < len; i++) que.offer(scoville[i]);
        while(que.peek() < K){
            if(que.size() == 1) return -1;
            
            int lowestFood = que.poll();
            int secondlowestFood = que.poll();
            que.offer(lowestFood + secondlowestFood * 2);
            ++answer;
        }
        
        return answer;
    }
}