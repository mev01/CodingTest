// baekjoon9663.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
using namespace std;
void DFS(int num, int y);
bool Promising(int i, int y);
int ans = 0;
vector<int> x = { 0 };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num;
	cin >> num;
	DFS(num, 1);
	cout << ans;
}

void DFS(int num, int y) {
	if (y == num+1) {
		ans++;
		return;
	}
	for (int i = 1; i <= num; i++) {
		if (Promising(i,y)) {
			x.push_back(i);
			DFS(num, y + 1);
			x.pop_back();
		}
	}
}

bool Promising(int i, int y) {
	for (int j = 1; j < y; j++) {
		if (x[j] == i || x[j] + (y - j) == i || x[j] - (y - j) == i)
			return false;
	}
	return true;
}