import java.util.*;

class Solution {
    static boolean[] visited;
    static Node[] arr;
    static Queue<Node> que;
    
    static class Node implements Comparable<Node>{
        public int num, distance;
        public List<Integer> list;
        
        public Node(int num){
            this.num = num;
            distance = 0;
            list = new ArrayList<Integer>();
        }
        
        @Override
        public int compareTo(Node n){
            return n.distance - this.distance;
        }
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        arr = new Node[n];
        
        for(int i = 0; i < n; i++){
            arr[i] = new Node(i);
        }
        
        // 노드 배열 구성
        for(int[] v : edge){
            int a = v[0] - 1;
            int b = v[1] - 1;
            
            arr[a].list.add(b);
            arr[b].list.add(a);
        }
        
        // bfs 로 탐색
        que = new LinkedList<Node>();
        visited = new boolean[n];
        
        que.offer(arr[0]);
        visited[0] = true;
        
        while(!que.isEmpty()){
            Node node = que.poll();
            
            for(int num : node.list){
                if(visited[num]) continue;
                
                visited[num] = true;
                arr[num].distance = node.distance + 1;
                que.offer(arr[num]);
            }
        }
        
        // 노드 배열 소팅해서 출력
        Arrays.sort(arr);
        
        int max = arr[0].distance;
        for(Node node : arr){
            if(node.distance == max) ++answer;
        }
        
        
        return answer;
    }
}