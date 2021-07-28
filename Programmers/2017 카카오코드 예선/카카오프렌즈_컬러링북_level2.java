class Solution {
    static int M, N, numberOfArea, maxSizeOfOneArea;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static long[][] Picture;
    static boolean[][] visited;
    
    public int[] solution(int m, int n, int[][] picture) {
        // picture 탐색하다가 탐색하지 않은 어떤 수 나오면
        // 해당 영역 전부 탐색, 방문처리
        M = m;
        N = n;
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        Picture = new long[m][n];
        visited = new boolean[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                Picture[i][j] = (long)picture[i][j];
            }
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] > 0 && !visited[i][j]){
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, DFS(i, j));
                } 
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
                                                
    int DFS(int r, int c){
        visited[r][c] = true;
        int cnt = 1;
        
        for(int dir = 0; dir < 4; dir++){
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            if(nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
            if(visited[nr][nc]) continue;
            if(Picture[r][c] != Picture[nr][nc]) continue;
            cnt += DFS(nr, nc);
        }
        
        return cnt;
    }                                            
}