import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0, hei = triangle.length;
        
        for(int i = 1; i < hei; i++){
            triangle[i][0] = triangle[i][0] + triangle[i - 1][0];
            triangle[i][i] = triangle[i][i] + triangle[i - 1][i - 1];
            
            for(int j = 1; j < i; j++){
                triangle[i][j] = triangle[i][j] + Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }
        
        for(int i : triangle[hei - 1]){
            answer = Math.max(answer, i);
        }
        return answer;
    }
}