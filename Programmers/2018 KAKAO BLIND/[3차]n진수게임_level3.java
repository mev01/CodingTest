import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < t; i++){
            int st = p + i * m;
            
            int s = 0, e = st;
            while(s < e){
                int M = (s + e) / 2;
                int num = getNumbers(n, M);
                
                if(num >= st) e = M;
                else s = M + 1;
            }
            
            int remainNum = st - getNumbers(n, s - 1);
            StringBuilder sToNum = new StringBuilder();
            do{
                if(s % n >= 10)  sToNum.append((char)(s % n - 10 + 'A'));
                else sToNum.append(s % n);
                s /= n;
            }while(s > 0);
            sToNum = sToNum.reverse();
            sb.append(sToNum.charAt(remainNum - 1));
        }
        
        return sb.toString();
    }
    
    int getNumbers(int n, int M){
        int temp = M, digit = 0;
        do{
            temp /= n;
            digit++;
        }while(temp > 0);

        int cnt = 0;
        for(int j = 1; j <= digit - 1; j++){
            if(j != 1) cnt += j * (int)Math.pow(n, j - 1) * (n - 1);
            else cnt += j * (int)Math.pow(n, j);
        }
        cnt += ((digit == 1 ? M + 1 : M) - ((int)Math.pow(n, digit - 1)) + 1) * digit;
        
        return cnt;
    }
}