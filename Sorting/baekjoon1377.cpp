#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n, max = 0;
	cin >> n;
	vector<pair<int, int>> v(n);
	for (int i = 0; i < n; i++){
		cin >> v[i].first;
		v[i].second = i;
	}
	sort(v.begin(),v.end());
	for (int i = 0; i < n; i++){
		if (v[i].second - i > max) max = v[i].second - i;
	}
	cout << max+1;
}