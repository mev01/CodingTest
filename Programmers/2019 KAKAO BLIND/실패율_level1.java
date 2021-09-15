import java.util.*;

class Solution {
    static class Stage implements Comparable<Stage>{
        int num;
        float rate;
        
        public Stage(int num, float rate){
            this.num = num;
            this.rate = rate;
        }
        
        @Override
        public int compareTo(Stage o1){
            if(this.rate == o1.rate) return this.num - o1.num;
            else if(this.rate < o1.rate) return 1;
            return -1;
        }
    }
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] userArr = new int[N + 1];
        Stage[] stageArr = new Stage[N];
        
        for(int num : stages){
            if(num <= N) ++userArr[num];
        }
        
        int sum = stages.length;
        for(int i = 0; i < N; i++){
            float rate;
            if(sum == 0) rate = 0;
            else rate = (float)userArr[i + 1] / sum;
            System.out.println(rate);
            
            stageArr[i] = new Stage(i + 1, rate);
            sum -= userArr[i + 1];
        }
        
        Arrays.sort(stageArr);
        
        for(int i = 0; i < N; i++){
            answer[i] = stageArr[i].num;
        }
        
        return answer;
    }
}