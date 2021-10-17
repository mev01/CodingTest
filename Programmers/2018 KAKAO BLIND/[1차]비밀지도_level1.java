class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        boolean[][] Map = new boolean[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                boolean isBlock1 = (arr1[i] & 1 << j) != 0 ? true : false;
                boolean isBlock2 = (arr2[i] & 1 << j) != 0 ? true : false;
                
                if(isBlock1 || isBlock2) Map[i][n - j - 1] = true;
            }
        }
        
        String[] answer = new String[n];
        
        for(int i = 0; i < n; i++){
            String str = "";
            for(int j = 0; j < n; j++){
                if(Map[i][j]) str += "#";
                else str += " ";
            }
            answer[i] = str;
        }
        
        return answer;
    }
}