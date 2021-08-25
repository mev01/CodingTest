import java.util.*;

class Solution {
    static int N;
    static int[] distS, distA, distB;
    static boolean[] visitedS, visitedA, visitedB;
    static ArrayList<Road>[] roadList;
    static PriorityQueue<Road> que;
    
    static class Road implements Comparable<Road>{
        int end, cost;
        
        public Road(int end, int cost){
            this.end = end;
            this.cost = cost;
        }

		@Override
		public int compareTo(Road o) {
			return this.cost- o.cost;
		}
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        
        // farse 통해 list 생성
        roadList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            roadList[i] = new ArrayList<Road>();
        }
        
        int len = fares.length;
        for(int i = 0; i < len; i++){
            int nodeA = fares[i][0];
            int nodeB = fares[i][1];
            int cost = fares[i][2];
            
            roadList[nodeA].add(new Road(nodeB, cost));
            roadList[nodeB].add(new Road(nodeA, cost));
        }
        
        // dijkstra를 통해 거리 생성
        distS = new int[N + 1];
        distA = new int[N + 1];
        distB = new int[N + 1];
        
        dijkstra(s, distS);
        dijkstra(a, distA);
        dijkstra(b, distB);
        
        // System.out.println(Arrays.toString(distS));
        // System.out.println(Arrays.toString(distA));
        // System.out.println(Arrays.toString(distB));
            
        // 각 노드를 돌며 검사
        int min = Integer.MAX_VALUE;
        for(int mid = 1; mid <= N; mid++){
            int sum = distS[mid] + distA[mid] + distB[mid];
            if(distS[mid] == Integer.MAX_VALUE || distA[mid] == Integer.MAX_VALUE 
               || distB[mid] == Integer.MAX_VALUE) sum = Integer.MAX_VALUE;
                
            min = Math.min(min, sum);
        }
        
        return min;
    }
    
    public void dijkstra(int start, int[] distArr){
        que = new PriorityQueue<Road>();
        
        // dist배열 초기화
        for(int i = 1; i <= N; i++){
            distArr[i] = Integer.MAX_VALUE;
        }
        // 시작 노드 que에 넣기
        distArr[start] = 0;
        que.offer(new Road(start, 0));
        
        while(!que.isEmpty()){
            Road road = que.poll();
            
            // 갱신되는지 확인
            for(Road nRoad : roadList[road.end]){
                if(distArr[nRoad.end] > road.cost + nRoad.cost){
                    Road updateRoad = findUpdateRoad(nRoad.end);
                    
                    if(updateRoad != null) que.remove(updateRoad);
                    
                    distArr[nRoad.end] = road.cost + nRoad.cost;
                    que.offer(new Road(nRoad.end, distArr[nRoad.end]));
                }
            }
        }
    }
    
    public Road findUpdateRoad(int num){
        for(Road road : que){
            if(road.end == num){
                return road;
            }
        }
        
        return null;
    }
}