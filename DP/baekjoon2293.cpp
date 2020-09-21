#include <iostream>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n, k;
	int coin[101], arr[10001] = { 0, };

	cin >> n >> k;
	for (int i = 0; i < n; i++)
		cin >> coin[i];

	for (int i = 0; i < n; i++){
		if (coin[i] > k)continue;
		arr[coin[i]] += 1;
		for (int j = coin[i]+1; j <= k; j++){
			arr[j] += arr[j - coin[i]];
		}
	}

	cout << arr[k];
}