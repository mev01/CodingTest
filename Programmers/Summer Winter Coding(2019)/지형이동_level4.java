import java.util.*;

class Solution {
    int N, M, height;
    int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    boolean[][] visited;
    int[][] land;
    PriorityQueue<Pos> que;
    
    class Pos{
        int r, c, cost;
        
        public Pos(int r, int c, int cost){
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        visited = new boolean[N][M];
        que = new PriorityQueue<Pos>((o1, o2) -> {return o1.cost - o2.cost;});
        this.land = land;
        this.height = height;
        
        que.offer(new Pos(0, 0, 0));
        while(!que.isEmpty()){
            Pos pos = que.poll();
            
            if(visited[pos.r][pos.c]) continue;
            
            answer += pos.cost;
            DFS(pos.r, pos.c);
        }
        
        return answer;
    }
    
    void DFS(int r, int c){
        visited[r][c] = true;
        
        for(int dir = 0; dir < 4; dir++){
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if(visited[nr][nc]) continue;
            
            if((int)(Math.abs(land[r][c] - land[nr][nc])) <= height){
                DFS(nr, nc);
            }
            else{
                que.offer(new Pos(nr, nc, (int)(Math.abs(land[r][c] - land[nr][nc]))));
            }
        }
    }
}