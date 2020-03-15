// baekjoon6603.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
using namespace std;
void DFS(vector<int> v, vector<int> ans, int num, int st);
int visit[13] = { 0, };

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int num, st = 0;
	cin >> num;
	while (num != 0) {
		vector<int> v, ans;
		v.push_back(0);
		for (int i = 0; i < num; i++){
			int a;
			cin >> a;
			v.push_back(a);
		}
		DFS(v, ans, num, 1);
		cout << "\n";
		cin >> num;
	}
}

void DFS(vector<int> v, vector<int> ans, int num, int st) {
	if (ans.size()==6){
		for (int i = 0; i < 6; i++){
			cout << ans[i] << " ";
		}
		cout << "\n";
		return;
	}
	for (int i = st; i <= num; i++){
		if (6-ans.size()+st<=num+1)
		{
			ans.push_back(v[i]);
			DFS(v, ans, num, i+1);
			ans.pop_back();
		}
	}
}