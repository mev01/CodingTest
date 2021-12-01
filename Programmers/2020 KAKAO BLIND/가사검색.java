import java.util.*;

class Solution {
    static class Trie{
        HashMap<Integer, Integer> cntMap;
        HashMap<Character, Trie> map;
        
        public Trie(){
            cntMap = new HashMap<>();
            map = new HashMap<>();
        }
    }
    public int[] solution(String[] words, String[] queries) {
        Trie Root = new Trie();
        Trie reverseRoot = new Trie();
        
        for(String word : words){
            char[] wordToChar = word.toCharArray();
            
            Trie nTrie = Root;
            for(int i = 0; i < wordToChar.length; i++){
                char ch = wordToChar[i];
                
                if(!nTrie.map.containsKey(ch)){
                    nTrie.map.put(ch, new Trie());
                }
                if(!nTrie.cntMap.containsKey(wordToChar.length - i)){
                    nTrie.cntMap.put(wordToChar.length - i, 0);
                }
                nTrie.cntMap.put(wordToChar.length - i, nTrie.cntMap.get(wordToChar.length - i) + 1);
                nTrie = nTrie.map.get(ch);
            }
            
            nTrie = reverseRoot;
            for(int i = wordToChar.length - 1; i >= 0; i--){
                char ch = wordToChar[i];
                
                if(!nTrie.map.containsKey(ch)){
                    nTrie.map.put(ch, new Trie());
                }
                if(!nTrie.cntMap.containsKey(i + 1)){
                    nTrie.cntMap.put(i + 1, 0);
                }
                nTrie.cntMap.put(i + 1, nTrie.cntMap.get(i + 1) + 1);
                nTrie = nTrie.map.get(ch);
            }
        }
        
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            char[] wordToChar = queries[i].toCharArray();
            boolean isReverse = false;
            
            if(wordToChar[0] == '?'){
                StringBuilder sb = new StringBuilder(queries[i]);
                wordToChar = sb.reverse().toString().toCharArray();
                isReverse = true;
            }
            
            Trie nTrie = isReverse ? reverseRoot : Root;
            for(int j = 0; j < wordToChar.length; j++){
                char ch = wordToChar[j];
                
                if(ch == '?'){
                    if(!nTrie.cntMap.containsKey(wordToChar.length - j)) answer[i] = 0;
                    else answer[i] = nTrie.cntMap.get(wordToChar.length - j);
                    break;
                }
                if(!nTrie.map.containsKey(ch)){
                    answer[i] = 0;
                    break;
                }
                if(j == wordToChar.length - 1){
                    if(!nTrie.cntMap.containsKey(1)) answer[i] = 0;
                    else answer[i] = nTrie.cntMap.get(1);
                    // System.out.println(i);
                    break;
                }
                nTrie = nTrie.map.get(ch);
            }
        }
        
        return answer;
    }
}