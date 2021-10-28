import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        HashSet<String> originSet = new HashSet<String>();
        for(String gem : gems){
            originSet.add(gem);
        }
        
        int s = 0, e = 0, cnt = 1;
        int[] ans = {0, 100000};
        HashMap<String, Integer> stolenMap = new HashMap<String, Integer>();
        
        for(String gem : originSet){
            stolenMap.put(gem, 0);
        }
        
        stolenMap.put(gems[0], stolenMap.get(gems[0]) + 1);
        
        while(s <= e){
            if(e < gems.length - 1 && cnt != originSet.size()){
                ++e;
                if(stolenMap.get(gems[e]) == 0) ++cnt;
                stolenMap.put(gems[e], stolenMap.get(gems[e]) + 1);
            }
            else{
                if(cnt == originSet.size() && e - s < ans[1] - ans[0]){
                    ans[1] = e + 1;
                    ans[0] = s + 1;
                }
                
                stolenMap.put(gems[s], stolenMap.get(gems[s]) - 1);
                if(stolenMap.get(gems[s]) == 0) --cnt;
                ++s;
            }
        }
        
        return ans;
    }
}