import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EA1954 {
	static int N, direction;
	static int[] dirX = new int[]{0,1,0,-1}, dirY = new int[]{1,0,-1,0};
	static int[][] map;
	
	static void snail(int x, int y, int num, int dir){
		if(num > N*N) return;
		map[x][y] = num;
		if(x+dirX[dir] < 0 || x+dirX[dir] >= N || y+dirY[dir] < 0 || y+dirY[dir] >= N || map[x+dirX[dir]][y+dirY[dir]] != 0) dir = (dir+1)%4;
		snail(x+dirX[dir], y+dirY[dir], num+1, dir);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	    
	    int T = Integer.parseInt(br.readLine());
	    for (int i = 1; i <= T; i++) {
	    	N = Integer.parseInt(br.readLine());
	    	map = new int[10][10];
	    	
	    	snail(0, 0, 1, 0);
	    	
	    	sb.append("#"+i+"\n");
	    	for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					sb.append(map[x][y]+" ");
				}
				sb.append("\n");
			}
		}
	    System.out.println(sb.toString());
	    br.close();
	}

}
