#include <iostream>
#include <vector>
using namespace std;

int dis[100001];
vector<pair<int, int>> vt[100001];

void DFS(int pas, int i) {
	for (int j = 0; j < vt[i].size(); j++){
		if(pas!= vt[i][j].first)
			dis[vt[i][j].first] = vt[i][j].second + dis[i];
	}
	for (int j = 0; j < vt[i].size(); j++) {
		if (pas != vt[i][j].first)
			DFS(i, vt[i][j].first);
	}
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n, num, a, b, max = 0;
	cin >> n;
	for (int i = 0; i < n; i++){
		cin >> num >> a;
		while (a!=-1){
			cin >> b;
			vt[num].push_back(make_pair(a, b));
			cin >> a;
		}
	}
	DFS(0, 1);
	for (int i = 1; i <= n; i++){
		if (dis[i] > max){
			max = dis[i];
			num = i;
		}
	}
	dis[num] = 0;
	DFS(0, num);
	max = 0;
	for (int i = 1; i <= n; i++)
		if (dis[i] > max)
			max = dis[i];
	cout << max;
}