import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int num = 0, idx = 0;
        int[] arr = new int[3];
        for(int i = 0; i < dartResult.length(); i++){
            char ch = dartResult.charAt(i);
            
            if(ch >= '0' && ch <= '9'){
                num = num * 10 + Integer.parseInt(Character.toString(ch));
            }
            else if(ch =='S' || ch == 'D' || ch == 'T'){
                arr[idx] = (int)Math.pow(num, ch == 'S' ? 1 : (ch == 'D' ? 2 : 3));
                num = 0;
                ++idx;
            }
            else if(ch == '*'){
                if(idx - 2 >= 0) arr[idx - 2] *= 2;
                arr[idx - 1] *= 2;
            }
            else if(ch == '#'){
                arr[idx - 1] *= -1;
            }
        }
        
        // System.out.println(Arrays.toString(arr));
        
        return arr[0] + arr[1] + arr[2];
    }
}
