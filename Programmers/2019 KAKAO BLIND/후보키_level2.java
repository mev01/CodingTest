import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int lenRow = relation.length;
        int lenCol = relation[0].length;
        
        HashSet<String> set;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        int maxNum = (int)Math.pow(2, lenCol) - 1;
        loop:
        for(int num = 1; num <= maxNum; num++){
            for(int test : list){
                if((test & num) == test) continue loop;
            }
            set = new HashSet<String>();
            
            for(int i = 0; i < lenRow; i++){
                String str = "";
                for(int j = 0; j < lenCol; j++){
                    if((num & (1 << j)) != 0){
                        str += relation[i][j];
                    }
                }
                
                set.add(str);
            }
            
            if(set.size() == lenRow){
                list.add(num);
            } 
        }
        
        return list.size();
    }
    
    
}