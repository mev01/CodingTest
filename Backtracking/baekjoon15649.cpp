#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

void DFS(int x);
void Print();
bool Promising(int i, int y);

vector<int> v = { 0 };
int N, M;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	cin >> N >> M;
	DFS(1);
}

void DFS(int x) {
	if (x == M+1) {
		Print();
		return;
	}
	for (int i = 1; i <= N; i++) {
		if (Promising(i, x)) {
			v.push_back(i);
			DFS(x+1);
			v.pop_back();
		}
	}
}

bool Promising(int i, int x) {
	for (int j = 1; j < x; j++) {
		if (v[j]==i)
			return false;
	}
	return true;
}

void Print() {
	for (int i = 1; i <= M; i++) {
		cout << v[i] << " ";
	}
	cout << "\n";
}