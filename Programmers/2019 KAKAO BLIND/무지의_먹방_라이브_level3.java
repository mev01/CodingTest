class Solution {
    static int len;
    static int[] foodArr;
    
    public int solution(int[] food_times, long k) {
        int answer = 0;
        foodArr = food_times;
        
        len = foodArr.length;
        int idx = 0;
        while(k-- > 0){
            --foodArr[idx];
            
            int nIdx = findNextIdx(idx);
            if(nIdx == -1) return -1;
            idx = nIdx;
        }
        
        return idx + 1;
    }
    
    int findNextIdx(int idx){
        int nIdx = (idx + 1) % len;
        while(foodArr[nIdx] == 0){
            nIdx  = (nIdx + 1) % len;
            if(idx == nIdx) return -1;
        }
        return nIdx;
    }
}