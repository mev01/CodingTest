import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] splitArr = s.split("}");
        for(int i = 0; i < splitArr.length; i++){
            splitArr[i] = splitArr[i].substring(2, splitArr[i].length());
        }
        
        String[][] splitArr2 = new String[splitArr.length][];
        for(int i = 0; i < splitArr.length; i++){
            splitArr2[i] = splitArr[i].split(",");
        }
        
        Arrays.sort(splitArr2, (o1, o2) -> {return o1.length - o2.length;});
        
        boolean checked[] = new boolean[100001];
        int[] ans = new int[splitArr2.length];
        
        for(int i = 0; i < splitArr2.length; i++){
            for(int j = 0; j < splitArr2[i].length; j++){
                int num = Integer.parseInt(splitArr2[i][j]);
                
                if(!checked[num]){
                    ans[i] = num;
                    checked[num] = true;
                } 
            }
        }
        
        return ans;
    }
}