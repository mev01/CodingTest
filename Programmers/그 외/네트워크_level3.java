import java.util.*;

class Solution {
    static int[] root, numSet;
    
    public int solution(int n, int[][] computers) {
        root = new int[n];
        numSet = new int[n];
        
        for(int i = 0; i < n; i++){
            root[i] = i;
            numSet[i] = 1;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(computers[i][j] == 1){
                    Union(i, j);
                    // System.out.println(Arrays.toString(root));
                    // System.out.println(Arrays.toString(numSet));
                }
            }
        }
        
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < n; i++){
            set.add(find(i));
        }
        
        return set.size();
    }
    
    void Union(int i, int j){
        int rootI = find(i);
        int rootJ = find(j);
        
        if(rootI == rootJ) return;
        
        if(numSet[rootI] >= numSet[rootJ]){
            root[rootJ] = rootI;
            numSet[rootI] += numSet[rootJ];
        } 
        else{
            root[rootI] = rootJ;
            numSet[rootJ] += numSet[rootI];
        } 
    }
    
    int find(int i){
        if(root[i] == i) return i;
        return root[i] = find(root[i]);
    }
}