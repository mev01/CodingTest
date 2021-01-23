import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class beakjoon10992 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int numLine = Integer.parseInt(br.readLine());
		int numStar = numLine*2 - 1;	//한 줄의 별 갯수
		int midStarIndex = numStar/2 + 1;
		
		for (int Line = 0; Line < numLine - 1; Line++) {
			for (int starIndex = 1; starIndex <= numStar; starIndex++) {
				if(starIndex == midStarIndex - Line){
					bw.write("*");
					if(Line == 0) break;
				}
				else if(starIndex == midStarIndex + Line){
					bw.write("*");
					break;
				}
				else
					bw.write(" ");	
			}
			bw.write("\n");
		}
		for (int starIndex = 1; starIndex <= numStar; starIndex++) //마지막 줄
			bw.write("*");
		bw.flush();
		bw.close();
	}
}
