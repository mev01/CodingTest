import java.util.*;

class Solution {
    static int roadLen;
    
    static class Room{
        boolean isTrap;
        ArrayList<Integer> connectedRoads = new ArrayList<>();
    }
    
    static class User{
        int num, cnt;
        boolean[] reverseArr;
        int[] cntArr;
        
        public User(int num, int cnt, boolean[] reverseArr, int[] cntArr){
            this.num = num;
            this.cnt = cnt;
            this.reverseArr = reverseArr;
            this.cntArr = cntArr;
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
        
        for(int num : traps){
            roomArr[num].isTrap = true;
        }
        
        int answer = Integer.MAX_VALUE;
        boolean[] reverseArr = new boolean[roadLen];
        int[] cntArr = new int[n + 1];
        
        Queue<User> que = new LinkedList<>();
        que.offer(new User(start, 0, reverseArr, cntArr));
        
        while(!que.isEmpty()){
            User user = que.poll();
            Room room = roomArr[user.num];
            
            if(answer <= user.cnt) continue;
            if(user.num == end){
                answer = user.cnt;
            }
            
            // System.out.println(user.num + " " + user.cnt);
            
            for(int idx : room.connectedRoads){
                int from = user.reverseArr[idx] ? roads[idx][1] : roads[idx][0];
                int to = user.reverseArr[idx] ? roads[idx][0] : roads[idx][1];
                
                if(user.num != from) continue;
                
                reverseArr = user.reverseArr.clone();
                cntArr = user.cntArr.clone();
                if(roomArr[to].isTrap){
                    for(int toIdx : roomArr[to].connectedRoads){
                        reverseArr[toIdx] = !reverseArr[toIdx];
                    }
                }
                
                cntArr[to]++;
                if(cntArr[to] > 2) continue;
                
                que.offer(new User(to, user.cnt + roads[idx][2], reverseArr, cntArr));
            }
        }
        
        return answer;
    }
}