class Solution {
    static int MOD = 20170805, ans = 0;
    static int[] dr = {-1, 0}, dc = {0, -1};
    static int[][][] dp;
    
    public int solution(int m, int n, int[][] cityMap) {
        dp = new int[m][n][2];
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        
        for(int i = 0; i  m; i++){
            for(int j = 0; j  n; j++){
                for(int dir = 0; dir  2; dir++){
                    int pr = i + dr[dir];
                    int pc = j + dc[dir];
                    
                    if(pr  0  pc  0) continue;
                    
                    if(cityMap[i][j] == 0){
                        dp[i][j][0] = (dp[i][j][0] + dp[pr][pc][dir]) % MOD;
                        dp[i][j][1] = (dp[i][j][1] + dp[pr][pc][dir]) % MOD;
                    }
                    else if(cityMap[i][j] == 2){
                        dp[i][j][dir] = (dp[i][j][dir] + dp[pr][pc][dir]) % MOD;
                    }
                }
            }
        }
        
        return dp[m - 1][n - 1][0];
    }
}