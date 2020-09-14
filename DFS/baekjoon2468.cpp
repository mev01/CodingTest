#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;

int map[100][100] = { 0, };
int visit[100][100] = { 0, };
int mapx[4] = { 0,-1,0,1 };
int mapy[4] = { -1,0,1,0 };
int N;

void DFS(int x, int y, int rain) {//각지역과 연결된 구역을 visit에 표시
	visit[x][y] = 1;	
	for (int i = 0; i < 4; i++) {	//상하좌우 탐색
		if (!visit[x + mapx[i]][y + mapy[i]] && map[x + mapx[i]][y + mapy[i]] > rain && x + mapx[i] >= 0 && y + mapy[i] >= 0 && x + mapx[i] < N && y + mapy[i] < N)	//근처 배열의 내용을 통해 visit 배열에서 검색, 해당 지역을 방문하지 않은 경우
			DFS(x + mapx[i], y + mapy[i], rain);
	}
}

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int fans = 1, max = 0, a;
	cin >> N;
	for (int i = 0; i < N; i++) {	//값 map배열에 입력
		for (int j = 0; j < N; j++) {
			cin >> a;
			map[i][j] = a;
			if (max < a) max = a;
		}
	}

	for (int rain = 1; rain < max; rain++){	//비의 양에 따른 계산 반복
		memset(visit, 0, sizeof(visit));
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > rain && !visit[i][j]) {	//물에 잠기지 않았으면서 방문하지 않은 구역
					DFS(i, j, rain);
					ans++;
				}
			}
		}
		if (fans < ans) fans = ans;
	}
	cout << fans;
}