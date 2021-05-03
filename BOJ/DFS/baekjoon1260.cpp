// baekjoon1260.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
using namespace std;
void DFS(vector<int>* m, int N, int M, int V);
void BFS(vector<int>* m, int N, int M, int V);

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int	N, M, V;
	cin >> N >> M >> V;

	vector<int>* v = new vector<int>[N + 1];
	for (int i = 0; i < M; i++) {	//a, b번째 벡터에 원소 b, a추가
		int a, b;
		cin >> a >> b;
		v[a].push_back(b);
		v[b].push_back(a);
	}
	for (int i = 0; i < N; i++) {	//1~N 번째 벡터 정렬
		sort(v[i + 1].begin(), v[i + 1].end());
	}
	if (N < V) exit(1);
	DFS(v, N, M, V);
	cout << "\n";
	BFS(v, N, M, V);
}

void DFS(vector<int>* m, int N, int M, int V) {
	stack<int> st;
	bool* visit = new bool[N + 1];
	fill_n(visit, N + 1, false);
	cout << V << " ";
	st.push(V);
	visit[V] = true;
	if (m[V].empty())	//V와 연결된 정점이 없는 경우
		return;
	while (!st.empty()) {	//스택이 빌때까지 계속 실행
		V = st.top();
		for (int i = 0; i < m[V].size(); i++) {	//V번째 벡터의 원소들 검사
			if (!visit[m[V][i]]) {	//방문하지 않은 원소의 경우
				V = m[V][i];
				cout << V << " ";
				st.push(V);
				visit[V] = true;
				break;
			}
			else if (i == m[V].size() - 1) 	//검사 했는데 해당 벡터의 원소를 모두 방문한 경우
				st.pop();
		}
	}
}

void BFS(vector<int>* m, int N, int M, int V) {
	queue<int> que;
	bool* visit = new bool[N + 1];
	fill_n(visit, N + 1, false);
	que.push(V);
	visit[V] = true;
	if (m[V].empty()) {	//V와 연결된 정점이 없는 경우
		cout << que.front() << " ";
		return;
	}
	while (!que.empty()) {	//큐가 빌 때까지 계속 실행
		V = que.front();
		for (int i = 0; i < m[V].size(); i++) {	//해당 벡터의 원소중 방문하지 않은 원소를 모두 큐에 넣기
			if (!visit[m[V][i]]) {
				que.push(m[V][i]);
				visit[m[V][i]] = true;
			}
		}
		cout << que.front() << " ";
		que.pop();
	}
}