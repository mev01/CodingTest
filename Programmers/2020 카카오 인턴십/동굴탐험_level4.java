import java.util.*;

class Solution {
    static int[] Order;
    static Room[] roomArr;
    static boolean[] visited, isNotPass, essentialRoom;
    static Queue<Integer> nextRoom;
    
    static class Room{
        ArrayList<Integer> list = new ArrayList<>();
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
        
        visited = new boolean[n];
        nextRoom = new LinkedList<>();
        
        Search(0);
        
        while(!nextRoom.isEmpty()){
            // System.out.print("room ");
            // for(int a : nextRoom){
            //     System.out.print(a + " ");
            // }System.out.println();
            
            int room = nextRoom.poll();
                
            if(!visited[room]){
                boolean isPass = false;
                for(int nRoom : roomArr[room].list){
                    if(visited[nRoom]) isPass = true;
                }
                if(isPass) Search(room);
            }
        }
        
        for(boolean visit : visited){
            if(!visit) return false;
        }
        
        return true;
    }
    
    void Search(int room){
        // System.out.println(room);
        if(isNotPass[room]){
            return;
        }
        
        visited[room] = true;
        if(essentialRoom[room]){
            essentialRoom[room] = false;
            isNotPass[Order[room]] = false;
            
            nextRoom.offer(Order[room]);
        }
        
        for(int nRoom : roomArr[room].list){
            if(!visited[nRoom]) Search(nRoom);
        }
    }
}