class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length, M = board[0].length;
        int[][] damage = new int[N + 1][M + 1];
        
        for(int[] p : skill){
            if(p[0] == 1) p[5] *= -1;
            
            int r1 = p[1], c1 = p[2], r2 = p[3], c2 = p[4];
            damage[r1][c1] += p[5];
            damage[r1][c2 + 1] += p[5] * -1;
            damage[r2 + 1][c1] += p[5] * -1;
            damage[r2 + 1][c2 + 1] += p[5];
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                damage[i][j + 1] += damage[i][j];
            }
        }
        for(int j = 0; j < M; j++){
            for(int i = 0; i < N; i++){
                damage[i + 1][j] += damage[i][j];
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(board[i][j] + damage[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}