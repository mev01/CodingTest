import java.util.*;

class Solution {
    static class Trie{
        int cnt;
        HashMap<Character, Trie> map;
            
        public Trie(){
            this.cnt = 0;
            map = new HashMap<>();
        }
    }
    public int solution(String[] words) {
        Trie root = new Trie();
        
        for(String word : words){
            char[] charArr = word.toCharArray();
            
            Trie nTrie = root;
            for(char ch : charArr){
                if(!nTrie.map.containsKey(ch)){
                    nTrie.map.put(ch, new Trie());
                }
                nTrie.map.get(ch).cnt += 1;
                nTrie = nTrie.map.get(ch);
            }
        }
        
        int answer = 0;
        for(String word : words){
            char[] charArr = word.toCharArray();
            
            Trie nTrie = root;
            for(int i = 0; i < charArr.length; i++){
                if(nTrie.map.get(charArr[i]).cnt == 1){
                    answer += i + 1;
                    break;
                }
                if(i == charArr.length - 1) answer += charArr.length;
                nTrie = nTrie.map.get(charArr[i]);
            }
        }
        
        return answer;
    }
}