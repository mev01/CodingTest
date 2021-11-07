import java.util.*;
import java.util.regex.*;

class Solution {
    static Map<String, Page> map;
    
    static class Page{
        int cnt;
        double matchingScore;
        ArrayList<String> linkList;
        
        public Page(int cnt){
            this.cnt = cnt;
            linkList = new ArrayList<String>();
        }
    }
    public int solution(String word, String[] pages) {
        map = new LinkedHashMap<String, Page>();
        word = word.toUpperCase();
        
        for(String page : pages){
            String url = "";
            int cnt = 0;
            page = page.toUpperCase();
            
            Pattern pattern = Pattern.compile("(<META PROPERTY=\"OG:URL\" CONTENT=\")(.+)(\"/>)");
            Matcher matcher = pattern.matcher(page);
            matcher.find();
            url = matcher.group(2);
            
            pattern = Pattern.compile("(?<![A-Z])(" + word + ")(?![A-Z])");
            matcher = pattern.matcher(page);
            while(matcher.find()){
                cnt++;
            }
            
            // System.out.println(cnt);
            map.put(url, new Page(cnt));
            
            pattern = Pattern.compile("(<A HREF=\"HTTPS://)(.+)(\">)");
            matcher = pattern.matcher(page);
            while(matcher.find()){
                map.get(url).linkList.add("HTTPS://" + matcher.group(2));
            }
        }
        
        for (String key : map.keySet()) {
            Page page = map.get(key);
            page.matchingScore += page.cnt;
            
            double tempScore = page.linkList.size() == 0 ? 0 : page.cnt / page.linkList.size();
            for(String link : page.linkList){
                if(map.containsKey(link)){
                    Page nPage = map.get(link);
                    nPage.matchingScore += tempScore;
                }
            }
        }
        
        int answer = 0, idx = 0;
        double max = 0;
        for (String key : map.keySet()) {
            Page page = map.get(key);
            if(page.matchingScore > max){
                max = page.matchingScore;
                answer = idx;
            }
            idx++;
        } 
        
        return answer;
    }
}