#include <iostream>
using namespace std;

int n, map[65][65];

bool check(int x1, int x2, int y1, int y2) {
	int chk = map[x1][y1];
	for (int i = x1; i <= x2; i++){
		for (int j = y1; j <= y2; j++) {
			if (chk != map[i][j]) return false;
		}
	}
	return true;
}

void solve(int x1, int x2, int y1, int y2) {
	int dis = (x2 - x1 + 1) / 2;
	if(x1==x2)
		cout << map[x1][y1];
	else {
		if (check(x1, x2, y1, y2)) cout << map[x1][y1];
		else {
			cout << "(";
			solve(x1, x1 + dis - 1, y1, y1 + dis - 1);
			solve(x1, x1 + dis - 1, y2 - dis + 1, y2);
			solve(x2 - dis + 1, x2, y1, y1 + dis - 1);
			solve(x2 - dis + 1, x2, y2 - dis + 1, y2);
			cout << ")";
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string a;

	cin >> n;
	for (int i = 1; i <= n; i++){
		cin >> a;
		for (int j = 0; j < n; j++){
			map[i][j + 1] = a[j] - '0';
		}
	}

	solve(1, n, 1, n);
}