// baekjoon1012.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <vector>
#include <string>
#include <string.h>
#include <queue>
using namespace std;
void BFS(int x, int y);
int visit[50][50] = { 0 }, map[50][50] = { 0 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int	num;
	vector<int> house;
	cin >> num;

	for (int i = 0; i < num; i++) {
		int M, N, K, cnt = 0;
		cin >> M >> N >> K;
		for (int i = 0; i < K; i++) {
			int X, Y;
			cin >> X >> Y;
			map[X][Y] = 1;
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visit[i][j] == 0) {
					BFS(i, j);
					cnt++;
				}
			}
		}
		cout << cnt << "\n";
		memset(visit, 0, sizeof(visit));
		memset(map, 0, sizeof(map));
	}
}

void BFS(int x, int y) {
	queue<int> qx, qy;

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
		if (map[x + 1][y] == 1 && x + 1 <= 49 && visit[x + 1][y] != 1) {	//우
			visit[x + 1][y] = 1;
			qx.push(x + 1);
			qy.push(y);
		}
		if (map[x][y + 1] == 1 && y + 1 <= 49 && visit[x][y + 1] != 1) {	//하
			visit[x][y + 1] = 1;
			qx.push(x);
			qy.push(y + 1);
		}
		qx.pop();
		qy.pop();
	}
}