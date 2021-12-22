class Solution {
    public int solution(int n) {
        return Search(n, 0);
    }
    
    int Search(int cnt, int sum){
        if(cnt == 0 && sum == 0){
            return 1;
        }
        
        int ans = 0;
        
        if(cnt > 0) ans += Search(cnt - 1, sum + 1);
        if(sum >= 1) ans += Search(cnt, sum - 1);
        
        return ans;
    }
}
