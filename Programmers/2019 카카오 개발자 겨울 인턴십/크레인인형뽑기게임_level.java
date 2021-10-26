import java.util.*;

class Solution {
    static int ans;
    static int[][] Board;
    static Stack<Integer> st;
    
    public int solution(int[][] board, int[] moves) {
        Board = board;
        st = new Stack<Integer>();
        
        for(int m : moves){
            int num = getIdx(m - 1);
            
            if(num >= 0){
                if(!st.isEmpty() && st.peek() == num){
                    ans += 2;
                    st.pop();
                }
                else st.push(num);
            }
        }
        
        return ans;
    }
    
    int getIdx(int line){
        for(int i = 0; i < Board.length; i++){
            if(Board[i][line] != 0){
                int ch = Board[i][line];
                Board[i][line] = 0;
                return ch;
            }
        }
        return -1;
    }
    
    
}