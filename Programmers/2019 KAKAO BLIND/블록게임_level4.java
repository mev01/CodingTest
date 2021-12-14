import java.util.*;

class Solution {
    static int ans = 0;
    static int[][] Board;
    static Rec[] recArr;
    
    static class Rec{
        int r1 = Integer.MAX_VALUE, c1 = Integer.MAX_VALUE, r2 = Integer.MIN_VALUE, c2 = Integer.MIN_VALUE;
        
        public void updateRec(int r, int c){
            if(r < r1) r1 = r;
            else if(r2 < r) r2 = r;
            
            if(c < c1) c1 = c;
            else if(c2 < c) c2 = c;
        }
    }
    public int solution(int[][] board) {
        Board = board;
        recArr = new Rec[201];
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] > 0){
                    if(recArr[board[i][j]] == null) recArr[board[i][j]] = new Rec();
                    recArr[board[i][j]].updateRec(i, j);
                }
            }
        }
        
        while(true){
            HashSet<Integer> set = fallBlack();
            
            int deletedRec = checkFull(set);
            
            if(deletedRec == 0) break;
            else ans += deletedRec;
        }
        
        return ans;
    }
    
    HashSet<Integer> fallBlack(){
        HashSet<Integer> set = new HashSet<>();
        
        for(int j = 0; j < Board[0].length; j++){
            for(int i = 0; i < Board.length; i++){
                if(Board[i][j] > 0){
                    if(i - 1 >= 0) Board[i - 1][j] = -1;
                    if(i - 2 >= 0) Board[i - 2][j] = -1;
                    
                    set.add(Board[i][j]);
                    break;
                }
            }
        }
        
        return set;
    }
    
    int checkFull(HashSet<Integer> set){
        int cnt = 0;
        
        Iterator iter = set.iterator();
        loop:
        while(iter.hasNext()){
            int num = (int)iter.next();
            
            for(int i = recArr[num].r1; i <= recArr[num].r2; i++){
                for(int j = recArr[num].c1; j <= recArr[num].c2; j++){
                    if(Board[i][j] != num && Board[i][j] != -1) continue loop;
                }
            }
            
            cnt++;
            for(int i = recArr[num].r1; i <= recArr[num].r2; i++){
                for(int j = recArr[num].c1; j <= recArr[num].c2; j++){
                    Board[i][j] = 0;
                }
            }
        }
        
        return cnt;
    }
}