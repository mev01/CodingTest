import java.util.*;

class Solution {
    static int ansMin = 6, ansMax = 0, chooseNum;
    static boolean[] isChooseNum = new boolean[46], isWinNum = new boolean[46];
    static int[] chooseLottos = new int[6], Lottos;
    
    public int[] solution(int[] lottos, int[] win_nums) {
        Lottos = lottos;
        for(int i : lottos){
            isChooseNum[i] = true;
        }
        for(int i : win_nums){
            isWinNum[i] = true;
        }
        
        for(int i : lottos){
            if(i == 0) chooseNum++;
        }
        
        Comb(1, 0);
        
        int[] answer = {(7 - ansMax) == 7 ? 6 : (7 - ansMax), (7 - ansMin) == 7 ? 6 : (7 - ansMin)};
        return answer;
    }
    
    void Comb(int idx, int cnt){
        if(cnt == chooseNum){
            // System.out.println(Arrays.toString(chooseLottos));
            updateAns();
            return;
        }
        
        for(int i = idx; i < 46; i++){
            if(isChooseNum[i]) continue;
            chooseLottos[cnt] = i;
            Comb(i + 1, cnt + 1);
        }
    }
    
    void updateAns(){
        int correctAnsNum = 0;
        
        for(int i : Lottos){
            if(i != 0 && isWinNum[i])  correctAnsNum++;
        }
        for(int i : chooseLottos){
            if(i != 0 && isWinNum[i])  correctAnsNum++;
        }
        
        if(ansMax < correctAnsNum) ansMax = correctAnsNum;
        if(correctAnsNum < ansMin) ansMin = correctAnsNum;
    }
}