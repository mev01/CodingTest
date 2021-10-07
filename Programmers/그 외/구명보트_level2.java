import java.util.*;

class Solution {
    static class Boat implements Comparable<Boat>{
        int wei, cnt;
        
        public Boat(int wei, int cnt){
            this.wei = wei;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Boat b){
            if(this.cnt == b.cnt) return this.wei - b.wei;
            return this.cnt - b.cnt;
        }
    }
    
    public int solution(int[] people, int limit) {
        Integer[] arr = Arrays.stream( people ).boxed().toArray( Integer[]::new );
        Arrays.sort(arr, Collections.reverseOrder());
        PriorityQueue<Boat> que = new PriorityQueue<>();
        
        for(int wei : arr){
            if(que.isEmpty() || que.peek().wei + wei > limit || que.peek().cnt == 2){
                que.offer(new Boat(wei, 1));
            }
            else if(que.peek().cnt == 1){
                que.offer(new Boat(que.poll().wei + wei, 2));
            }
            // System.out.println(que.peek().cnt + " " + que.peek().wei);
        }
        
        return que.size();
    }
}