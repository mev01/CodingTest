#include <iostream>
using namespace std;
bool arr[1000001] = { 0,1 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int M, N;
	cin >> M >> N;
	for (int i = 2; i * i <= N; i++) {
		if (!arr[i]) {
			for (long long j = i * i; j <= N; j += i) {
				arr[j] = 1;
			}
		}
	}
	for (int i = M; i <= N; i++) {
		if (!arr[i]) cout << i << "\n";
	}
}