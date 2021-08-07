import java.util.*;

class Solution {
    static int N, M;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static class Pos{
        int r, c, cnt;
        
        public Pos(int r, int c, int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        visited = new boolean[N][M];
        Queue<Pos> que = new LinkedList<Pos>();
        
        que.offer(new Pos(0, 0, 1));
        visited[0][0] = true;
        
        int answer = 0;
        while(!que.isEmpty()){
            Pos pos = que.poll();
            // System.out.println(pos.r + " " + pos.c);
            
            if(pos.r == N - 1 && pos.c == M - 1){
                answer = pos.cnt;
                break;
            }
            
            for(int dir = 0; dir < 4; dir++){
                int nr = pos.r + dr[dir];
                int nc = pos.c + dc[dir];
                
                if(isOutOfMap(nr, nc)) continue;
                if(maps[nr][nc] == 0) continue;
                if(visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                que.offer(new Pos(nr, nc, pos.cnt + 1));
            }
        }
        
        return answer == 0 ? -1 : answer;
    }
    
    boolean isOutOfMap(int r, int c){
        if(r < 0 || r >= N || c < 0 || c >= M) return true;
        return false;
    }
}s