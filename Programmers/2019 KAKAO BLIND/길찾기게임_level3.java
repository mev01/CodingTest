import java.util.*;

class Solution {
    static int num = 0;
    static int[][] ansArr;
    static Node rootNode;
    static Node[] nodeArr;
    
    static class Node implements Comparable<Node>{
        int num, r, c;
        Node parent, left, right;
        
        public Node(int num, int r, int c){
            this.num = num;
            this.r = r;
            this.c = c;
        }
        
        @Override
        public int compareTo(Node o){
            return this.r - o.r;
        }
    }
    public int[][] solution(int[][] nodeinfo) {
        int size = nodeinfo.length;
        nodeArr = new Node[size];
        
        for(int i = 0; i < size; i++){
            nodeArr[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        
        Arrays.sort(nodeArr);
        
        rootNode = nodeArr[0];
        for(int i = 1; i < size; i++){
            if(nodeArr[i].c > rootNode.c) rootNode = nodeArr[i];
            if(nodeArr[i - 1].c > nodeArr[i].c){
                nodeArr[i].parent = nodeArr[i - 1];
                nodeArr[i - 1].right = nodeArr[i];
            }
            else{
                Node node = findSmallerNode(nodeArr[i - 1], nodeArr[i].c);
                
                if(node.parent != null){
                    nodeArr[i].parent = node.parent;
                    node.parent.right = nodeArr[i];
                } 
                node.parent = nodeArr[i];
                nodeArr[i].left = node;
            }
        }
        
        ansArr = new int[2][size];
        preorder(rootNode);
        num = 0;
        postorder(rootNode);
        
        System.out.println(rootNode.r);
        return ansArr;
    }
    
    Node findSmallerNode(Node n, int c){
        if(n.parent == null || n.parent.c > c) return n;
        return findSmallerNode(n.parent, c);
    }
    
    void preorder(Node n){
        ansArr[0][num++] = n.num;
        if(n.left != null) preorder(n.left);
        if(n.right != null) preorder(n.right);
    }
    
    void postorder(Node n){
        if(n.left != null) postorder(n.left);
        if(n.right != null) postorder(n.right);
        ansArr[1][num++] = n.num;
    }
}