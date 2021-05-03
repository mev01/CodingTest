// baekjoon11724.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
using namespace std;
void DFS(int a);
int visit[1001] = { 0, };
vector<int> v[1001];

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int	N, M, cnt = 0;
	cin >> N >> M;

	for (int i = 0; i < M; i++) {	//a, b번째 벡터에 원소 b, a추가
		int a, b;
		cin >> a >> b;
		v[a].push_back(b);
		v[b].push_back(a);
	}
	for (int i = 1; i <= N; i++) {
		if (visit[i] == 1)
			continue;
		cnt++;
		DFS(i);
	}
	cout << cnt;
}

void DFS(int a) {
	visit[a] = 1;
	for (int i = 0; i < v[a].size(); i++) {
		if (!visit[v[a][i]])
			DFS(v[a][i]);
	}
}