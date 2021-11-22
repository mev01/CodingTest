class Solution {
    public String solution(String sentence) {
        for(char ch = 'a'; ch <= 'z'; ch++){
            int cnt = 0, lastIdx = 0;
            
            for(int i = 0; i < sentence.length(); i++){
                if(sentence.charAt(i) == ch){
                    cnt++;
                    lastIdx = i;
                }
            }
            
            if(cnt == 2){
                sentence = sentence.replaceAll(Character.toString(ch), " ");
            }
            else if(cnt != 0){
                if(lastIdx + 2 < sentence.length() && (sentence.charAt(lastIdx + 2) >= 'A' && sentence.charAt(lastIdx + 2) <= 'Z')){
                    System.out.println(sentence.charAt(lastIdx + 2));
                    sentence = sentence.substring(0, lastIdx + 2) + " " + sentence.substring(lastIdx + 2);
                }
                sentence = sentence.replaceAll(Character.toString(ch), "");
            }
            
        }
        
        return sentence;
    }
}