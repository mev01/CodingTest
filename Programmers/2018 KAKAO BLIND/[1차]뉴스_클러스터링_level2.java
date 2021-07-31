class Solution {
    static int[][] wordCnt1, wordCnt2;
    public int solution(String input1, String input2) {
        int answer = 0;
        String str1 = input1.toUpperCase();
        String str2 = input2.toUpperCase();
        wordCnt1 = new int[26][26];
        wordCnt2 = new int[26][26];
        
        for(int i = 0; i < str1.length() - 1; i++){
            if((str1.charAt(i) - 'A' >= 0 && str1.charAt(i) - 'A' < 26 
                && str1.charAt(i + 1) - 'A' >= 0 && str1.charAt(i + 1) - 'A' < 26)){
                ++wordCnt1[str1.charAt(i) - 'A'][str1.charAt(i + 1) - 'A'];
            }
        }
        for(int i = 0; i < str2.length() - 1; i++){
            if((str2.charAt(i) - 'A' >= 0 && str2.charAt(i) - 'A' < 26 
                && str2.charAt(i + 1) - 'A' >= 0 && str2.charAt(i + 1) - 'A' < 26)){
                ++wordCnt2[str2.charAt(i) - 'A'][str2.charAt(i + 1) - 'A'];
            }
        }
        
        int interSection = 0, union = 0;
        for(int i = 0; i < 26; i++){
            for(int j = 0; j < 26; j++){
                if(wordCnt1[i][j] > 0 && wordCnt2[i][j] > 0) interSection += Math.min(wordCnt1[i][j], wordCnt2[i][j]);
                union += Math.max(wordCnt1[i][j], wordCnt2[i][j]);
            }
        }
        
        return (union == 0) ? 65536 : interSection * 65536 / union;
    }
}