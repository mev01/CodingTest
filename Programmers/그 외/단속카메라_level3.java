import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>(){
           @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });
        
        int answer = 1, dis = routes[0][1];
        
        for(int[] route : routes){
            if(route[0] > dis){
                ++answer;
                dis = route[1];
            }
        }
        
        return answer;
    }
}