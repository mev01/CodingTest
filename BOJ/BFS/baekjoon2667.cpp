// baekjoon2667.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//
#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>
using namespace std;
int BFS(int x, int y);
int visit[25][25] = { 0 }, map[25][25] = { 0 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int	N, cnt = 0;
	vector<int> house;
	cin >> N;

	for (int i = 0; i < N; i++) {
		string a;
		cin >> a;
		for (int j = 0; j < N; j++) {
			map[i][j] = a[j] - '0';
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == 1 && visit[i][j] == 0) {
				house.push_back(BFS(i, j));
				cnt++;
			}
		}
	}
	sort(house.begin(), house.end());
	cout << cnt << "\n";
	for (int i = 0; i < house.size(); i++) {
		cout << house[i] << "\n";
	}

}

int BFS(int x, int y) {
	queue<int> qx, qy;
	int house = 0;

	qx.push(x);
	qy.push(y);
	visit[x][y] = 1;
	while (!qx.empty()) {	//큐가 빌 때까지 계속 실행, (x, y)의 근처 검사해서 큐에 넣기
		x = qx.front();
		y = qy.front();
		if (map[x][y - 1] == 1 && y - 1 >= 0 && visit[x][y - 1] != 1) {	//상
			visit[x][y - 1] = 1;
			qx.push(x);
			qy.push(y - 1);
		}
		if (map[x - 1][y] == 1 && x - 1 >= 0 && visit[x - 1][y] != 1) {	//좌
			visit[x - 1][y] = 1;
			qx.push(x - 1);
			qy.push(y);
		}
		if (map[x + 1][y] == 1 && x + 1 <= 24 && visit[x + 1][y] != 1) {	//우
			visit[x + 1][y] = 1;
			qx.push(x + 1);
			qy.push(y);
		}
		if (map[x][y + 1] == 1 && y + 1 <= 24 && visit[x][y + 1] != 1) {	//하
			visit[x][y + 1] = 1;
			qx.push(x);
			qy.push(y + 1);
		}
		qx.pop();
		qy.pop();
		house++;
	}

	return house;
}