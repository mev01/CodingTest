import java.util.*;

class Solution {
    static ArrayList<String> list;
    static HashMap<String, Integer> map;
    static char[] preMenuComb, menuComb;
    
    public String[] solution(String[] orders, int[] course) {
        list = new ArrayList<String>();
        
        for(int size : course){
            map = new HashMap<String, Integer>();
            
            for(String str : orders){
                preMenuComb = str.toCharArray();
                Arrays.sort(preMenuComb);
                menuComb = new char[size];
                
                Combination(0, 0, size);
            }
            
            ArrayList<String> subList = new ArrayList<>();
            subList.addAll(map.keySet());
            
            Collections.sort(subList, new Comparator(){
                @Override
                public int compare(Object o1, Object o2){
                    return map.get(o2) - map.get(o1);
                }
            });
            
            if(subList.size() > 0){
                int max = map.get(subList.get(0));
                if(max == 1) continue;
                
                for(String str : subList){
                    if(map.get(str) == max) list.add(str);
                }
            }
        }
        
        String[] ans = list.toArray(new String[list.size()]);
        Arrays.sort(ans);
        return ans;
    }
        
    void Combination(int pre, int idx, int size){
        if(idx == size){
            String course = String.valueOf(menuComb);
            if(map.containsKey(course)) 
                map.put(course, map.get(course) + 1);
            else
                map.put(course, 1);
            // System.out.println(Arrays.toString(menuComb));
            return;
        }
        
        for(int i = pre; i < preMenuComb.length; i++){
            menuComb[idx] = preMenuComb[i];
            Combination(i + 1, idx + 1, size);
        }
    }
}