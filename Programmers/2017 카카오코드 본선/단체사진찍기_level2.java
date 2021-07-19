
public class 단체사진찍기_level2 {
    static int num, ans = 0;
    static boolean[] visited = new boolean[8];
    static int[] arr = new int[8];
    static String[] con;
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    
    public static void main(String[] args) {
    	String[] arr = {"N~F=0", "R~T>2"};
		System.out.println(solution(2, arr));
	}
    
    static int solution(int n, String[] data) {
        con = data;
        num = n;
        ans = 0;
        make(0);
        
        return ans;
    }
    
    static void make(int idx){
        if(idx == 8){
            check();
            return;
        }
        
        for(int i = 0; i < 8; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[idx] = i;
                make(idx + 1);
                visited[i] = false;
            }
        }
    }
    
    static void check(){
        int[] idxArr = new int[32];
        
        for(int i = 0; i < 8; i++){
            idxArr[friends[arr[i]] - 'A'] = i;
        }
        
        for(int i = 0; i < num; i++){
            int a = con[i].charAt(0) - 'A';
            int b = con[i].charAt(2) - 'A';
            int c = con[i].charAt(4) - '0';
            
            switch(con[i].charAt(3)){
                case '=':
                    if(!((int)Math.abs(idxArr[a] - idxArr[b]) - 1 == c)) return;
                    break;
                case '<':
                    if(!((int)Math.abs(idxArr[a] - idxArr[b]) - 1 < c)) return;
                    break;
                case '>':
                    if(!((int)Math.abs(idxArr[a] - idxArr[b]) - 1 > c)) return;
                    break;
            }
        }
        ++ans;
    }
}