// baekjoon1987.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
using namespace std;
void DFS(int x, int y);
int alpha[20][20] = { 0, };
int visit[26] = { 0, };
int cnt = 0, ans = 0;
int mapx[4] = { 0,-1,0,1 };
int mapy[4] = { -1,0,1,0 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int R, C;
	cin >> R >> C;
	for (int i = 0; i < R; i++){
		string a;
		cin >> a;
		for (int j = 0; j < C; j++){	//아스키코드 사용해서 A -> 0, B- > 1로 변환
			alpha[i][j] = a[j] - 65;
		}
	}
	DFS(0,0);
	cout << ans;
}

void DFS(int x, int y) {
	visit[alpha[x][y]] = 1;	// 각 알파벳에 해당하는 배열에 방문했는지 표시
	cnt++;
	for (int i = 0; i < 4; i++){	//상하좌우 탐색
		if (!visit[alpha[x + mapx[i]][y + mapy[i]]] && x + mapx[i] >= 0 && y + mapy[i] >= 0)	//근처 배열의 내용을 통해 visit 배열에서 검색, 해당 알파벳을 방문하지 않은 경우
			DFS(x + mapx[i], y + mapy[i]);
	}
	if (ans < cnt)
		ans = cnt;
	cnt--;
	visit[alpha[x][y]] = 0;
}