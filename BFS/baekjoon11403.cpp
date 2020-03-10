// baekjoon11403.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <iostream>
#include <vector>
#include <string>
#include <string.h>
#include <queue>
#include <algorithm>
using namespace std;
vector<int> v[101];
void BFS(int num, int x);

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);
	
	int num;	
	cin >> num;

	for (int i = 0; i < num; i++){
		for (int j = 0; j < num; j++){
			int a;
			cin >> a;
			if (a == 1)
				v[i + 1].push_back(j + 1);
		}
	}
	for (int i = 1; i <= num; i++){
		BFS(num, i);
		for (int j = 1; j <= num; j++){
			if (find(v[i].begin(), v[i].end(), j) != v[i].end())
				cout << "1 ";
			else
				cout << "0 ";
		}
		cout << "\n";
	}
}

void BFS(int num, int x) {
	queue<int> que;
	int* visit = new int[num + 1]{ 0, };
	
	que.push(x);
	int frt=x;
	while (!que.empty()) {	//큐가 빌 때까지 계속 실행, (x, y)의 근처 검사해서 큐에 넣기
		frt = que.front();
		for (int i = 0; i < v[frt].size(); i++){
			if (visit[v[frt][i]] == 0) {
				que.push(v[frt][i]);
				v[x].push_back(v[frt][i]);
				visit[v[frt][i]] = 1;
			}
		}
		que.pop();
	}
}