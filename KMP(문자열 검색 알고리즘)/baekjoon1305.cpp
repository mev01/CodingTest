// baekjoon1305.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	int L;
	string S;

	cin >> L;
	cin.ignore();
	getline(cin, S);

	//S를 pi함수로 만들어서 L-최대수
	int j = 0;
	vector<int> v(L, 0);
	for (int i = 1; i < L; i++)
	{
		while (j > 0 && S[i] != S[j])
			j = v[--j];
		if (S[i] == S[j])
			v[i] = ++j;
	}

	cout << L - v[L - 1];
}