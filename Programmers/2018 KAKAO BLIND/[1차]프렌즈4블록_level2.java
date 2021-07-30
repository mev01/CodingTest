class Solution {
    static int M, N, answer;
    static int[] dr = {0, 1, 1}, dc = {1, 0, 1};
    static boolean[][] isdeleted;
    static char[][] board;
    
    public int solution(int m, int n, String[] input) {
        board = new char[m][n];
        answer = 0;
        M = m;
        N = n;
        
        for(int i = 0; i < m; i++){
            board[i] = input[i].toCharArray();
        }
        
        while(true){
            int cnt = 0;
            isdeleted = new boolean[m][n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(board[i][j] != '0' && canDelete(i, j)) ++cnt;
                }
            }
            
            if(cnt == 0) break;
            Delete();
            Fall();
        }
        
        return answer;
    }
                       
    boolean canDelete(int r, int c){
        char ch = board[r][c];
        int cnt = 1;
        
        for(int dir = 0; dir < 3; dir++){
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            if(nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
            if(board[nr][nc] == ch) ++cnt;
        }
        
        if(cnt < 4) return false;
        isdeleted[r][c] = true;
        for(int dir = 0; dir < 3; dir++){
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            isdeleted[nr][nc] = true;
        }
        return true;
    }
    
    void Delete(){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(isdeleted[i][j]){
                    board[i][j] = '0';
                    ++answer;
                } 
            }
        }
    }
    
    void Fall(){
        for(int j = 0; j < N; j++){
            int idx = 0, nIdx = -1;
            for(int i = M - 1; i >= 0; i--){
                if(nIdx == -1 && board[i][j] == '0') nIdx = i;
                if(nIdx >= 0 && board[i][j] != '0'){
                    board[nIdx--][j] = board[i][j];
                    board[i][j] = '0';
                }
            }
        }
    }
}