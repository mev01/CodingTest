#include <iostream>
#include <vector>
using namespace std;
vector<int> ver[20001];
int visit[20001];

void DFS(int i) {
	for (int j = 0; j < ver[i].size(); j++){
		if (!visit[ver[i][j]]) {
			if (visit[i] == 1) {
				visit[ver[i][j]] = 2;
				DFS(ver[i][j]);
			}
			if (visit[i] == 2) {
				visit[ver[i][j]] = 1;
				DFS(ver[i][j]);
			}
		}
	}
}

int Graph(int v) {
	for (int i = 1; i <= v; i++){
		for (int j = 0; j < ver[i].size(); j++) {
			if (visit[i] == visit[ver[i][j]]) return 0;
		}
	}
	return 1;
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int k, v, e;
	cin >> k;
	while (k--){
		cin >> v >> e;
		vector<int> ver1[20001];
		while (e--){
			int a, b;
			cin >> a >> b;
			ver1[a].push_back(b);
			ver1[b].push_back(a);
		}
		fill_n(visit, 20001, 0);
		copy(ver1, ver1 + 20001, ver);
		for (int i = 1; i <= v; i++){
			if (!visit[i]) {
				visit[i] = 1;
				DFS(i);
			}
		}
		cout << (Graph(v) ? "YES" : "NO") << "\n";
	}
}