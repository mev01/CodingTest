class Solution {
    static int ans = 0, len, end;
    static int[] numArr;
    
    public int solution(int[] numbers, int target) {
        len = numbers.length;
        numArr = numbers;
        end = target;
        
        BF(0, 0);
        
        return ans;
    }
    
    void BF(int num, int cnt){
        if(cnt == len){
            if(num == end) ++ans;
            return;
        }
        BF(num + numArr[cnt], cnt + 1);
        BF(num - numArr[cnt], cnt + 1);
    }
}