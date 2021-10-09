class Solution {
    public int[] solution(int n, int s) {
        int q = s / n, r = s % n;
        if(q == 0){
            int[] ans = {-1};
            return ans;
        }

        int[] ans = new int[n];
        for(int i = n - 1; i >= 0; i--){
            ans[i] = (r > 0) ? q + 1 : q;
            --r;
        }

        return ans;
    }
}