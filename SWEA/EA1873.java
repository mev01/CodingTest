import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA1873 {
	static char[][] map;
	static int[] dirX = new int[] {-1,1,0,0}, dirY = new int[] {0,0,-1,1};
	static int H = 0, W = 0, preX = 0, preY = 0, state = 0;
	
	static void move(int dir) {
		state = dir;
		if(preX+dirX[state] >= 0 && preX+dirX[state] < H && preY+dirY[state] >= 0 && preY+dirY[state] < W)
			if(map[preX+dirX[state]][preY+dirY[state]] == '.') {
				preX += dirX[state];
				preY += dirY[state];
			}
	}
	
	static void shoot() {
		int x = preX, y = preY;
		while(x >= 0 && x < H && y >= 0 && y < W) {
			if(map[x][y] == '*') {
				map[x][y] = '.';
				break;
			}
			else if(map[x][y] == '#') {
				break;
			}
			x +=  dirX[state];
			y += dirY[state];
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new char[20][20];
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 맵 그리기
			for (int h = 0; h < H; h++) {
				String element = br.readLine();
				for (int w = 0; w < W; w++) {
					map[h][w] = element.charAt(w);
					if(map[h][w]=='^') {
						preX = h;
						preY = w;
						state = 0;
						map[h][w] = '.';
					}
					else if(map[h][w]=='v') {
						preX = h;
						preY = w;
						state = 1;
						map[h][w] = '.';
					}
					else if(map[h][w]=='<') {
						preX = h;
						preY = w;
						state = 2;
						map[h][w] = '.';
					}
					else if(map[h][w]=='>') {
						preX = h;
						preY = w;
						state = 3;
						map[h][w] = '.';
					}
				}
			}
			int numInput = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			//명령어 처리
			for (int j = 0; j < numInput; j++) {
				if(input.charAt(j) == 'U') {
					move(0);
				}
				else if(input.charAt(j) == 'D') {
					move(1);
				}
				else if(input.charAt(j) == 'L') {
					move(2);
				}
				else if(input.charAt(j) == 'R') {
					move(3);
				}
				else if(input.charAt(j) == 'S') {
					shoot();
				}
			}
			
			//전차그리기
			if(state == 0) {
				map[preX][preY] = '^';
			}
			else if(state == 1) {
				map[preX][preY] = 'v';
			}
			else if(state == 2) {
				map[preX][preY] = '<';
			}
			else if(state == 3) {
				map[preX][preY] = '>';
			}
			
			//출력
			sb.append("#"+i+" ");
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					sb.append(map[h][w]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb.toString());
	}

}
