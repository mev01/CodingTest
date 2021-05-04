import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class EA1210 {
	static int[][] map = new int [100][100];
	static int answer = 0, isAnswer = 0;
	
	static void up(int y, int x) {
		if(y == 0) {
			answer = x;
			return;
		}
		if(x-1 > 0 && map[y][x-1] == 1) {	//left
			System.out.println("left:  "+y+" "+x);
			left(y, x-1);
		}
		else if(x+1 < 100 && map[y][x+1] == 1) {	//right
			System.out.println("right: "+y+" "+x);
			right(y, x+1);
		}
		else
			up(y-1, x);
	}
	
	static void left(int y, int x) {
		//System.out.println(y+" "+x);
		if(map[y-1][x] == 1) {	//down
			System.out.println("up:    "+y+" "+x);
			up(y-1, x);
		}
		else
			left(y, x-1);
	}
	
	static void right(int y, int x) {
		//System.out.println(y+" "+x);
		if(map[y-1][x] == 1) {	//down
			System.out.println("up:    "+y+" "+x);
			up(y-1, x);
		}
		else
			right(y, x+1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i = 1; i <= 1; i++) {
			br.readLine();
			for (int y = 0; y < 100; y++) {
				String dot = br.readLine();
				StringTokenizer st = new StringTokenizer(dot);
				for (int x = 0; x < 100; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			answer = 0;
			
			up(99, 99);

			bw.write("#"+i+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
