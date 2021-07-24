import java.util.*;

class Solution {
    static HashMap<String,String> hash;
    
    public String[] solution(String[] record) {
        hash = new HashMap<String, String>();
        int len = record.length;
        
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;
        
        for(int i = 0; i < len; ++i){
            st = new StringTokenizer(record[i]);
            String inst = st.nextToken();
            String user = st.nextToken();
            
            if(inst.equals("Enter")){
                String name = st.nextToken();
                hash.put(user, name);
                sb.append(user).append("님이 들어왔습니다./");
            }
            else if(inst.equals("Leave")){
                sb.append(user).append("님이 나갔습니다./");
            }
            else if(inst.equals("Change")){
                String name = st.nextToken();
                hash.put(user, name);
            }
        }
        
        sb.setLength(sb.length() - 1);
        String ans = sb.toString();
        
        for(String s : hash.keySet()){ //저장된 key값 확인
            ans = ans.replaceAll(s, hash.get(s));
        }
        
        st = new StringTokenizer(ans, "/");
        len = st.countTokens();
        
        String[] answer = new String[len];
        for(int i = 0; i < len; ++i){
            answer[i] = st.nextToken();
        }
        return answer;
    }
}
