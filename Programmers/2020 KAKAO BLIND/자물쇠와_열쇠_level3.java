import java.util.*; 

class Solution {
    static int N, M;
    static int[][][] Key = new int[4][][];
    static int[][] keyArr, lockArr;
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        lockArr = new int[N + 2 * M - 2][N + 2 * M - 2];
        
        Key[0] = key;
        Key[1] = Rotate(Key[0]);
        Key[2] = Rotate(Key[1]);
        Key[3] = Rotate(Key[2]);
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                lockArr[i + M - 1][j + M - 1] = lock[i][j];
            }
        }
        
        for(int i = 0; i <= lockArr.length - M; i++){
            for(int j = 0; j <= lockArr.length - M; j++){
                for(int num = 0; num < 4; num++){
                    keyArr = new int[N + 2 * M - 2][N + 2 * M - 2];
                    fill(i, j, num);
                    
                    if(isPoss()) return true;
                }
            }
        }
        
        
        return false;
    }
    
    void fill(int r, int c, int num){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                keyArr[r + i][c + j] = Key[num][i][j];
            }
        }
    }
    
    boolean isPoss(){
        for(int i = M - 1; i < N + M - 1; i++){
            for(int j = M - 1; j < N + M - 1; j++){
                if((lockArr[i][j] ^ keyArr[i][j]) == 0) return false;
            }
        }
        
        return true;
    }
    
    int[][] Rotate(int[][] arr){
        int[][] rotate = new int[M][M];
        
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                rotate[j][M - 1 - i] = arr[i][j];
            }
        }
        
        return rotate;
    }
}