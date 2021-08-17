import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        
        PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        que.offer(jobs[0]);
                          
        int time = que.peek()[0], idx = 1;                                                    
        while(true){
            if(que.isEmpty()){
                if(idx >= jobs.length) break;
                que.offer(jobs[idx]);
                time = jobs[idx++][0];
            }
            int[] job = que.poll();
            
            answer += (time - job[0] + job[1]);
            time += job[1];
            // System.out.println("after " + time);
            
            while(idx < jobs.length && jobs[idx][0] <= time){
                que.offer(jobs[idx++]);
            }
        }
        
        return answer/jobs.length;
    }
}