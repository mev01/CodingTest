import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int[][] costArr = new int[N + 1][N + 1];
        for(int[] cost : costArr){
            Arrays.fill(cost, 1 << 20);
        }
        for(int[] r : road){
            if(costArr[r[0]][r[1]] > r[2]){
                costArr[r[0]][r[1]] = r[2];
                costArr[r[1]][r[0]] = r[2];
            }
        }
        
        boolean[] changed = new boolean[N + 1];
        int[] lowestCost = new int[N + 1];
        Arrays.fill(lowestCost, 1 << 20);
        
        int node = 1;
        lowestCost[1] = 0;
        
        while(true){
            changed[node] = true;
            for(int j = 1; j <= N; j++){
                if(j != node && lowestCost[j] > lowestCost[node] + costArr[node][j]){
                    lowestCost[j] = lowestCost[node] + costArr[node][j];
                }
            }
            
            int min = Integer.MAX_VALUE;
            for(int j = 1; j <= N; j++){
                if(!changed[j] && min > lowestCost[j]){
                    node = j;
                    min = lowestCost[j];
                }
            }
            
            boolean last = true;
            for(int j = 1; j <= N; j++){
                if(!changed[j]) last = false;
            }
            if(last) break;
        }
        
        // System.out.println(Arrays.toString(lowestCost));

        int answer = 0;
        for(int j = 1; j <= N; j++){
            if(lowestCost[j] <= K) answer++;
        }
        return answer;
    }
}