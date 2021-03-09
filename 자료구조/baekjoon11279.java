import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon11279 {
	static int N, idx = 1;
	static int[] heap;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		heap = new int[N+1];
		
		int inst;
		for (int i = 0; i < N; i++) {
			inst = Integer.parseInt(br.readLine());
			
			if(inst == 0) {	//삭제
				if(idx == 1) {
					sb.append(0).append('\n');
				}
				else {
					sb.append(heap[1]).append('\n');
					heap[1] = heap[--idx];
					delete(1);
				}
			}
			else {	//삽입
				heap[idx++] = inst;
				insert(idx-1);
			}
		}
		
		System.out.print(sb.toString());
	}
	private static void delete(int i) {
		if(i*2 > idx-1) return;
		if(heap[2*i] > heap[i] || heap[2*i+1] > heap[i]) {
			if(heap[2*i] < heap[2*i + 1] && idx-1 >= 2*i + 1) {
				swap(i, 2*i + 1);
				delete(2*i + 1);
			}
			else {
				swap(i, 2*i);
				delete(2*i);
			}
		}
	}
	private static void insert(int i) {
		if(i == 1) return;
		if(heap[i/2] < heap[i]) {
			swap(i, i/2);
			insert(i/2);
		}
	}
	private static void swap(int a, int b) {
		int temp = heap[a];
		heap[a] = heap[b];
		heap[b] = temp;
	}
}
