#include <iostream>
using namespace std;
long long arr[201];

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int N, K;
	cin >> N >> K;

	arr[0] = 1;
	for (int i = 1; i <= K; i++){
		for (int j = 0; j <= N; j++){
			arr[j] = (arr[j] + arr[j - 1]) % 1000000000;
		}
	}
	cout << arr[N];
}