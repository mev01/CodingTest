import java.util.*;

class Solution {
    static class fileName implements Comparable<fileName>{
        String origin, head, tail;
        int idx, num;
        
        public fileName(String origin, String head, int num, String tail, int idx){
            this.origin = origin;
            this.head = head;
            this.tail = tail;
            this.idx = idx;
            this.num = num;
        }
        
        @Override
        public int compareTo(fileName f){
            if(this.head.equals(f.head) && this.num == f.num) return this.idx - f.idx;
            if(this.head.equals(f.head)) return this.num - f.num;
            return this.head.compareTo(f.head);
        }
    }
    public String[] solution(String[] files) {
        fileName[] fileArr = new fileName[files.length];
        
        for(int i = 0; i < files.length; i++){
            int s = 0, e = 0, j;
            for(j = 0; j < files[i].length(); j++){
                if(s == 0 && files[i].charAt(j) >= '0' && files[i].charAt(j) <= '9') s = j;
                if(s != 0 && !(files[i].charAt(j) >= '0' && files[i].charAt(j) <= '9')) break;
            }
            e = j;
            
            fileArr[i] = new fileName(files[i], files[i].substring(0, s).toUpperCase(), 
                                      Integer.parseInt(files[i].substring(s, e)), files[i].substring(e), i);
        }
        
        Arrays.sort(fileArr);
        
        for(int i = 0; i < files.length; i++){
            files[i] = fileArr[i].origin;
        }
        return files;
    }
}