import java.util.*;

class Solution {
    static int N;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static int[][][] costArr;
    static PriorityQueue<Pos> pq;
    
    static class Pos implements Comparable<Pos>{
        int pDir, r, c, cost;
        
        public Pos(int pDir, int r, int c, int cost){
            this.pDir = pDir;
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Pos p){
            return this.cost - p.cost;
        }
    }
    
    public int solution(int[][] board) {
        N = board.length;
        costArr = new int[N][N][4];
        for(int[][] arr1 : costArr){
            for(int[] arr2 : arr1){
                Arrays.fill(arr2, Integer.MAX_VALUE);
            }
        }
        pq = new PriorityQueue<>();
        
        pq.offer(new Pos(-3, 0, 0, 0));
        costArr[0][0][0] = 0;
        costArr[0][0][1] = 0;
        costArr[0][0][2] = 0;
        costArr[0][0][3] = 0;
        
        while(!pq.isEmpty()){
            Pos pos = pq.poll();
            
            if(pos.r == N - 1 && pos.c == N - 1){
                return costArr[N - 1][N - 1][pos.pDir];
            }
            
            for(int dir = 0; dir < 4; dir++){
                int nr = pos.r + dr[dir];
                int nc = pos.c + dc[dir];
                int cost = (dir == pos.pDir || pos.pDir == -3) ? 100 : 600;
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if(pos.pDir != -3 && (int)Math.abs(dir - pos.pDir) == 2) continue;
                if(board[nr][nc] == 1) continue;
                if(costArr[nr][nc][dir] < pos.cost + cost) continue;
                
                costArr[nr][nc][dir] = pos.cost + cost;
                pq.offer(new Pos(dir, nr, nc, pos.cost + cost));
            }
        }
        
        return 0;
    }
}