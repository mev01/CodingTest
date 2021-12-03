import java.util.*;

class Solution {
    static int[] Order;
    static Room[] roomArr;
    static boolean[] visited, isNotPass, essentialRoom;
    
    static class Room{
        ArrayList<Integer> list;
        
        public Room(){
            list = new ArrayList<>();
        }
    }
    
    public boolean solution(int n, int[][] paths, int[][] orders) {
        roomArr = new Room[n];
        for(int i = 0; i < n; i++){
            roomArr[i] = new Room();
        }
        
        for(int[] path : paths){
            roomArr[path[0]].list.add(path[1]);
            roomArr[path[1]].list.add(path[0]);
        }
        
        isNotPass = new boolean[n];
        essentialRoom = new boolean[n];
        Order = new int[n];
        
        for(int[] order : orders){
            Order[order[0]] = order[1];
            essentialRoom[order[0]] = true;
            if(order[0] != 0) isNotPass[order[1]] = true;
        }
        
        visited = new boolean[roomArr.length];
        if(!isNotPass[0]) Visit(0);
         
        for(boolean isVisit : visited){
            if(!isVisit) return false;
        }
        
        return true;
    }
    
    void Visit(int startNum){
        visited = new boolean[roomArr.length];
        Queue<Integer> que = new LinkedList<>();
        ArrayList<Integer> visitedEssentialRoom = new ArrayList<>();
        
        que.offer(startNum);
        visited[startNum] = true;
        
        while(!que.isEmpty()){
            int roomNum = que.poll();
            Room room = roomArr[roomNum];
            
            for(int num : room.list){
                if(!visited[num] && !isNotPass[num]){
                    visited[num] = true;
                    if(essentialRoom[num]){
                        essentialRoom[num] = false;
                        visitedEssentialRoom.add(num);
                    } 
                    
                    que.offer(num);
                }
            }
        }
        
        boolean flag = false;
        for(int num : visitedEssentialRoom){
            // System.out.println("visited " + num);
            isNotPass[Order[num]] = false;
            flag = true;
        }
        System.out.println();
        
        if(flag) Visit(0);
    }
}