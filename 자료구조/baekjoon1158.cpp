#include <iostream>
#include <queue>
using namespace std;


int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int N, M, num[5001], now = 0;
	cin >> N >> M;
	
	for (int i = 1; i <=N; i++){
		num[i] = i;
	}

	cout << "<";
	/*for (int i = 1; i <= N-1; i++) {
		for (int j = 0; j < M-1; j++){
			q.push(q.front());
			q.pop();
		}
		cout << q.front() << ", ";
		q.pop();
	}
	cout << q.front() << ">";*/
	while (N>0)
	{
		now = (now + M) % N;
		if (now == 0) now = N;
		if (N == 1) cout << num[1] << ">";
		else {
			cout << num[now] << ", ";
			for (int i = now; i < N; i++) {
				num[i] = num[i + 1];
			}
			now -= 1;
		}
		N -= 1;
	}
}