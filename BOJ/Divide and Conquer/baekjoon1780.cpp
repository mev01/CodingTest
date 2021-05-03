#include <iostream>
using namespace std;

int map[2188][2188], ans[3];

bool isAns(int xs, int xe, int ys, int ye) {
	int com = map[xs][ys];
	for (int i = xs; i <= xe; i++){
		for (int j = ys; j <= ye; j++) {
			if (map[i][j] != com) return false;
		}
	}
	ans[com + 1]++;
	return true;
}

void Divide(int xs, int xe, int ys, int ye) {
	if (!isAns(xs, xe, ys, ye)) {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				Divide(((xe - xs + 1) / 3 * i) + xs, (xe - xs + 1) / 3 * (i + 1) + (xs - 1), ((ye - ys + 1) / 3 * j) + ys, (ye - ys + 1) / 3 * (j + 1) + (ys - 1));
			}
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;
	for (int i = 1; i <= n; i++){
		for (int j = 1; j <= n; j++){
			cin >> map[i][j];
		}
	}

	Divide(1, n, 1, n);
	for (int i = 0; i < 3; i++){
		cout << ans[i] << "\n";
	}
}