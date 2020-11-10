#include <iostream>
#include <deque>
using namespace std;

int n, m, map[101][101], visit[101][101], dx[4] = { 0,0,-1,1 }, dy[4] = { -1,1,0,0 };
deque<pair<int, int>> de;

void BFS(int x, int y) {
	de.push_back(make_pair(x, y));
	for (int i = 0; i < 4; i++){
		if (x + dx[i] > 0 && y + dy[i] > 0 && map[x + dx[i]][y + dy[i]]&&!visit[x + dx[i]][y + dy[i]]) {
			de.push_back(make_pair(x + dx[i], y + dy[i]));
			visit[x + dx[i]][y + dy[i]] = visit[x][y] + 1;
		}
		if (x + dx[i] == n && y + dy[i] == m) return;
	}
	de.pop_front();
	BFS(de[0].first, de[0].second);
}


int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> n >> m;
	for (int i = 0; i < n; i++)
	{
		string a;
		cin >> a;
		for (int j = 0; j < m; j++){
			map[i + 1][j + 1] = a[j]-'0';
		}
	}
	visit[1][1] = 1;
	BFS(1, 1);
	cout << visit[n][m];
}
