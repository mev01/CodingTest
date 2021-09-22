import java.util.*;

class Solution {
    static int len;
    static boolean[] notRemained;
    static Food[] foodArr;
    
    static class Food implements Comparable<Food>{
        int num, value;
        
        public Food(int num, int value){
            this.num = num;
            this.value = value;
        }
        
        @Override
        public int compareTo(Food o){
            return this.value - o.value;
        }
    }
    
    public int solution(int[] food_times, long k) {
        len = food_times.length;
        notRemained = new boolean[len];
        foodArr = new Food[len];
        
        for(int j = 0; j < len; j++){
            foodArr[j] = new Food(j, food_times[j]);
        }
        
        Arrays.sort(foodArr);
        
        long pre = 0, time = 0;
        for(int i = 0; i < len; i++){
            long num = (len - i) * (foodArr[i].value - pre);
            if(time + num > k){
                long st = (k - time) % (len - i) + 1;
                for(int j = 0; j < len; j++){
                    if(!notRemained[j]) --st;
                    if(st == 0) return j + 1;
                }
            }
            
            time += num;
            pre = foodArr[i].value;
            notRemained[foodArr[i].num] = true;
        }
        
        return -1;
    }
}