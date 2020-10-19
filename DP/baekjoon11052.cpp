#include <iostream>
#include <algorithm>
using namespace std;
int cost[1001], maxcost[1001] = { 0 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n;
	cin >> n;
	for (int i = 1; i <= n; i++)
		cin >> cost[i];

	for (int i = 1; i <= n; i++)
		for (int j = i; j <= n; j++)
			maxcost[j] = max(maxcost[j], maxcost[j-i]+cost[i]);

	cout << maxcost[n];
}