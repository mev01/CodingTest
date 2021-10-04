import java.util.*;

class Solution {
    static int len;
    static String[] ans;
    static List<String> ansList = new ArrayList<>();
    static HashMap<String, ArrayList<Airport>> map = new HashMap<>();
    
    static class Airport implements Comparable<Airport>{
        String name;
        boolean visited;
        
        public Airport(String name){
            this.name = name;
        }
        
        @Override
        public int compareTo(Airport o){
            return this.name.compareTo(o.name);
        }
    }
    
    public String[] solution(String[][] tickets) {
        len = tickets.length;
        
        for(String[] ticket : tickets){
            if(!map.containsKey(ticket[0])) map.put(ticket[0], new ArrayList<>());
            map.get(ticket[0]).add(new Airport(ticket[1]));
        }
        ans = new String[len + 1];
        Travel("ICN", 0);
        
        return ans;
    }
    
    boolean Travel(String airport, int num){
        ans[num] = airport;
        if(num == len) return true;
        
        if(!map.containsKey(airport)) return false;
        ArrayList<Airport> list = map.get(airport); 
        Collections.sort(list);
        
        for(Airport nAirport : list){
            if(nAirport.visited) continue;
            
            nAirport.visited = true;
            if(Travel(nAirport.name, num + 1)) return true;
            nAirport.visited = false;
        }
        
        return false;
    }
}