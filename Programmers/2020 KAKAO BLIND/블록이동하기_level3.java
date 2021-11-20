import java.util.*;

class Solution {
    static int N;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    static class Robot{
        int r1, c1, r2, c2, time, dir;
        
        public Robot(int r1, int c1, int r2, int c2, int dir, int time){
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.time = time;
            this.dir = dir;
        }
    }
    public int solution(int[][] board) {
        N = board.length;
        boolean[][][] visited = new boolean[N][N][4];
        Queue<Robot> que = new LinkedList<>();
        
        visited[0][0][0] = true;
        que.offer(new Robot(0, 0, 0, 1, 0, 0));
        
        while(!que.isEmpty()){
            Robot robot = que.poll();
            
            if((robot.r1 == N - 1 && robot.c1 == N - 1) || (robot.r2 == N - 1 && robot.c2 == N -1)){
                return robot.time;
            }
            
            // 4방 이동
            for(int dir = 0; dir < 4; dir++){
                int nr1 = robot.r1 + dr[dir], nc1 = robot.c1 + dc[dir];
                int nr2 = robot.r2 + dr[dir], nc2 = robot.c2 + dc[dir];
                
                if(isOutOfMap(nr1, nc1)) continue;
                if(isOutOfMap(nr2, nc2)) continue;
                if(board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;
                if(visited[nr1][nc1][robot.dir]) continue;
                
                visited[nr1][nc1][robot.dir] = true;
                que.offer(new Robot(nr1, nc1, nr2, nc2, robot.dir, robot.time + 1));
            }
            
            // 가로 상태일 떄 회전
            if(robot.c1 != robot.c2){
                for(int dir = 0; dir < 2; dir++){
                    int nr1 = robot.r1 + dr[dir], nc1 = robot.c1 + dc[dir];
                    int nr2 = robot.r2 + dr[dir], nc2 = robot.c2 + dc[dir];

                    if(isOutOfMap(nr1, nc1)) continue;
                    if(isOutOfMap(nr2, nc2)) continue;
                    if(board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;

                    if(!visited[robot.r1][robot.c1][3 - dir]){
                        visited[robot.r1][robot.c1][3 - dir] = true;
                        que.offer(new Robot(robot.r1, robot.c1, nr1, nc1, 3 - dir, robot.time + 1));
                    }

                    if(!visited[nr2][nc2][2 + dir]){
                        visited[nr2][nc2][2 + dir] = true;
                        que.offer(new Robot(nr2, nc2, robot.r2, robot.c2, 2 + dir, robot.time + 1));
                    }
                }
            }
            // 세로 상태일 떄 회전
            else{
                for(int dir = 2; dir < 4; dir++){
                    int nr1 = robot.r1 + dr[dir], nc1 = robot.c1 + dc[dir];
                    int nr2 = robot.r2 + dr[dir], nc2 = robot.c2 + dc[dir];

                    if(isOutOfMap(nr1, nc1)) continue;
                    if(isOutOfMap(nr2, nc2)) continue;
                    if(board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;

                    if(!visited[robot.r1][robot.c1][3 - dir]){
                        visited[robot.r1][robot.c1][3 - dir] = true;
                        que.offer(new Robot(robot.r1, robot.c1, nr1, nc1, 3 - dir, robot.time + 1));
                    }

                    if(!visited[nr2][nc2][dir - 2]){
                        visited[nr2][nc2][dir - 2] = true;
                        que.offer(new Robot(nr2, nc2, robot.r2, robot.c2, dir - 2, robot.time + 1));
                    }
                }
            }
        }
        
        return 0;
    }
    
    boolean isOutOfMap(int r, int c){
        if(r < 0 || r >= N || c < 0 || c >= N) return true;
        return false;
    }
}