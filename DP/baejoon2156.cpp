#include <iostream>
#include <algorithm>
using namespace std;
int ans[10001], arr[10001];

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;

	for (int i = 1; i <= n; i++)
		cin >> arr[i];
	ans[1] = arr[1];
	for (int i = 2; i <= n; i++) 
		ans[i] = max(ans[i - 1], max(ans[i - 2] + arr[i], ans[i - 3] + arr[i - 1] + arr[i]));
	cout << ans[n];
}