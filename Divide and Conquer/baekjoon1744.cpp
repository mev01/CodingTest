#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n, x, pn = 0, mn = 0, n1 = 0;
	long ans = 0;
	vector<int> pl, min;

	cin >> n;
	while (n--){
		cin >> x;
		if (x > 1) {
			pn++;
			pl.push_back(x);
		}
		else if (x <= 0) {
			mn++;
			min.push_back(x);
		}
		else n1++;
	}

	sort(pl.begin(), pl.end());
	sort(min.begin(), min.end());

	for (int i = pn - 1; i > 0; i -= 2) {
		ans += pl[i] * pl[i - 1];
	}
	if (pn % 2 != 0) ans += pl[0];
	for (int i = 0; i < mn - 1; i += 2) {
		ans += min[i] * min[i + 1];
	}
	if (mn % 2 != 0) ans += min[mn - 1];
	cout << ans + n1;
}