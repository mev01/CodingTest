class Solution {
    public String solution(String p) {
        String answer =  makeCorrect(p);
        
        return answer;
    }
    
    String makeCorrect(String str){
        if(str.equals("")) return str;
        
        int idx = getDivideIdx(str);
        String u = str.substring(0, idx);
        String v = str.substring(idx, str.length());
        
        if(isCorrect(u)) return u + makeCorrect(v);
        else{
            v = "(" + makeCorrect(v) + ")";
            u = u.substring(1, u.length() - 1);
            
            return v + Reverse(u);
        }
    }
    
    int getDivideIdx(String str){
        int cnt = 0;
        
        for(int i = 0; i < str.length(); i++){
            cnt += (str.charAt(i) == '(') ? 1 : -1;
            
            if(cnt == 0) return i + 1;
        }
        
        return str.length();
    }
    
    boolean isCorrect(String str){
       int cnt = 0;
        
        for(int i = 0; i < str.length(); i++){
            cnt += (str.charAt(i) == '(') ? 1 : -1;
            
            if(cnt < 0) return false;
        }
        
        return true;
    }
    
    String Reverse(String str){
        String newStr = "";
        
        for(int i = 0; i < str.length(); i++){
            newStr += (str.charAt(i) == '(') ? ")" : "(";
        }
        
        return newStr;
    }
}