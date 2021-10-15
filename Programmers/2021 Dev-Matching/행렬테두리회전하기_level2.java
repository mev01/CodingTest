import java.util.*;

class Solution {
    static int R, C, min;
    static int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        R = rows;
        C = columns;
        
        int[] answer = new int[queries.length];
        int[][] map = new int[R + 1][C + 1];
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                map[i][j] = (i - 1) * C + j;
            }
        }
        
        for(int i = 0; i < queries.length; i++){
            min = Integer.MAX_VALUE;
            map = Rotate(map, queries[i]);
            
            answer[i] = min;
        }
        
        return answer;
    }
    
    int[][] cloneMap(int[][] map){
        int[][] nMap = new int[R + 1][C + 1];
        for(int i = 0; i <= R; i++){
            nMap[i] = map[i].clone();
        }
        
        return nMap;
    }
    
    int[][] Rotate(int[][] map, int[] query){
        int[][] nMap = cloneMap(map);
        
        for(int i = query[0]; i <= query[2]; i++){
            for(int j = query[1]; j <= query[3]; j++){
                if(!(i == query[0] || i == query[2] || j == query[1] || j == query[3])) continue;
                
                if(min > map[i][j]) min = map[i][j];
                if(i == query[0] && j != query[3]){
                    nMap[i][j + 1] = map[i][j];
                }
                else if(i != query[2] && j == query[3]){
                    nMap[i + 1][j] = map[i][j];
                }
                else if(i == query[2] && j != query[1]){
                    nMap[i][j - 1] = map[i][j];
                }
                else if(i != query[0] && j == query[1]){
                    nMap[i - 1][j] = map[i][j];
                }
            }
        }
        
        return nMap;
    }
}