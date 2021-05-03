#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
void DFS(int num);
void Promising(int num, int ck[10]);
void Print();

vector<int> x, y;
vector<int> ans[81];
int map[10][10] = { 0, };
int pred[10][10] = { 0, };
int siz = 0;
int Find = 0;

int main()
{
	int a;
	for (int i = 1; i < 10; i++) {
		for (int j = 1; j < 10; j++) {
			cin >> a;
			if (!a) {
				x.push_back(i);
				y.push_back(j);
				siz++;
			}
			map[i][j] = a;
		}
	}
	DFS(0);
}

void DFS(int num) {
	if (num == siz) {  //정답이 완성되면 출력하고 전부 return
		Find = 1;
		Print();
		return;
	}
	int ck[10] = { 0, };
	Promising(num, ck); //유망한지 검사

	for (int i = 1; i < 10; i++) {
		if (ck[i] == 0 && !Find) {
			map[x[num]][y[num]] = i;
			DFS(num + 1);
			map[x[num]][y[num]] = 0;
		}
	}
}

void Promising(int num, int ck[10]) {
	for (int i = 1; i < 10; i++) {	
		ck[map[x[num]][i]]++;	//가로줄 검사
		ck[map[i][y[num]]]++;	//세로줄 검사
	}
	for (int i = (x[num] - 1) / 3 * 3 + 1; i < (x[num] - 1) / 3 * 3 + 4; i++) {	//정사각형 검사
		for (int j = (y[num] - 1) / 3 * 3 + 1; j < (y[num] - 1) / 3 * 3 + 4; j++) {
			ck[map[i][j]]++;
		}
	}
}

void Print() {
	for (int i = 1; i < 10; i++) {
		for (int j = 1; j < 10; j++) {
			cout << map[i][j] << " ";
		}
		cout << "\n";
	}
}