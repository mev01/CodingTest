import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;

        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            
            if(st.isEmpty() || st.peek() != c) st.push(c);
            else st.pop();
            
            // System.out.println(st.isEmpty() ? "empty" : st.peek());
        }

        return st.isEmpty() ? 1 : 0;
    }
}