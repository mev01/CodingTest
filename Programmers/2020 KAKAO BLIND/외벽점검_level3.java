import java.util.*;

class Solution {
    static int N, ans = Integer.MAX_VALUE;
    static int[] weakArr, distArr, weakLine;
    static ArrayList<Integer> friends;
    static boolean[] subsetArr;
    
    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        weakArr = weak;
        distArr = dist;
        
        weakLine = new int[weakArr.length * 2 - 1];
        makeWeakToLine();
        
        subsetArr = new boolean[dist.length];
        Subset(0);
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    void makeWeakToLine(){
        for(int i = 0; i < weakLine.length; i++){
            if(i >= weakArr.length) weakLine[i] = weakLine[i - weakArr.length] + N;
            else weakLine[i] = weakArr[i];
        }
    }
    
    void Subset(int idx){
        if(idx == distArr.length){
            friends = calcFriendsToPutIn();
            if(canInspection()){
                ans = Math.min(ans, friends.size());
            }
            
            return;
        }
        
        subsetArr[idx] = false;
        Subset(idx + 1);
        
        subsetArr[idx] = true;
        Subset(idx + 1);
    }
    
    ArrayList<Integer> calcFriendsToPutIn(){
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < subsetArr.length; i++){
            if(subsetArr[i]){
                list.add(distArr[i]);
            }
        }
        
        return list;
    }
    
    Boolean canInspection(){
        for(int i = 0; i < weakArr.length; i++){
            int dist = i;
            
            for(int j = 0; j < friends.size(); j++){
                int num = weakLine[dist] + friends.get(j);
                
                int idx = Arrays.binarySearch(weakLine, num);
                if(idx < 0) idx = (idx + 1) * -1 - 1;
                
                // if(idx >= i + weakArr.length){
                //     System.out.println(Arrays.toString(weakLine));
                //     for(int k : friends){
                //         System.out.print(k + " ");
                //     }
                //     System.out.println();
                //     System.out.println(i + " " + j + " " + idx + " " + (i + weakArr.length) + " " + num);
                // }
                // System.out.println(Arrays.toString(weakLine));
                //     for(int k : friends){
                //         System.out.print(k + " ");
                //     }
                //     System.out.println();
                //     System.out.println(i + " " + j + " " + idx + " " + (i + weakArr.length - 1) + " " + num);
                
                
                if(idx >= i + weakArr.length - 1) return true;
                dist = idx + 1;
            }
        }
        
        return false;
    }
}