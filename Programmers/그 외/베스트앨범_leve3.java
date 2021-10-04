import java.util.*;

class Solution {
    HashMap<String, ArrayList<Music>> map;
    
    static class Music implements Comparable<Music>{
        int code, playTime;
        String name;
        
        public Music(int code, int playTime, String name){
            this.code = code;
            this.playTime = playTime;
            this.name = name;
        }
        
        @Override
        public int compareTo(Music m){
            if(this.playTime == m.playTime) return this.code - m.code;
            return m.playTime - this.playTime;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        map = new HashMap<String, ArrayList<Music>>();
        
        for(int idx = 0; idx < genres.length; idx++){
            if(!map.containsKey(genres[idx])) map.put(genres[idx], new ArrayList<Music>());
            
            map.get(genres[idx]).add(new Music(idx, plays[idx], ""));
        }
        
        ArrayList<Music> genresList = new ArrayList<Music>();
        for(String key : map.keySet()){
            // System.out.println(key);
            
            int cnt = 0;
            ArrayList<Music> list = map.get(key);
            for(Music music : list){
                cnt += music.playTime;
            } 
            
            genresList.add(new Music(0, cnt, key));
        }
        
        Collections.sort(genresList);
        
        ArrayList<Integer> ans = new ArrayList<>();
        for(Music genre : genresList){
            ArrayList<Music> list = map.get(genre.name);
            Collections.sort(list);
            
            ans.add(list.get(0).code);
            if(list.size() > 1) ans.add(list.get(1).code);
        }
        
        int[] ansArr = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            ansArr[i] = ans.get(i);
        }
        return ansArr;
    }
}