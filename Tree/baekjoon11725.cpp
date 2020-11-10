#include <iostream>
#include <vector>
using namespace std;

int node[100001] = { 0,1 };
vector<int> v[100001];

void Tree(int r) {
	for (int i = 0; i < v[r].size(); i++){
		if (node[v[r][i]])
			v[r].erase(v[r].begin() + i--);
		else
			node[v[r][i]] = r;
	}
	for (int i = 0; i < v[r].size(); i++) {
		Tree(v[r][i]);
	}
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int n, a, b;
	
	cin >> n;
	for (int i = 0; i < n - 1; i++) {
		cin >> a >> b;
		v[a].push_back(b);
		v[b].push_back(a);
	}
	Tree(1);
	for (int i = 2; i <= n; i++){
		cout << node[i] << '\n';
	}
}