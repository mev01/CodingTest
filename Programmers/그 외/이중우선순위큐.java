import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for(String oper : operations){
            StringTokenizer st = new StringTokenizer(oper);
            String inst = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            if(inst.equals("I")){
                int idx = Collections.binarySearch(list, num);
                if(idx < 0) idx = (idx + 1) * -1;
                
                list.add(idx, num);
            }
            else{
                if(list.size() == 0) continue;
                
                if(num == -1) list.remove(0);
                else list.remove(list.size() - 1);
            }
        }
        
        int[] answer = new int[2];
        
        if(list.size() != 0){
            answer[0] = list.get(list.size() - 1);
            answer[1] = list.get(0);
        }
        return answer;
    }
}