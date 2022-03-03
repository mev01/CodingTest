import java.util.*;

class Solution {
    static int N, M;
    static int[] dr = {N - 1, 0, 1, 0}, dc = {0, 1, 0, M - 1};
    static ArrayList<Integer> lenList;
    static String[] grid;
    static Pos[][] map;
    
    static class Pos{
        int r, c;
        boolean[] visited;
        
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
            visited = new boolean[4];
        }
    }
    
    public int[] solution(String[] grid) {
        lenList = new ArrayList<>();
        N = grid.length;
        M = grid[0].length();
        this.grid = grid;
        
        dr[0] = N - 1;
        dc[3] = M - 1;
        
        map = new Pos[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = new Pos(i, j);
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                for(int dir = 0; dir < 4; dir++){
                    if(map[i][j].visited[dir]){
                        continue;
                    } 
                    
                    Pos nPos = nextPos(map[i][j], dir);
                    map[i][j].visited[dir] = true;
                    
                    Search(nPos, dir);
                }
            }
        }
        
        int[] answer = new int[lenList.size()];
        for(int i = 0; i < lenList.size(); i++){
            answer[i] = lenList.get(i);
        }
        Arrays.sort(answer);
        
        return answer;
    }
    
    static void Search(Pos pPos, int dir){
        int len = 1;
        while(true){
            char light = grid[pPos.r].charAt(pPos.c);
            int nDir = dir;

            if(light == 'L') nDir = ((dir + 3) % 4);
            else if(light == 'R') nDir = ((dir + 1) % 4);

            Pos nPos = nextPos(pPos, nDir);

            if(pPos.visited[nDir]){
                lenList.add(len);
                break;
            }

            pPos.visited[nDir] = true;
            pPos = nPos;
            len++;
            dir = nDir;
        }
    }
    
    static Pos nextPos(Pos pos, int dir){
        int nr = (pos.r + dr[dir]) % N;
        int nc = (pos.c + dc[dir]) % M;
        
        return map[nr][nc];
    }
}