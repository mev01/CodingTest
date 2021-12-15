class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int dis = w * 2 + 1;
        int idx = 1;
        
        for(int i = 0; i < stations.length; i++){
            int station = stations[i];
            
            answer += ((station - w - idx) + (dis - 1)) / dis;
            idx = station + w + 1;
        }
        
        answer += ((n - idx + 1) + (dis - 1)) / dis;
        return answer;
    }
}