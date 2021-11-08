import java.util.*;

class Solution {
    static ArrayList<Integer> list;
    
    public int solution(int n, int[] weak, int[] dist) {
        list = new ArrayList<Integer>();
        calcOrder(dist.length);
        
        return -1;
    }
    
    void calcOrder(int cnt){
        if(cnt == 0){
            calcLength();
            return;
        }
        
        for(int i = cnt; i >= 1; i--){
            if(list.size() == 0 || i <= list.get(list.size() - 1)){
                list.add(i);
                calcOrder(cnt - i);
                list.remove(list.size() - 1);
            }
        }
    }
    
    void calcLength(){
        
    }
}