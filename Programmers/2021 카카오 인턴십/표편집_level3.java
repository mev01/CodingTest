import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int idx = k, size = n;
        Stack<Integer> st = new Stack<>();
        
        for(String inst : cmd){
            if(inst.equals("C")){
                st.push(idx);
                size--;
                if(idx == size) idx--;
            }
            else if(inst.equals("Z")){
                int backNum = st.pop();
                
                if(backNum <= idx) idx++;
                size++;
            }
            else if(inst.charAt(0) == 'U'){
                int num = Integer.parseInt(inst.substring(2, inst.length()));
                
                idx -= num;
            }
            else{
                int num = Integer.parseInt(inst.substring(2, inst.length()));
                
                idx += num;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++){
            sb.append("O");
        }
        while(!st.isEmpty()){
            sb.insert(st.pop(), "X");
        }
        
        return sb.toString();
    }
}