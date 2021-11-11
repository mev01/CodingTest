import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static Queue<Pos> que;
    static boolean[][] visited;
    
    static class Pos{
        int r, c, dis;
        
        public Pos(int r, int c, int dis){
            this.r = r;
            this.c = c;
            this.dis = dis;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        loop:
        for(int place = 0; place < 5; place++){
            for(int i = 0 ; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(places[place][i].charAt(j) == 'P'){
                        que = new LinkedList<>();
                        visited = new boolean[5][5];
                        
                        que.offer(new Pos(i, j, 0));
                        visited[i][j] = true;
                        
                        while(!que.isEmpty()){
                            Pos pos = que.poll();
                            
                            // System.out.println(pos.r + " " + pos.c);
                            
                            if(!(i == pos.r && j == pos.c) && places[place][pos.r].charAt(pos.c) == 'P'){
                                answer[place] = 0;
                                continue loop;
                            }
                            if(pos.dis == 2) continue;
                            
                            for(int dir = 0; dir < 4; dir++){
                                int nr = pos.r + dr[dir];
                                int nc = pos.c + dc[dir];
                                
                                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                                if(places[place][nr].charAt(nc) == 'X') continue;
                                if(visited[nr][nc]) continue;
                                
                                visited[nr][nc] = true;
                                que.offer(new Pos(nr, nc, pos.dis + 1));
                            }
                        }
                    }
                }
            }
            answer[place] = 1;
        }
        
        return answer;
    }
}