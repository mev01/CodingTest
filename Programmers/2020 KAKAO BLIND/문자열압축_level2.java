import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        int len = s.length();
        for(int strLen = 1; strLen <= len / 2 + 1; strLen++){
            int idx = 0, cnt = strLen, rp = 0;
            String str = s.substring(idx, idx + strLen);
            
            for(int i = 0; i < len / strLen; i++, idx += strLen){
                String comp = s.substring(idx, idx + strLen);
                
                if(!comp.equals(str)){
                    if(rp >= 2) cnt += (int) Math.log10(rp) + 1;
                    rp = 1;
                    str = comp;
                    cnt += strLen;
                }
                else{
                    rp += 1;
                }
            }
            
            if(rp >= 2) cnt += (int) Math.log10(rp) + 1;
            cnt += len % strLen;
            answer = Math.min(answer, cnt);
        }
        
        return answer;
    }
}