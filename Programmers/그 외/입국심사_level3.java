import java.util.*;

class Solution {
    static int N;
    static long len;
    
    public long solution(int n, int[] times) {
        len = times.length;
        N = n;
        
        Arrays.sort(times);
        
        return binarySearch(1, (long)times[(int)len - 1] * (long)n / len, times);
    }
    
    long binarySearch(long s, long e, int[] times){
        long m = (s + e) / 2;
        if(s == e) return m;
        
        long cnt = 0;
        for(int i = 0; i < len; i++){
            cnt += m / times[i];
        }
        // System.out.println(s + " " + e + " " + cnt);
        
        if(cnt >= N) return binarySearch(s, m, times);
        else return binarySearch(m + 1, e, times);
    }
}