import java.util.*;

class Solution {
    static boolean[][] Pillar, Beam;
    
    public int[][] solution(int n, int[][] build_frame) {
        Pillar = new boolean[n + 2][n + 2];
        Beam = new boolean[n + 2][n + 2];
        
        ArrayList<int[]> list = new ArrayList<>();
        
        for(int[] frame : build_frame){
            int x = frame[0] + 1;
            int y = frame[1] + 1;
            int a = frame[2];
            int b = frame[3];
            
            if(a == 0){
                if(b == 1){
                    if(!canBuildPillar(x, y)) continue;
                    Pillar[x][y] = true;
                }
                else{
                    if(!canDeletePillar(x, y)){
                        Pillar[x][y] = true;
                        continue;
                    } 
                }
            }
            else{
                if(b == 1){
                    if(!canBuildBeam(x, y)) continue;
                    Beam[x][y] = true;
                }
                else{
                    if(!canDeleteBeam(x, y)){
                        Beam[x][y] = true;
                        continue;
                    } 
                }
            }
        }
        
        for(int i = 1; i <= n + 1; i++){
            for(int j = 1; j <= n + 1; j++){
                if(Pillar[i][j]){
                    int[] temp = {i - 1, j - 1, 0};
                    list.add(temp);
                }
                if(Beam[i][j]){
                    int[] temp = {i - 1, j - 1, 1};
                    list.add(temp);
                }
            }
        }
        
        
        Collections.sort(list, (o1, o2) -> {
            if(o1[0] == o2[0] && o1[1] == o2[1]) return o1[2] - o2[2];
            else if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        
        int[][] answer = new int[list.size()][];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    boolean canBuildPillar(int x, int y){
        if(y == 1 || (Beam[x - 1][y] || Beam[x][y]) || Pillar[x][y - 1]) return true;
        return false;
    }
    
    boolean canBuildBeam(int x, int y){
        if((Pillar[x][y - 1] || Pillar[x + 1][y - 1]) || (Beam[x - 1][y] && Beam[x + 1][y])) return true;
        return false;
    }
    
    boolean canDeletePillar(int x, int y){
        Pillar[x][y] = false;
        if(Beam[x - 1][y + 1] && !canBuildBeam(x - 1, y + 1)) return false;
        if(Beam[x][y + 1] && !canBuildBeam(x, y + 1)) return false;
        if(Pillar[x][y + 1] && !canBuildPillar(x, y + 1)) return false;
        return true;
    }
    
    boolean canDeleteBeam(int x, int y){
        Beam[x][y] = false;
        if(Pillar[x][y] && !canBuildPillar(x, y)) return false;
        if(Pillar[x + 1][y] && !canBuildPillar(x + 1, y)) return false;
        if(Beam[x + 1][y] && !canBuildBeam(x + 1, y)) return false;
        if(Beam[x - 1][y] && !canBuildBeam(x - 1, y)) return false;
        return true;
    }
}