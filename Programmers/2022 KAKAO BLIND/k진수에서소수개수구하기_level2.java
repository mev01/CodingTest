import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String cN = Integer.toString(n, k);
        String[] arr = cN.split("0");
        
        int cnt = 0;
        for(String num : arr){
            if(!num.equals("") && isPrime(Long.parseLong(num))){
                answer++;
            }
        }
        
        return answer;
    }
    
    boolean isPrime(long num){
        if(num == 1) return false;
        
        for(long i = 2; i <= (long)(Math.sqrt(num)); i++){
            if(num % i == 0) return false;
        }
        
        return true;
    }
}