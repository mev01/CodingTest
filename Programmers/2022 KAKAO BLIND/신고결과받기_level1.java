import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Integer> idxMap = new HashMap<>();
        HashMap<String, HashSet<String>> reportedMap = new HashMap<>();
        int[] answer = new int[id_list.length];
        
        for(int i = 0; i < id_list.length; i++){
            idxMap.put(id_list[i], i);
            reportedMap.put(id_list[i], new HashSet<>());
        }
        
        for(String r : report){
            String[] names = r.split(" ");
            
            reportedMap.get(names[1]).add(names[0]);
        }
        
        for(String reported : reportedMap.keySet()){
            HashSet<String> reportSet = reportedMap.get(reported);
            
            if(reportSet.size() >= k){
                for(String reportName : reportSet){
                    answer[idxMap.get(reportName)]++;
                }
            }
        }
        
        return answer;
    }
}