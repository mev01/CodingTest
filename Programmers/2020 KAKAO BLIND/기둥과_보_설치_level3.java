import java.util.*;

class Solution {
    static int N;
    static boolean[][] pillarArr, beamArr;
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        N = n;
        pillarArr = new boolean[n + 1][n + 1];
        beamArr = new boolean[n + 1][n + 1];
        
        for(int[] frame : build_frame){
            int r = frame[0], c = frame[1], a = frame[2], b = frame[3];
            
            if(b == 0){
                if(a == 0){
                    if(pillarArr[r][c]){
                        pillarArr[r][c] = false;
                        boolean flag = true;
                        
                        if(!OutOfMap(r, c + 1)) flag = flag && canBuildPillar(r, c + 1) ? true : false;
                        if(!OutOfMap(r, c + 1)) flag = flag && canBuildBeam(r, c + 1) ? true : false;
                        if(!OutOfMap(r, c - 1)) flag = flag && canBuildBeam(r, c - 1) ? true : false;
                        
                        if(!flag) pillarArr[r][c] = true;
                    }
                }
                else{
                    if(beamArr[r][c]){
                        beamArr[r][c] = false;
                        boolean flag = true;
                        
                        if(!OutOfMap(r - 1, c)) flag = flag && canBuildBeam(r - 1, c) ? true : false;
                        if(!OutOfMap(r + 1, c)) flag = flag && canBuildBeam(r + 1, c) ? true : false;
                        if(!OutOfMap(r, c)) flag = flag && canBuildPillar(r, c) ? true : false;
                        if(!OutOfMap(r + 1, c)) flag = flag && canBuildPillar(r + 1, c) ? true : false;
                        
                        if(!flag) beamArr[r][c] = true;
                    }
                }
            }
            else{
                if(a == 0){
                    pillarArr[r][c] = canBuildPillar(r, c) ? true : false;
                }
                else{
                    beamArr[r][c] = canBuildBeam(r, c) ? true : false;
                }
            }
        }
        
        ArrayList<int[]> list = new ArrayList<int[]>();
        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= N; j++){
            	if(pillarArr[i][j]){
                    int[] arr = {i, j, 0};
                    list.add(arr);
                }
                if(beamArr[i][j]){
                    int[] arr = {i, j, 1};
                    list.add(arr);
                }
            }
        }
        
        answer = new int[list.size()][];
        
        int idx = 0;
        for(int[] arr : list){
            answer[idx++] = arr;
        } 
        
        Arrays.sort(answer, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					if(o1[1] == o2[1]) {
						return o1[2] - o2[2];
					}
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});
        
        return answer;
    }
    
    boolean OutOfMap(int r, int c){
        if(r < 0 || r > N || c < 0 || c > N) return true;
        return false;
    }
    
    boolean canBuildBeam(int r, int c){
        if(!OutOfMap(r - 1, c) && !OutOfMap(r + 1, c) && (beamArr[r - 1][c] && beamArr[r + 1][c])) return true;
        if(!OutOfMap(r, c - 1) && pillarArr[r][c - 1]) return true;
        if(!OutOfMap(r + 1, c - 1) && pillarArr[r + 1][c - 1]) return true;
        
        return false;
    }
    
    boolean canBuildPillar(int r, int c){
        if(c == 0) return true;
        if(!OutOfMap(r - 1, c) && beamArr[r - 1][c]) return true;
        if(!OutOfMap(r, c) && beamArr[r][c]) return true;
        if(!OutOfMap(r, c - 1) && pillarArr[r][c - 1]) return true;
        
        return false;
    }
}