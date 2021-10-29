import java.util.*;

class Solution {
    static int ans = 0;
    static boolean[] visited;
    static String[] user, bann, patterns, ansArr;
    static HashSet<String> set;
    
    public int solution(String[] user_id, String[] banned_id) {
        user = user_id;
        bann = banned_id;
        
        visited = new boolean[user_id.length];
        patterns = new String[banned_id.length];
        ansArr = new String[banned_id.length];
        set = new HashSet<String>();
        
        
        for(int i = 0; i < banned_id.length; i++){
            patterns[i] = "^" + banned_id[i].replaceAll("\\*", ".") + "$";
        }
        
        Comb(0);
        
        return set.size();
    }
    
    void Comb(int cnt){
        if(cnt == bann.length){
            String[] sorted = ansArr.clone();
            Arrays.sort(sorted);
            
            String str = "";
            for(String s : sorted){
                str += s;
            }
            set.add(str);
            
            return;
        }
        
        for(int i = 0; i < user.length; i++){
            if(!visited[i] && user[i].matches(patterns[cnt])){
                ansArr[cnt] = user[i];
                visited[i] = true;
                
                Comb(cnt + 1);
                
                visited[i] = false;
            }
        }
    }
}