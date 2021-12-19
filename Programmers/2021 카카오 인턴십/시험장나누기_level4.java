import java.util.*;

class Solution {
    static int ans = Integer.MAX_VALUE, tempAns, K, Root;
    static int[] Seq;
    static Node[] nodeArr;
    
    static class Node{
        int num, left, right, sum;
        boolean isDisconnected;
        
        public Node(int num, int left, int right){
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }
    public int solution(int k, int[] num, int[][] links) {
        boolean[] visited = new boolean[num.length];
        nodeArr = new Node[num.length];
        K = k;
        
        for(int i = 0; i < num.length; i++){
            nodeArr[i] = new Node(num[i], links[i][0], links[i][1]);
            
            if(links[i][0] != -1) visited[links[i][0]] = true;
            if(links[i][1] != -1) visited[links[i][1]] = true;
        }
        
        for(int i = 0; i < num.length; i++){
            if(!visited[i]) Root = i;
        }
        
        Seq = new int[k - 1];
        Comb(0, 0);
        
        return ans;
    }
    
    void Comb(int idx, int cnt){
        if(cnt == K - 1){
            for(int num : Seq){
                nodeArr[num].isDisconnected = true;
            }
            
            tempAns = 0;
            Divide(Root);
            tempAns = Math.max(tempAns, nodeArr[Root].sum);
            
            for(int num : Seq){
                nodeArr[num].isDisconnected = false;
            }
            
            ans = Math.min(ans, tempAns);
            return;
        }
        
        for(int i = idx; i < nodeArr.length; i++){
            Seq[cnt] = i;
            Comb(i + 1, cnt + 1);
        }
    }
    
    void Divide(int n){
        Node node = nodeArr[n];
        
        if(node.left != -1) Divide(node.left);
        if(node.right != -1) Divide(node.right);
        
        node.sum = 0;
        if(!(node.left == -1 || nodeArr[node.left].isDisconnected)) node.sum += nodeArr[node.left].sum;
        if(!(node.right == -1 || nodeArr[node.right].isDisconnected)) node.sum += nodeArr[node.right].sum;
        node.sum += node.num;
        
        if(node.isDisconnected){
            tempAns = Math.max(tempAns, node.sum);
        }
    }
}
