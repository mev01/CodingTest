// baekjoon1786.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string T,P;
	getline(cin, T);
	getline(cin, P);
	int lenP = P.length(), lenT = T.length();

	//pi에pi 배열 넣기
	vector<int> pi(lenP,0);
	int j = 0;
	for (int i = 1; i < lenP; i++){		
		while (j > 0 && P[i] != P[j])
			j = pi[--j];
		if (P[i] == P[j])
			pi[i] = ++j;
	}

	//P의 처음 인덱스 나타내는 p, 비교하는 인덱스를 나타내는 i, 
	vector<int> ans;
	int k = 0;
	for (int i = 0; i < lenT; i++)
	{
		while (k > 0 && T[i] != P[k])
			k = pi[--k];
		if (T[i] == P[k]) {
			if (k == lenP - 1) {
				ans.push_back(i - lenP + 2);
				k = pi[k];
			}
			else {
				k++;
			}
		}
	}

	cout << ans.size()<<endl;
	for (int i = 0; i < ans.size(); i++){
		cout << ans[i] << " ";
	}
}