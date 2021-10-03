import java.util.*;

class Solution {
    static int len, maxLen;
    static Queue<Word> que = new LinkedList<Word>();
    
    static class Word{
        String word;
        int cnt;
        
        public Word(String word, int cnt){
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        len = begin.length();
        maxLen = words.length;
        
        que.offer(new Word(begin, 0));
        while(!que.isEmpty()){
            Word nWord = que.poll();
            
            if(nWord.word.equals(target)){
                return nWord.cnt;
            }
            if(nWord.cnt == maxLen) continue;
            
            for(String word : words){
                int difChar = 0;
                for(int i = 0; i < len; i++){
                    if(word.charAt(i) != nWord.word.charAt(i)) difChar++;
                }

                if(difChar == 1) que.offer(new Word(word, nWord.cnt + 1));
            }
        }
        
        return 0;
    }
}