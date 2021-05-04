import java.util.Scanner;

public class EA1493 {
	static int[] map=new int[301];
	static int x, y;
	
	public static void main(String[] args) {
		int num, p, q;
		Scanner sc=new Scanner(System.in);
		
		num=sc.nextInt();
		for (int i = 1; i <= 300; i++) {
			map[i]=i+map[i-1];
		}
		for (int i = 1; i <= num; i++) {
			int x1, y1, x2, y2, answer;
			p=sc.nextInt();
			q=sc.nextInt();
			FindIndex(p);
			x1=x; y1=y;
			FindIndex(q);
			x2=x; y2=y;
			
			answer=FindDot(x1+x2, y1+y2);
				
			System.out.println("#"+i+" "+answer);
		}
	}
	
	public static void FindIndex(int p) {
		int index=0, dis;
		for (int i = 1; i <= 141; i++) {
			if(map[i]>=p) {
				index=i;
				break;
			}
		}
		dis=map[index]-p;
		x=index-dis;
		y=1+dis;
	}
	
	public static int FindDot(int x, int y) {
		int dis=y-1;
		x=x+dis;
		return map[x]-dis;	
	}
	
}
