import java.util.*;

class Solution {
    static int M, N, cnt;
    static String[] Board;
    static Friends[] friendsArr;
    
    static class Friends{
        int r1, c1, r2, c2;
        boolean isDeleted;
        
        public Friends(int r1, int c1){
            this.r1 = r1;
            this.c1 = c1;
        }
    }
    
    public String solution(int m, int n, String[] board) {
        M = m;
        N = n;
        Board = board;
        friendsArr = new Friends[26];
        
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                char ch = Board[i].charAt(j);
                
                if(ch < 'A' || ch > 'Z') continue;
                ++cnt;
                
                if(friendsArr[ch - 'A'] == null){
                    friendsArr[ch - 'A'] = new Friends(i, j);
                }
                else{
                    friendsArr[ch - 'A'].r2 = i;
                    friendsArr[ch - 'A'].c2 = j;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        loop:
        while(cnt/2 != 0){
            for(int num = 0; num < 26; num++){
                if(friendsArr[num] == null || friendsArr[num].isDeleted) continue;
                
                if(isReachable(num, friendsArr[num].r1, friendsArr[num].c1, friendsArr[num].r2, friendsArr[num].c2)){
                    Board[friendsArr[num].r1] = Board[friendsArr[num].r1].substring(0, friendsArr[num].c1) + '.' + Board[friendsArr[num].r1].substring(friendsArr[num].c1 + 1);
                    Board[friendsArr[num].r2] = Board[friendsArr[num].r2].substring(0, friendsArr[num].c2) + '.' + Board[friendsArr[num].r2].substring(friendsArr[num].c2 + 1);
                    friendsArr[num].isDeleted = true;
                    cnt -= 2;
                    
                    sb.append((char)(num + 'A'));
                    continue loop;
                }
            }
            return "IMPOSSIBLE";
        }
        
        return sb.toString();
    }
    
    boolean isReachable(int num, int r1, int c1, int r2, int c2){
        if(r1 == r2 || c1 == c2){
            if(noBlock(num, r1, c1, r2, c2)) return true;
        }
        else{
            if(noBlock(num, r1, c1, r2, c1) && noBlock(num, r2, c1, r2, c2)) return true;
            if(noBlock(num, r1, c1, r1, c2) && noBlock(num, r1, c2, r2, c2)) return true;
        }
        
        return false;
    }
    
    boolean noBlock(int num, int r1, int c1, int r2, int c2){
        if(r1 == r2){
            for(int j = ((c1 < c2) ? c1 : c2); j <= ((c1 > c2) ? c1 : c2); j++){
                if(!(Board[r1].charAt(j) == '.' || Board[r1].charAt(j) - 'A' == num)) return false;
            }
        }
        else{
            for(int i = ((r1 < r2) ? r1 : r2); i <= ((r1 > r2) ? r1 : r2); i++){
                if(!(Board[i].charAt(c1) == '.' || Board[i].charAt(c1) - 'A' == num)) return false;
            }
        }
        
        return true;
    }
}