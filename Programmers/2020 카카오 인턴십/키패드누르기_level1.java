class Solution {
    static int[][] disArr = {{4, 2}, {1, 1}, {1, 2}, {1, 3}, 
                            {2, 1}, {2, 2}, {2, 3}, 
                            {3, 1}, {3, 2}, {3, 3},
                             {4, 1}, {4, 3}};
    
    public String solution(int[] numbers, String hand) {
        int L = 10, R = 11;
        StringBuilder sb = new StringBuilder();
        
        for(int num : numbers){
            if(num == 1 || num == 4 || num == 7){
                sb.append("L");
                L = num;
            }
            else if(num == 3 || num == 6 || num == 9){
                sb.append("R");
                R = num;
            }
            else{
                int disL = Math.abs(disArr[L][0] - disArr[num][0]) + Math.abs(disArr[L][1] - disArr[num][1]);
                int disR = Math.abs(disArr[R][0] - disArr[num][0]) + Math.abs(disArr[R][1] - disArr[num][1]);
                
                if(disL < disR){
                    sb.append("L");
                    L = num;
                }
                else if(disR < disL){
                    sb.append("R");
                    R = num;
                }
                else{
                    if(hand.equals("right")){
                        sb.append("R");
                        R = num;
                    }
                    else{
                        sb.append("L");
                        L = num;
                    }
                }
            }
        }
        
        return sb.toString();
    }
}