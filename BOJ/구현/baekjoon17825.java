import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class beakjoon17825 {
	static int ans = 0;
	static int[][] horse = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
	static int[] dice;
	static int[][] Route = {{},
							{10, 13, 16, 19, 25, 30, 35, 40},
							{20, 22, 24, 25, 30, 35, 40},
							{30, 28, 27, 26, 25, 30, 35, 40},
							{}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		Route[0] = new int[20];
		Game(0, 0);
		
		System.out.println(ans);
	}
	
	static void Game(int turn, int score) {
		if(turn == 10) {
			ans = Math.max(ans, score);
			return;
		}
		
		int number = dice[turn];
		loop:
		for (int i = 0; i < 4; i++) {
			if(horse[i][0] == 4) continue; 
			
			int routeNum = horse[i][0];
			int roomNum = horse[i][1];
			if((routeNum == 0 && horse[i][1] * 2 != 40) && (horse[i][1] * 2) % 10 == 0) {
				horse[i][0] = horse[i][1] * 2 / 10;
				horse[i][1] = number;
			}
			else {
				horse[i][1] = roomNum + number;
			}
			
			for (int j = 0; j < 4; j++) {
				if(j != i && horse[i][0] == horse[j][0] && horse[i][1] == horse[j][1]) {
					horse[i][0] = routeNum;
					horse[i][1] = roomNum;
					continue loop;
				}
				if(horse[i][1] == Route[horse[i][0]].length && horse[j][1] == Route[horse[j][0]].length) {
					horse[i][0] = routeNum;
					horse[i][1] = roomNum;
					continue loop;
				}
			}
			
			
			if(horse[i][1] * 2 > 40 || (horse[i][0] != 0 && horse[i][1] >= Route[horse[i][0]].length)) {
				horse[i][0] = 4;
				Game(turn + 1, score);
			}
			else if(horse[i][0] == 0) Game(turn + 1, score + horse[i][1] * 2);
			else Game(turn + 1, score + Route[horse[i][0]][horse[i][1]]);
			
			horse[i][0] = routeNum;
			horse[i][1] = roomNum;
		}
			
	}
}
