import java.util.*;

class Solution {
    static HashMap<String,String> hash;
    
    public String[] solution(String[] record) {
        hash = new HashMap<String, String>();
        int len = record.length;
        
        List<String> message = new LinkedList<>();
        StringTokenizer st;
        
        for(int i = 0; i < len; ++i){
            st = new StringTokenizer(record[i]);
            String inst = st.nextToken();
            String user = st.nextToken();
            
            if(inst.equals("Enter")){
                String name = st.nextToken();
                hash.put(user, name);
            }
            else if(inst.equals("Change")){
                String name = st.nextToken();
                hash.put(user, name);
            }
        }
        
        for(int i = 0; i < len; ++i){
            st = new StringTokenizer(record[i]);
            String inst = st.nextToken();
            String user = st.nextToken();
            
            if(inst.equals("Enter")){
                String name = st.nextToken();
                message.add(hash.get(user) + "님이 들어왔습니다.");
            }
            else if(inst.equals("Leave")){
                message.add(hash.get(user) + "님이 나갔습니다.");
            }
        }
        return message.toArray(new String[0]);
    }
}