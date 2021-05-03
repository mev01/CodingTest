#include <iostream>
using namespace std;

int n, map[6562][6562];

void solve(int x, int y, int dis) {
	if (dis == 3) {
		for (int i = x + dis / 3; i <= x + dis / 3 * 2 - 1; i++) {
			for (int j = y + dis / 3; j <= y + dis / 3 * 2 - 1; j++) {
				map[i][j]++;
			}
		}
		return;
	}
	for (int i = 0; i < 3; i++){
		for (int j = 0; j < 3; j++){
			solve(x + dis/3 * i, y + dis/3 * j, dis / 3);
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n;
	solve(1, 1, n);
	for (int i = 1; i <= n; i++){
		for (int j = 1; j <= n; j++){
			if (!map[i][j]) cout << "*";
			else cout << " ";
		}
		cout << "\n";
	}
}