import java.util.*;

class Solution {
    int answer;
    int[] info;
    int[][] edges, childArr;
    Queue<Person> que;
    
    class Person{
        int nS, nW;
        boolean[] visited;
        
        public Person(int nS, int nW, boolean[] visited){
            this.nS = nS;
            this.nW = nW;
            this.visited = visited;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;
        childArr = new int[info.length][2];
        
        for(int[] edge : edges){
            int p = edge[0], c = edge[1];
            
            if(childArr[p][0] == 0) childArr[p][0] = c;
            else childArr[p][1] = c;
        }
        
        boolean[] visited = new boolean[info.length];
        visited[0] = true;
        Person root = new Person(1, 0, visited);
        
        que = new LinkedList<>();
        que.offer(root);
        
        while(!que.isEmpty()){
            Person person = que.poll();
            visited = person.visited;
            
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < info.length; i++){
                if(visited[i]){
                    if(!visited[childArr[i][0]]) list.add(childArr[i][0]);
                    if(!visited[childArr[i][1]]) list.add(childArr[i][1]);
                }
            }
            
            for(int idx : list){
                int nS = person.nS, nW = person.nW;
                
                if(info[idx] == 0) nS++;
                else nW++;
                
                answer = nS;
                
                if(nW >= nS) continue;
                
                boolean[] nVisited = person.visited.clone();
                nVisited[idx] = true;
                que.offer(new Person(nS, nW, nVisited));
            }
            
            System.out.println();
        }
        
        
        return answer;
    }
    
    
}