import java.util.*;

class Solution {
    static int N, ans = Integer.MAX_VALUE;
    static int[] weakArr, distArr, weakLine, permFriendsArr;
    static ArrayList<Integer> friends;
    static boolean[] subsetArr, visitedPerm;
    
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
            
            permFriendsArr = new int[friends.size()];
            visitedPerm = new boolean[friends.size()];
            permFriends(0);
            
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
    
    void permFriends(int idx){
        if(idx == friends.size()){
            if(canInspection()){
                ans = Math.min(ans, friends.size());
            }
            return;
        }
        
        for(int i = 0; i < friends.size(); i++){
            if(!visitedPerm[i]){
                visitedPerm[i] = true;
                permFriendsArr[idx] = friends.get(i);
                
                permFriends(idx + 1);
                visitedPerm[i] = false;
            }
        }
    }
    
    Boolean canInspection(){
        for(int i = 0; i < weakArr.length; i++){
            int dist = i;
            
            for(int j = 0; j < friends.size(); j++){
                int num = weakLine[dist] + permFriendsArr[j];
                int idx = Arrays.binarySearch(weakLine, num);
                if(idx < 0) idx = (idx + 1) * -1 - 1;
                
                if(idx >= i + weakArr.length - 1) return true;
                dist = idx + 1;
            }
        }
        
        return false;
    }
}