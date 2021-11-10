import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++){
            int st = p + i * m;
            System.out.println("st " + st);
            
            int s = 1, e = st;
            while(s < e){
                int M = (s + e) / 2, temp = M;
                
                int digit = 0;
                while(temp > 0){
                    temp /= n;
                    digit++;
                }
                System.out.println("M " + M);
                System.out.println("digit " + digit);
                
                int cnt = 0;
                for(int j = 1; j <= digit - 1; j++){
                    cnt += (j * (int)Math.pow(n, j));
                    if(j != 1) cnt -= (j * (int)Math.pow(n, j - 1));
                    System.out.println("cnt " + cnt);
                }
                cnt += (M - (int)Math.pow(n, digit - 1)) * digit;
                System.out.println("cnt " + "end " + cnt);
                
                if(cnt < st) e = M - 1;
                else s = M;
            }
            
            System.out.println(s);
        }
        
        String answer = "";
        return answer;
    }
}