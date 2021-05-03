// baekjoon1701.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main()
{
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	string S;
	getline(cin, S);

	int cnt = 0, len = S.size();
	while (len > 1)
	{	
		vector<int> v(len, 0);
		int j = 0;
		for (int i = 1; i < len; i++)//pi배열 구하기
		{
			while (j > 0 && S[i] != S[j])
				j = v[--j];
			if (S[i] == S[j])
				v[i] = ++j;
			if (v[i] > cnt)
				cnt = v[i];//pi배열의 원소중 최대 값이 두번 이상 겹치는 문자열 중 가장 긴 길이, 최대 값 계속 업데이트
		}
		S = S.substr(1);//앞의 한자리씩 줄여나가며 배열 생성
		len--;
	}

	cout << cnt;
}