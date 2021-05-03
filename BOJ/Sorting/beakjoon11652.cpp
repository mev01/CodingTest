#include <iostream>
#include <algorithm>
using namespace std;
long long arr[100000];

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;
	for (int i = 0; i < n; i++){
		cin >> arr[i];
	}
	sort(arr, arr + n);

	int cnt = 0, rem = 1;
	long long ans = arr[0];
	for (int i = 1; i < n; i++) {
		if (arr[i] == arr[i - 1])
			rem++;
		else {
			if (rem > cnt) {
				cnt = rem;
				ans = arr[i - 1];
			}
			rem = 1;
		}
	}
	if (rem > cnt) {
		cnt = rem;
		ans = arr[n - 1];
	}
	cout << ans;
}