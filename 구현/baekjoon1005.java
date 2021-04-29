import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1005 {
	static int T, N, K, minTime, W;
	static int[] buildTime, indegree;
	static Queue<Integer> queue;
	static int[][] adjArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(in.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			buildTime = new int[N];
			adjArr = new int[N][N];
			st = new StringTokenizer(in.readLine(), " ");
			for(int i = 0 ; i < N; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}
			int from, to;
			indegree = new int[N];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				from = Integer.parseInt(st.nextToken())-1;
				to = Integer.parseInt(st.nextToken())-1;
				indegree[to]++;
				adjArr[from][to]=1;
			}
			W = Integer.parseInt(in.readLine());
			queue = new LinkedList<Integer>();
			for(int i = 0; i < N; i++) {
				if(indegree[i] == 0) {
					queue.add(i);
				}
			}
			int size, time;
			minTime = 0;
			while(!queue.isEmpty()) {
				size = queue.size();
				time = Integer.MIN_VALUE;
				boolean found = false;
				for(int poll = 0; poll < size; poll++) {
					int curr = queue.poll();
					time = Math.max(time, buildTime[curr]);
					if(curr == W-1) found = true;
					for(int i=0; i<N; i++) {
						if(adjArr[curr][i]==1) {
							indegree[i]--;
							if(indegree[i]==0) {
								queue.add(i);
							}
						}
					}
					
				}
				minTime += time;
				if(found) break;
			}
			out.append(String.format("%d\n", minTime));
		}
		out.close();
	}

}