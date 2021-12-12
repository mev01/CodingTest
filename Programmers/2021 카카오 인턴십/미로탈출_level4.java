import java.util.*;

class Solution {
    static int roadLen;
    
    static class Room{
        boolean isTrap;
        int trapNum;
        ArrayList<Integer> connectedRoads = new ArrayList<>();
    }
    
    static class User implements Comparable<User>{
        int num, cnt, activeTrap;
        
        public User(int num, int cnt, int activeTrap){
            this.num = num;
            this.cnt = cnt;
            this.activeTrap = activeTrap;
        }
        
        @Override
        public int compareTo(User o){
            return this.cnt - o.cnt;
        }
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        Room[] roomArr = new Room[n + 1];
        for(int i = 1; i <= n; i++){
            roomArr[i] = new Room();
        }
        
        roadLen = roads.length;
        for(int i = 0; i < roadLen; i++){
            roomArr[roads[i][0]].connectedRoads.add(i);
            roomArr[roads[i][1]].connectedRoads.add(i);
        }
        
        for(int i = 0; i < traps.length; i++){
            roomArr[traps[i]].isTrap = true;
            roomArr[traps[i]].trapNum = 1 << i;
        }
        
        int answer = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        
        PriorityQueue<User> que = new PriorityQueue<>();
        que.offer(new User(start, 0, 0));
        
        while(!que.isEmpty()){
            User user = que.poll();
            Room room = roomArr[user.num];
            
            if(user.num == end){
                return user.cnt;
            }
            
            System.out.println(user.num + " " + user.cnt);
            
            for(int idx : room.connectedRoads){
                boolean isReverse = false;
                if(roomArr[roads[idx][0]].isTrap){
                    isReverse = (user.activeTrap & roomArr[roads[idx][0]].trapNum) != 0 ? !isReverse : isReverse;
                }
                if(roomArr[roads[idx][1]].isTrap){
                    isReverse = (user.activeTrap & roomArr[roads[idx][1]].trapNum) != 0 ? !isReverse : isReverse;
                }
                
                int from = isReverse ? roads[idx][1] : roads[idx][0];
                int to = isReverse ? roads[idx][0] : roads[idx][1];
                
                System.out.println(from + " " + to);
                
                if(user.num != from) continue;
                if(!roomArr[to].isTrap && visited[to]) continue;
                
                visited[to] = true;
                int nTrap = roomArr[to].isTrap ? user.activeTrap ^ roomArr[to].trapNum : user.activeTrap;
                
                que.offer(new User(to, user.cnt + roads[idx][2], nTrap));
            }
            System.out.println();
        }
        
        return answer;
    }
}