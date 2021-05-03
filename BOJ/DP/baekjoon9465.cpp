#include <iostream>
#include <algorithm>
using namespace std;
int Case[3][100001], ans[100001][3];

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int T, n;
	cin >> T;
	while (T--) {
		cin >> n;
		for (int i = 1; i <= n; i++) {
			cin >> Case[1][i];
		}
		for (int i = 1; i <= n; i++) {
			cin >> Case[2][i];
		}
		ans[1][0] = 0;
		ans[1][1] = Case[1][1];
		ans[1][2] = Case[2][1];
		for (int i = 2; i <= n; i++) {
			ans[i][0] = max(ans[i - 1][0], max(ans[i - 1][1], ans[i - 1][2]));
			ans[i][1] = max(ans[i - 1][0] + Case[1][i], ans[i - 1][2] + Case[1][i]);
			ans[i][2] = max(ans[i - 1][0] + Case[2][i], ans[i - 1][1] + Case[2][i]);
		}
		cout << max(ans[n][0], max(ans[n][1], ans[n][2])) << "\n";
	}
}