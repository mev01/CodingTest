import java.util.*;

class Solution {
    static int ans = Integer.MAX_VALUE;
    static int[] keyArr;
    static Emp[] empArr;
    static HashMap<Integer, Boolean> visited;
    static HashMap<Integer, ArrayList<Integer>> map;
    
    static class Emp{
        int cost, par;
        
        public Emp(int cost){
            this.cost = cost;
        }
    }
    public int solution(int[] sales, int[][] links) {
        map = new HashMap<>();
        empArr = new Emp[sales.length + 1];
        
        for(int i = 0; i < sales.length; i++){
            empArr[i + 1] = new Emp(sales[i]);
        }
        
        for(int i = 0; i < links.length; i++){
            if(!map.containsKey(links[i][0])){
                map.put(links[i][0], new ArrayList<Integer>());
                map.get(links[i][0]).add(links[i][0]);
            }
            
            map.get(links[i][0]).add(links[i][1]);
            empArr[links[i][1]].par = links[i][0];
        }
        
        keyArr = new int[map.size()];
        visited = new HashMap<>();
        int idx = 0;
        
        for(int key : map.keySet()){
            visited.put(key, false);
            keyArr[idx++] = key;
            Collections.sort(map.get(key), (o1, o2) -> {return empArr[o1].cost - empArr[o2].cost;});
        }
        
        Search(0, 0);
        
        return ans;
    }
    
    void Search(int idx, int sum){
        if(idx == map.size()){
            ans = Math.min(ans, sum);
            return;
        }
        
        // System.out.println("out");
        
        if(ans <= sum) return;
        if(visited.get(keyArr[idx])){
            Search(idx + 1, sum);
            visited.put(keyArr[idx], false);
            return;
        }
        
        
        ArrayList<Integer> list = map.get(keyArr[idx]);
        
        for(int num : list){
            if(empArr[num].par != 0) visited.put(empArr[num].par, true);
            if(visited.containsKey(num)) visited.put(num, true);
            
            // System.out.println(keyArr[idx] + " " + num);
            
            Search(idx + 1, empArr[num].cost + sum);
            
            if(empArr[num].par != 0) visited.put(empArr[num].par, false);
            if(visited.containsKey(num)) visited.put(num, false);
        }
        
        visited.put(keyArr[idx], false);
    }
    
    
}