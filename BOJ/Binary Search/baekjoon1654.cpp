#include <iostream>
#include <vector>
using namespace std;

long long K, N, answer, ma = 0;
vector<long> v;

void search(int min, int max) {
	int mid = min + (max - min) / 2, cnt = 0;
	if (min == mid) {
		answer = mid;
		return;
	}
	for (int j = 0; j < K; j++) {
		cnt += v[j] / mid;
		if (cnt > N) break;
	}
	if (cnt >= N) search(mid, max);
	else search(min, mid);
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> K >> N;
	for (int i = 0; i < K; i++){
		int a;
		cin >> a;
		v.push_back(a);
		ma += a;
	}
	ma /= N;
	search(1, ma+1);

	cout << answer;
}