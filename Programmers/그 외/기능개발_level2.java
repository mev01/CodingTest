import java.util.*;

class Solution {
    static Queue<Integer> que;
    public int[] solution(int[] progresses, int[] speeds) {
        que = new LinkedList<>();
        int len = progresses.length;
        
        for(int i = 0; i < len; ++i){
            int job = 100 - progresses[i];
            que.offer((job + speeds[i] - 1) / speeds[i]);
        }
        
        List<Integer> list = new LinkedList<>();
        while(!que.isEmpty()){
            int firstJob = que.peek(), cnt = 0;
            
            while(!que.isEmpty() && que.peek() <= firstJob){
                que.poll();
                ++cnt;
            }
            list.add(cnt);
        }
        
        return list.stream().mapToInt(i->i).toArray();
    }
}